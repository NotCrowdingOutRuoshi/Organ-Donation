package Net.UDP.Test.Stub;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;

import com.sun.jndi.cosnaming.IiopUrl.Address;

import Common.Interfaces.ITCPServer;

public class TCPSMStub implements ITCPServer{

	private Vector<InetAddress> ipTables;
	@Override
	public void startServer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void BroadcastAllClient() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<InetAddress> getClientIPTable() {
		// TODO Auto-generated method stub
		try {
			ipTables.add(InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ipTables;
	}
	

}
