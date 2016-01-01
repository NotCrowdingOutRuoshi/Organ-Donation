package Net.UDP.Test;

import static org.junit.Assert.assertTrue;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.sun.corba.se.spi.activation.Server;
import Common.Interfaces.ICentralizedDataCenter;
import Common.Interfaces.ITCPServer;
import Common.Interfaces.IUDPBroadcast;
import Common.Interfaces.IUDPUpdateServer;
import Libraries.JSON.JSONArray;
import Net.UDP.Server.UDPBroadCast;
import Net.UDP.Test.Mock.UDPUSMock;
import Net.UDP.Test.Stub.CDCStub;
import Net.UDP.Test.Stub.TCPSMStub;


public class UDPBCTest {
	private ICentralizedDataCenter _cdc;
	private ITCPServer _tcpsm;
	private IUDPBroadcast udpBroadCast;
	private UDPUSMock udpusMock;
	
	@Before
	public void seUP() {
		_cdc = new CDCStub();
		_tcpsm = new TCPSMStub();
		udpBroadCast = new UDPBroadCast(_tcpsm, _cdc);
		udpusMock = new UDPUSMock();		
	}
	

	@After
	public void setDown() {
		udpBroadCast = null;
		udpusMock = null;
	}

	@Test
	public void testBroadCastOneMessage() {
		udpBroadCast.startUDPBroadCast();
		JSONArray players = new JSONArray(_cdc.getUpdateInfo());
		String receiveData = udpusMock.receiveData();
		assertTrue(receiveData.equals(players.get(0).toString()));
	
	}

}