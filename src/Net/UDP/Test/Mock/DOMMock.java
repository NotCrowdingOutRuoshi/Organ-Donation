package Net.UDP.Test.Mock;

import Net.UDP.Test.Stub.DOMStub;

public class  DOMMock extends DOMStub {

	private String result = "";
	public void addVirtualCharacter(String msg) {
		result = "addVirtualCharacter " + msg;
	}

	public synchronized void addItem(String msg) {
		result = "addItem " + msg;
	}

	public void updateVirtualCharacter(String msg) {
		result = "updateVirtualCharacter " + msg;
	}

	public void updateItem(String msg) {
		result = "updateItem " + msg;
	}
	
	public String getResult() {
		return result;
	}
	
	public void initResult() {
		result = "";
	}
}
