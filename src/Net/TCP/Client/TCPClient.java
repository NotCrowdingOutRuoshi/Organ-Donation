package Net.TCP.Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import UserInterfaceModule.GameManager;
import Common.Interfaces.ITCPClient;

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
			GameManager.getInstance().setClientId(clientId);
			
			while(true){
				System.out.println("Client receive:"+input.read());
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
