package Common.Interfaces;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public interface ITCPClient {
	
	public boolean connectServer(InetAddress serverip) throws UnknownHostException, IOException;
	
	public void inputMoves(String MoveCode) throws IOException;
	
	public void disconnect() throws IOException;
	
}
