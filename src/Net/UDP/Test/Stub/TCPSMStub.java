package Net.UDP.Test.Stub;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;

import Common.Interfaces.ITCPServer;

public class TCPSMStub implements ITCPServer{

	private Vector<InetAddress> ipTables;

	
	public TCPSMStub() {
		ipTables= new Vector<InetAddress>();
		try {
			ipTables.add(InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void startServer() {
		// TODO Auto-generated method stub
	
	}

	public void BroadcastAllClient() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<InetAddress> getClientIPTable() {
		// TODO Auto-generated method stub
		return ipTables;
	}

	@Override
	public void BroadcastAllClient(String state) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeServer() throws IOException {
		// TODO Auto-generated method stub
		
	}
	

}
