package Net.UDP.Test.Stub;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import Common.Interfaces.IUDPBroadCast;

public class UDPBCStub implements IUDPBroadCast {
	private int _port = 27016;;

	private DatagramSocket _socket;

	public UDPBCStub() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void startUDPBroadCast() {
		// TODO Auto-generated method stub
		try {
			_socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void endUDPBroadCast() {
		// TODO Auto-generated method stub
		if (_socket != null) {
			_socket.close();
		}
	}

	public void send(String msg, String ip) {

		byte buffer[] = msg.getBytes();
		try {
			_socket.send(new DatagramPacket(buffer, buffer.length, InetAddress.getByName(ip), _port));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
