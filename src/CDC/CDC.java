package CDC;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import CDC.Entities.Organ;
import CDC.Entities.Player;
import CDC.Entities.StateType;
import Common.Direction;

public class CDC {
	Map<Integer,Player> PlayerList;
	
	public CDC(){
		PlayerList = new HashMap<Integer,Player>();
		
	}
	
	public void addPlayer(int id){
		Player player = new Player();
		player.setID(id);
		for(int i=0;i<4;i++){
			Organ organ = new Organ(id*10+i);
			player.addOrgan(organ);
		}
		
		PlayerList.put(id, player);
	}

	public void updateDir(int id ,Direction dir,StateType state){
		Player player = PlayerList.get(id);
		player.setDir(dir);
		
	}
	
	public void setState(int id,StateType state){
		Player player = PlayerList.get(id);
		player.setState(state);
		if(state == StateType.ATTACK){
			playerAttack(player);
		}
	}
	
	private void playerAttack(Player player){
		int targetX = player.getX();
		int targetY = player.getY();
		
		if(player.getDir()==Direction.UP){
			targetY -= 1;
		}
		else if(player.getDir()==Direction.DOWN){
			targetY += 1;
		}
		else if(player.getDir()==Direction.LEFT){
			targetX += 1;
		}
		else if(player.getDir()==Direction.RIGHT){
			targetX -= 1;
		}
		
		for(int i=0;i<PlayerList.size();i++){
			if(i!=player.getID()){
				Player player2 = PlayerList.get(i+1);
				if(targetX==player2.getX() && targetY==player2.getY()){
					player2.setHealth(player2.getHealth()-50);				
				}
			}
		}
		player.setHealth(player.getHealth()-5);
	}
	
	public String getUpdateInfo(){
		JSONObject jsonObject = new JSONObject(PlayerList);
		return jsonObject.toString();
	}
	
	public void computingXYThread(){

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
	        			if(player.getState()==StateType.WALK){
	        				switch (player.getDir()) {
		        			case UP:
		        				player.setY(player.getY()-player.getSpeed()/2);;
		        				break;

		        			case DOWN:
		        				player.setY(player.getY()+player.getSpeed()/2);;
		        				break;
		        				
		        			case LEFT:
		        				player.setX(player.getX()-player.getSpeed()/2);;
		        				break;
		        				
		        			case RIGHT:
		        				player.setX(player.getX()+player.getSpeed()/2);;
		        				break;
		        				
		        			default:
		        				break;
		        			}	
	        			}
	        					
	        		}
	        	}
	        	
	        }
	    };
	    thread.start();
	}
	
	public void StateThread(){

		Thread thread = new Thread()
	    {
	        public void run()
	        {
	        	while(true){

	        		for(int i=0;i<PlayerList.size();i++){
	        			Player player = PlayerList.get(i+1);
	        			if(player.getHealth()==0){
	        				player.setState(StateType.EXHAUSTION);
	        			}
	        		}
	        	}
	        	
	        }
	    };
	    thread.start();
	}
}
