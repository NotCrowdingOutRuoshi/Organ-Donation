package Net.UDP.Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

import Common.Constants;
import Common.Interfaces.IDynamicObjectModule;
import Common.Interfaces.IUDPUpdateServer;
import Libraries.JSON.JSONObject;

public class UDPUdateServer implements IUDPUpdateServer {
	private IDynamicObjectModule _dom;
	private int bufferSize = 512;
	private long updateSecond = 50;
	private byte _buffer[];

	private DatagramPacket _dataPacket;
	private DatagramSocket _socket;

	private Timer _reciveUDPDataTimer;

	public UDPUdateServer(IDynamicObjectModule dom) {
		_buffer = new byte[bufferSize];
		_dataPacket = new DatagramPacket(_buffer, _buffer.length);
		_reciveUDPDataTimer = new Timer();
		_dom = dom;
	}

	@Override
	public void initUDPserver() {
		try {
			_socket = new DatagramSocket(Constants.UDPSERVERPORT);
		} catch (SocketException e) {

		}
		_reciveUDPDataTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					reciveFromUDPServer();
				} catch (IOException e) {
				}
			}
		}, 0, updateSecond);
	}

	@Override
	public void stopUDPServer() {
		// TODO Auto-generated method stub
		if (_reciveUDPDataTimer != null) {
			_reciveUDPDataTimer.cancel();
			_socket.close();
		}
	}

	private void reciveFromUDPServer() throws IOException {
		_socket.receive(_dataPacket);
		String msg = new String(_dataPacket.getData(), 0, _dataPacket.getLength());
		JSONObject player = new JSONObject(msg);
		_dom.updateSprite(Integer.valueOf(player.get("id").toString()), player);
	}

}
