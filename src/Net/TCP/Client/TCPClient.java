package Net.TCP.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import Common.Constants;
import Common.Interfaces.ITCPClient;
import UserInterfaceModule.GameManager;

public class TCPClient implements Runnable, ITCPClient {

	private Socket socket;
	
	private InputStream input;

	public TCPClient() {
		// TODO Auto-generated constructor stub
		socket = new Socket();
	}

	public boolean connectServer(InetAddress serverip)
			throws UnknownHostException, IOException {
		if (!socket.isConnected()) {
			socket = new Socket(serverip.getHostAddress(), 9090);
			input = socket.getInputStream();
			
			startReceive();
		}
		return socket.isConnected();
	}

	public void inputMoves(String MoveCode) throws IOException {
		assert(socket.isConnected());
		MoveCode += "\r\n"; 
		OutputStream output = socket.getOutputStream();
		output.write(MoveCode.getBytes());
		output.flush();
	}
	
	private void startReceive(){
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			int clientId = input.read();
			System.out.println("client: "+clientId);
			GameManager.getInstance().setClientId(clientId);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			while(true){
//				String recv = br.readLine();
				String[] rec = br.readLine().split(" ");
				String recv = rec[0];
				int winner = 0;
				if(rec.length>1){
					winner = Integer.valueOf(rec[1]);
				}
				switch(recv){
					case Constants.GAME_STATE_MENU:
						GameManager.getInstance().setGameStatus(recv);
						break;
					case Constants.GAME_STATE_WAIT:
						GameManager.getInstance().setGameStatus(recv);
						break;
					case Constants.GAME_STATE_INIT:
						GameManager.getInstance().setGameStatus(recv);
						break;
					case Constants.GAME_STATE_START:
						GameManager.getInstance().setGameStatus(recv);
						break;
					case Constants.GAME_STATE_OVER:
						GameManager.getInstance().setWinnerId(winner);
						GameManager.getInstance().setGameStatus(recv);
						break;
					default:
						System.out.println("illegal message:"+recv+" !!!!!!!!!");
						break;
				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void disconnect() throws IOException {
		// TODO Auto-generated method stub
		socket.close();
	}

}
