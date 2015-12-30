package Net.TCP.Client;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient implements Runnable {

	private Socket socket;

	public final static int GET = 33;
	
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

	public void inputMoves(int MoveCode) throws IOException {
		assert (MoveCode == KeyEvent.VK_UP || MoveCode == KeyEvent.VK_DOWN
				|| MoveCode == KeyEvent.VK_LEFT
				|| MoveCode == KeyEvent.VK_RIGHT || MoveCode == KeyEvent.VK_UP
				|| MoveCode == TCPClient.GET );
		
		assert(socket.isConnected());

		OutputStream output = socket.getOutputStream();
		output.write(MoveCode);
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
