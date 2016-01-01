package Net.UDP.Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Libraries.JSON.JSONArray;

import static org.junit.Assert.assertTrue;
import Net.UDP.Client.UDPUdateServer;
import Net.UDP.Test.Mock.DOMMock;
import Net.UDP.Test.Stub.CDCStub;
import Net.UDP.Test.Stub.UDPBCStub;

public class UDPUSTest {
	private DOMMock _dom;
	private UDPUdateServer _udpUpdateServer;
	private UDPBCStub _udpbcStub;
	private CDCStub _cdcStub;
	
	@Before
	public void setup() {
		_dom = new DOMMock();
		_udpbcStub = new UDPBCStub();
		_udpUpdateServer = new UDPUdateServer(_dom);
		_cdcStub = new CDCStub();
	}

	@After
	public void setdown() {

	}


	@Test
	public void testUDPUSToUpdateVirtualCharacter() {
		_udpUpdateServer.initUDPserver();
		_udpbcStub.startUDPBroadCast();
		JSONArray msgArray = new JSONArray( _cdcStub.getUpdateInfo());
		String msg = msgArray.get(0).toString();
		_udpbcStub.send(msg, "127.0.0.1");
		while(_dom.getResult().equals(""));
		assertTrue(msg.equals(_dom.getResult()));
	}

	@Test
	public void testGetErrorMessage() {
	
	}
}
