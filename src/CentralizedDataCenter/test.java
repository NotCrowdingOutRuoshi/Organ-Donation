package CentralizedDataCenter;

import java.util.ArrayList;



import Common.Constants;
import Common.StateType;
import Libraries.JSON.JSONArray;
import Libraries.JSON.JSONObject;


public class test {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		CDC cdc = new CDC();
//		cdc.startGameLogicSchedule();
		cdc.addPlayer(1);
		cdc.addPlayer(2);
		cdc.addPlayer(3);
		cdc.addPlayer(4);
		cdc.PlayerList.get(2).setX(200);
		cdc.PlayerList.get(2).setY(340);
		cdc.setState(2, StateType.EXHAUST);
		cdc.updateDir(1, Constants.ACTIONCODE_EAST);
		cdc.setState(1, StateType.STEAL);
		cdc.updateDir(2, Constants.ACTIONCODE_EAST);
		cdc.setState(2, StateType.WALK);
		cdc.updateDir(3, Constants.ACTIONCODE_EAST);
		cdc.setState(3, StateType.WALK);
		cdc.updateDir(4, Constants.ACTIONCODE_EAST);
		cdc.setState(4, StateType.WALK);
//		cdc.setState(1, StateType.STEAL);
//		cdc.setState(2, StateType.IDLE);
//		cdc.GameLogicThread();
//		
		Thread.sleep(3000);
//		cdc.setState(1, StateType.STEAL);
		System.out.println(cdc.getUpdateInfo());
		
		JSONArray jsonObject = new JSONArray(cdc.getUpdateInfo());
		System.out.println(jsonObject.get(0));
		for(int i=0;i<jsonObject.length();i++){
			JSONObject jsonObject2 = new JSONObject(jsonObject.get(i).toString());
			System.out.println(jsonObject2.get("x")+"  "+jsonObject2.get("y"));
			
			JSONArray j = new JSONArray(jsonObject2.get("organs").toString());
			System.out.println(j.get(i));
		}
		
		
	}

}
