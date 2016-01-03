package Net.UDP.Test.Stub;

import java.util.HashMap;
import java.util.Map;

import Common.Interfaces.ICentralizedDataCenter;
import Libraries.JSON.JSONArray;
import Net.UDP.Test.Stub.Entities.PlayerStub;

public class CDCStub implements ICentralizedDataCenter {
	Map<Integer, PlayerStub> PlayerList;
	public CDCStub() {
		// TODO Auto-generated constructor stub
		PlayerList = new HashMap<Integer, PlayerStub>();
		PlayerStub player = new PlayerStub();
		player.setID(0);
		PlayerList.put(0, player);
	}
	@Override
	public void addPlayer(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDir(int id, int dir) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setState(int id, String state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUpdateInfo() {
		// TODO Auto-generated method stub
		JSONArray j = new JSONArray(PlayerList.values());
		return j.toString();
	}
	
	public void clearData() {
		
	}

	@Override
	public void startGameLogicSchedule() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGameState(String gameState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getGameState() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public int getWinnerId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
