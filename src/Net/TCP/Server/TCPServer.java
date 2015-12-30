package Net.TCP.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import CentralizedDataCenter.CDC;

public class TCPServer implements Runnable {

	private ServerSocket serverSocket;

	private Vector<InetAddress> clientIPTable;
	
	private Vector<Socket> clientOut;
	
	private CDC cdc;

	public final static int MOVE = 22, GET = 33;
	
	public TCPServer(CDC cdc) {
		this.cdc = cdc;
		clientIPTable = new Vector<InetAddress>();
		clientOut = new Vector<Socket>();
	}
	
	public void initTCPServer() throws IOException {
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
	
	private CDC cdc;

	public ConnectThread(Socket socket,CDC cdc) {
		this.socket = socket;
		this.cdc = cdc;
	}

	@Override
	public void run() {
		try {
			InputStream input = socket.getInputStream();

			while (true) {
				int moveCode = input.read();
				switch (moveCode) {
				case TCPServer.MOVE:
//					cdc.updateDirection(5, moveCode);
					break;
				case TCPServer.GET:
//					cdc.getItem(5);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}