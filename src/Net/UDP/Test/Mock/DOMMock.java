package Net.UDP.Test.Mock;

import Common.Interfaces.IDynamicObjectModule;
import DynamicObjectModule.Entities.Sprite;
import Libraries.JSON.JSONObject;

public class  DOMMock implements IDynamicObjectModule {

	private String result = "";

	@Override
	public void addVirtualCharacter(int clientNumber, JSONObject data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sprite findSprite(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sprite[] getAllDynamicObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateVirtualCharacter(int index, JSONObject data) {
		// TODO Auto-generated method stub
		result = data.toString();
	}
	
	public String getResult() {
		return result;
	}

	@Override
	public int getCountDown() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCountDown(int number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSprite(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}

	
}
