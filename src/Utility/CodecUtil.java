package Utility;

import java.util.Vector;

public class CodecUtil {
	public static String encode(String serverType,String msg) {
		return String.join(serverType, msg);
	}
	
	public static Vector<String> decode(String msg) {
		String updateInfo = msg;
		Vector<String> msgToken = new Vector<>();
		if(updateInfo.startsWith("{") && updateInfo.endsWith("}")) {
			msgToken.add(updateInfo);	
		} 
		else {
			do {
				int subIndex = updateInfo.indexOf(" ");
				if(subIndex == -1) assert false;
				
				msgToken.add(updateInfo.substring(0, subIndex));
				updateInfo = updateInfo.substring(subIndex + 1);
				if(updateInfo.startsWith("{") && updateInfo.endsWith("}")) {
					msgToken.add(updateInfo);	
				} 
			} while (true);
		}
		
		
		return msgToken;
	}
	

}
