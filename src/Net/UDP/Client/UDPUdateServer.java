package Net.UDP.Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import Common.Interfaces.IDynamicObjectModule;
import org.json.JSONObject;
import Common.Interfaces.IUDPUpdateServer;
import UserInterfaceModule.GameManager;
import Utility.CodecUtil;

public class UDPUdateServer implements IUDPUpdateServer {
	private IDynamicObjectModule _dom;
	private int _port = 27016;;
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
			_socket = new DatagramSocket(_port);
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
		Vector<String> msgList = CodecUtil.decode(msg);
		if (msgList.size() == 1) {
			JSONObject player = new JSONObject(msgList.get(0));
			_dom.updateSprite(Integer.valueOf(player.getString("id")), player);
		} else {
			JSONObject player = new JSONObject(msgList.get(1));
			_dom.addVirtualCharacter(Integer.valueOf(player.getString("id")), player);
		}

	}

}
