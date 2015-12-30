package Common.Interfaces;

import java.net.InetAddress;
import java.util.Vector;

public interface ITCPServer {
	
	public void startServer();
	
	public void BroadcastAllClient();
	
	public Vector<InetAddress> getClientIPTable();
	
}
