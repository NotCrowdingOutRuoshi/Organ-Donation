package Common.Interfaces;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Vector;

public interface ITCPServer {
	
	public void startServer();
	
	public void BroadcastAllClient(String state) throws IOException;
	
	public Vector<InetAddress> getClientIPTable();
	
	public void closeServer() throws IOException;
	
}
