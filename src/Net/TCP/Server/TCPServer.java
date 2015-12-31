package Net.TCP.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import Common.StateType;
import Common.Interfaces.ICentralizedDataCenter;
import Common.Interfaces.ITCPServer;

public class TCPServer implements Runnable, ITCPServer {

	private ServerSocket serverSocket;

	private Vector<InetAddress> clientIPTable;
	
	private Vector<Socket> clientOut;
	
	private ICentralizedDataCenter cdc;
	
	public TCPServer(ICentralizedDataCenter cdc) {
		this.cdc = cdc;
		clientIPTable = new Vector<InetAddress>();
		clientOut = new Vector<Socket>();
	}
	
	private void initTCPServer() throws IOException {
		serverSocket = new ServerSocket(9090);
		while(true){
			Socket socket = serverSocket.accept();
			new ConnectThread(socket,cdc).start();
			clientIPTable.add(socket.getInetAddress());
			clientOut.add(socket);
		}
	}
	
	public void startServer(){
		Thread thread = new Thread(this);
		thread.start();
	}

	public Vector<InetAddress> getClientIPTable() {
		return clientIPTable;
	}
	
	public void BroadcastAllClient() throws IOException{
		assert(serverSocket!=null);
		for( int i=0; i<clientOut.size(); i++ ){
			OutputStream out = clientOut.get(i).getOutputStream();
			out.write(11);
			out.flush();
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			initTCPServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class ConnectThread extends Thread {

	private Socket socket;
	
	private ICentralizedDataCenter cdc;
	
	private int playerId;
	
	private static int playerCnt = 0;

	public ConnectThread(Socket socket,ICentralizedDataCenter cdc) {
		this.socket = socket;
		this.cdc = cdc;
		playerCnt++;
		playerId = playerCnt;
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			OutputStream out = socket.getOutputStream();
			
			out.write(playerId);
			out.flush();
			
			cdc.addPlayer(playerId);
			
			while (true) {
				String[] recv = br.readLine().split(" ");
				int clientId = Integer.valueOf(recv[0]);
				String actionState = recv[recv.length-1]; 
				
				switch (actionState) {
				case StateType.WALK:
					cdc.updateDir(clientId, Integer.valueOf(recv[1]));
					cdc.setState(clientId, actionState);
					break;
				case StateType.ATTACK:
					cdc.setState(clientId, actionState);
					break;
				case StateType.STEAL:
					cdc.setState(clientId, actionState);
					break;
				case StateType.IDLE:
					cdc.setState(clientId, actionState);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}