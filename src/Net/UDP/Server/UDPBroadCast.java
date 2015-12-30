package Net.UDP.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import com.sun.org.apache.bcel.internal.classfile.Code;

import Common.Interfaces.ICentralizedDataCenter;
import Common.Interfaces.ITCPClient;
import Common.Interfaces.ITCPServer;
import Common.Interfaces.IUDPBroadcast;
import jdk.internal.dynalink.linker.LinkerServices.Implementation;


public class UDPBroadCast implements IUDPBroadcast {

	private int port = 27016;;
	private long updateSecond = 50;

	private ITCPServer _tcpsm;
	private ICentralizedDataCenter _cdc;

	private Timer _broadCaseTimer;

	private DatagramSocket _socket;

	
	
	public UDPBroadCast(ITCPServer tCPServer, ICentralizedDataCenter centralizedDataCenter) {
		_tcpsm = tCPServer;
		_cdc = centralizedDataCenter;
		_broadCaseTimer = new Timer();

	}

	@Override
	public void startUDPBroadCast() {
		// TODO Auto-generated method stub
		try {
			_socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			_socket.close();
			e.printStackTrace();
		}
		_broadCaseTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					broadCastToClient();
				} catch (IOException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}, 0, updateSecond);

	}

	@Override
	public void endUDPBroadCast() {
		// TODO Auto-generated method stub
		if (_broadCaseTimer != null) {
			_socket.close();
			_broadCaseTimer.cancel();
		}
	}

	private void broadCastToClient() throws IOException, UnknownHostException {
		Vector<InetAddress> IPTable = _tcpsm.getClientIPTable();
		String data = _cdc.getUpdateInfo();
	
		for (InetAddress ip : IPTable)  {
			byte buffer[] = data.getBytes();
			_socket.send(new DatagramPacket(buffer, buffer.length, ip, port));
		}
		
	}

}
