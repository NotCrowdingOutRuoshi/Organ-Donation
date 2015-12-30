package Common.Interfaces;

import java.net.InetAddress;

public interface ITCPCM {
	
	public boolean connectServer(InetAddress serverip);
	
	public void inputMoves(int MoveCode);
	
}
