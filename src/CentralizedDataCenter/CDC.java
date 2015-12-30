package CentralizedDataCenter;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

import CentralizedDataCenter.Entities.Organ;
import CentralizedDataCenter.Entities.Player;
import Common.Constants;
import Common.StateType;

public class CDC {
	Map<Integer,Player> PlayerList;
	Map<Integer,Organ> OrganList;
	private int alive = 4;
	public CDC(){
		PlayerList = new HashMap<Integer,Player>();
		OrganList = new HashMap<Integer,Organ>();
	}
	
	public void addPlayer(int id){
		Player player = new Player();
		player.setID(id);		
		PlayerList.put(id, player);
	}

	public void updateDir(int id ,int dir){
		Player player = PlayerList.get(id);
		player.setDir(dir);
		
	}
	
	public void setState(int id,String state){
		Player player = PlayerList.get(id);
		player.setState(state);
		if(state.equals(StateType.ATTACK)){
			playerAttack(player);
		}
		else if(state.equals(StateType.STEAL)){
			playerSteal(player);
		}
	}

	private void playerAttack(Player player){
		int targetX = player.getX();
		int targetY = player.getY();
		
		if(player.getDir()==Constants.ACTIONCODE_NORTH){
			targetY -= 1;
		}
		else if(player.getDir()==Constants.ACTIONCODE_SOUTH){
			targetY += 1;
		}
		else if(player.getDir()==Constants.ACTIONCODE_EAST){
			targetX += 1;
		}
		else if(player.getDir()==Constants.ACTIONCODE_WEST){
			targetX -= 1;
		}
		
		for(int i=1;i<=PlayerList.size();i++){
			if(i!=player.getId()){
				Player player2 = PlayerList.get(i);
				if(targetX==player2.getX() && targetY==player2.getY()){
					player2.setHealth(player2.getHealth()-50);
				}
			}
		}
		player.setHealth(player.getHealth()-5);
	}
	
	private void playerSteal(Player player){
		int targetX = player.getX();
		int targetY = player.getY();
		
		if(player.getDir()==Constants.ACTIONCODE_NORTH){
			targetY -= 1;
		}
		else if(player.getDir()==Constants.ACTIONCODE_SOUTH){
			targetY += 1;
		}
		else if(player.getDir()==Constants.ACTIONCODE_EAST){
			targetX += 1;
		}
		else if(player.getDir()==Constants.ACTIONCODE_WEST){
			targetX -= 1;
		}
		
		for(int i=0;i<PlayerList.size();i++){
			if(i!=player.getId()){
				Player player2 = PlayerList.get(i+1);
				if(targetX==player2.getX() && targetY==player2.getY() && player2.getState().equals(StateType.EXHAUST)){
					Organ o = player2.StealedOrgan();
					player2.remove(o);
					player.addOrgan(o);
				}
			}
		}
		player.setHealth(player.getHealth()-5);
	}
	
	public String getUpdateInfo(){
		JSONObject jsonObject = new JSONObject(PlayerList);
		return jsonObject.toString();
	}
	
	private void computingXYThread(){

		Thread thread = new Thread()
	    {
	        public void run()
	        {
	        	while(true){	
	        		try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
	        		for(int i=0;i<PlayerList.size();i++){
	        			Player player = PlayerList.get(i+1);
	        			if(player.getState().equals(StateType.WALK)){
	        				if(player.getDir()==Constants.ACTIONCODE_NORTH){
	        					player.setY(player.getY()-player.getSpeed()/2);
	        				}
	        				else if(player.getDir()==Constants.ACTIONCODE_SOUTH){
	        					player.setY(player.getY()+player.getSpeed()/2);
	        				}
	        				else if(player.getDir()==Constants.ACTIONCODE_EAST){
	        					player.setX(player.getX()+player.getSpeed()/2);
	        				}
	        				else if(player.getDir()==Constants.ACTIONCODE_WEST){
	        					player.setX(player.getX()-player.getSpeed()/2);
	        				}
	        				
	        			}
	        					
	        		}
	        	}
	        	
	        }
	    };
	    thread.start();
	}
	
	public void GameLogicThread(){

		Thread thread = new Thread()
	    {
	        public void run()
	        {
	        	while(true){
	        		enviromentLogic();
	        		logic();
	        		gameover();
	        	}
	        	
	        }
	    };
	    thread.start();
	}
	
	public void logic(){
		for(int i=1;i<=PlayerList.size();i++){
			Player player = PlayerList.get(i);
			if(player.getHealth()==0){
				player.setState(StateType.EXHAUST);
			}
			if(player.getEnergy()==0){
				player.setState(StateType.DEATH);
				alive -= 1;
			}
		}
	}
	
	public void enviromentLogic(){
		for(int i=1;i<PlayerList.size();i++){
			Player player = PlayerList.get(i);
			player.decreaseOrganHp(2);
		}
	}
	
	public void gameover(){
		if(alive<=0){
			//send gameover
		}
	}

}
