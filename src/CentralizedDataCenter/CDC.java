package CentralizedDataCenter;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import CentralizedDataCenter.Entities.Organ;
import CentralizedDataCenter.Entities.Player;
import Common.Constants;
import Common.StateType;
import Common.Interfaces.ICentralizedDataCenter;
import Libraries.JSON.JSONArray;
import Libraries.JSON.JSONObject;

public class CDC implements ICentralizedDataCenter {
	Map<Integer, Player> PlayerList;
	Map<Integer, Organ> OrganList;
	private String _gameState = Constants.GAME_STATE_WAIT;
	private Vector<String> _gameStateList;
	private Timer _enviromentTimer;
	private Timer _logicTimer;
	private int winnerId;
	
	public CDC() {
		PlayerList = new HashMap<Integer, Player>();
		OrganList = new HashMap<Integer, Organ>();
		_enviromentTimer = new Timer();
		_logicTimer = new Timer();
		_gameStateList = new Vector<>();
		_gameStateList.add(Constants.GAME_STATE_INIT);
		_gameStateList.add(Constants.GAME_STATE_MENU);
		_gameStateList.add(Constants.GAME_STATE_START);
		_gameStateList.add(Constants.GAME_STATE_OVER);
		_gameStateList.add(Constants.GAME_STATE_WAIT);
	}

	public void addPlayer(int id) {
		Player player = new Player();
		player.setID(id);
		if (id == 1) {
			player.setX(Constants.MIN_X);
			player.setY(Constants.MIN_Y);
		} else if (id == 2) {
			player.setX(Constants.MAX_X);
			player.setY(Constants.MIN_Y);
		} else if (id == 3) {
			player.setX(Constants.MIN_X);
			player.setY(Constants.MAX_Y);
		} else if (id == 4) {
			player.setX(Constants.MAX_X);
			player.setY(Constants.MAX_Y);
		}
		PlayerList.put(id, player);
	}

	public void updateDir(int id, int dir) {
		Player player = PlayerList.get(id);
		player.setEnergy(player.getEnergy() - 1);
		player.setDir(dir);

	}

	public void setState(int id, String state) {
		Player player = PlayerList.get(id);
		player.setState(state);
		if (state.equals(StateType.ATTACK)) {
			playerAttack(player);
		} else if (state.equals(StateType.STEAL)) {
			playerSteal(player);
		}
	}

	public String getUpdateInfo() {
		JSONArray j = new JSONArray(PlayerList.values());
		return j.toString();
	}

	@Override
	public void setGameState(String gameState) {
		// TODO Auto-generated method stub
		if (_gameStateList.contains(gameState)) {
			_gameState = gameState;
		} else {
			assert false;
		}
	}

	@Override
	public String getGameState() {
		// TODO Auto-generated method stub
		return _gameState;
	}
	
	private void setWinner(){
		for (int i = 1; i <= PlayerList.size(); i++) {
			Player player = PlayerList.get(i);
			if(!player.getState().equals(StateType.DEATH)){
				winnerId = player.getId();
			}
		}
	}
	
	public int getWinnerId(){
		return winnerId;
	}

	@Override
	public void startGameLogicSchedule() {
		// TODO Auto-generated method stub

		_enviromentTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				enviromentLogic();
			}
		}, 0, 1000);

		_logicTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				updatePlayersLocation();
				logic();
			}
		}, 0, 10);

	}

	private void updatePlayersLocation() {

		for (int i = 0; i < PlayerList.size(); i++) {
			Player player = PlayerList.get(i + 1);
			if (player.getState().equals(StateType.WALK)) {
				if (player.getDir() == Constants.ACTIONCODE_NORTH && player.getY() > Constants.MIN_Y) {
					player.setY(player.getY() - player.getSpeed() / 2);
				} else if (player.getDir() == Constants.ACTIONCODE_SOUTH && player.getY() < Constants.MAX_Y) {
					player.setY(player.getY() + player.getSpeed() / 2);
				} else if (player.getDir() == Constants.ACTIONCODE_EAST && player.getX() < Constants.MAX_X) {
					player.setX(player.getX() + player.getSpeed() / 2);
				} else if (player.getDir() == Constants.ACTIONCODE_WEST && player.getX() > Constants.MIN_X) {
					player.setX(player.getX() - player.getSpeed() / 2);
				}
			}
			// System.out.println(player.getId()+" "+player.getX()+"
			// "+player.getY());
		}

	}

	private void logic() {
		int alive = PlayerList.size();
		for (int i = 1; i <= PlayerList.size(); i++) {
			Player player = PlayerList.get(i);
			if (player.getEnergy() == 0) {
				player.setState(StateType.EXHAUST);
			}
			if (player.getHealth() == 0) {
				player.setState(StateType.DEATH);
			}
			if (player.getState().equals(StateType.DEATH)) {
				alive -= 1;

				if (alive <= 1) {
					setWinner();
					setGameState(Constants.GAME_STATE_OVER);
					
//					System.out.println("GAME OVER");
				}
			}

			
		}
	}

	private void enviromentLogic() {
		for (int i = 1; i <= PlayerList.size(); i++) {
			Player player = PlayerList.get(i);
			player.decreaseOrganHp(1);
			if (player.getEnergy() <= 495) {
				player.setEnergy(player.getEnergy() + 5);
			}
		}
	}

	private void playerAttack(Player player) {
		int targetX = player.getX();
		int targetY = player.getY();

		if (player.getDir() == Constants.ACTIONCODE_NORTH) {
			targetY -= Constants.IMAGE_HEIGHT;
		} else if (player.getDir() == Constants.ACTIONCODE_SOUTH) {
			targetY += Constants.IMAGE_HEIGHT;
		} else if (player.getDir() == Constants.ACTIONCODE_EAST) {
			targetX += Constants.IMAGE_WIDTH;
		} else if (player.getDir() == Constants.ACTIONCODE_WEST) {
			targetX -= Constants.IMAGE_WIDTH;
		}

		for (int i = 1; i <= PlayerList.size(); i++) {
			if (i != player.getId()) {
				Player player2 = PlayerList.get(i);
				if (Math.abs(targetX - player2.getX()) < Constants.IMAGE_WIDTH
						&& Math.abs(targetY - player2.getY()) < Constants.IMAGE_HEIGHT) {
					player2.setEnergy(player2.getEnergy() - 50);
					System.out.println("ATTACK SUCCESS");
				}
			}
		}
		player.setEnergy(player.getEnergy() - 1);
	}

	private void playerSteal(Player player) {
		int targetX = player.getX();
		int targetY = player.getY();

		if (player.getDir() == Constants.ACTIONCODE_NORTH) {
			targetY -= Constants.IMAGE_HEIGHT;
		} else if (player.getDir() == Constants.ACTIONCODE_SOUTH) {
			targetY += Constants.IMAGE_HEIGHT;
		} else if (player.getDir() == Constants.ACTIONCODE_EAST) {
			targetX += Constants.IMAGE_WIDTH;
		} else if (player.getDir() == Constants.ACTIONCODE_WEST) {
			targetX -= Constants.IMAGE_WIDTH;
		}

		for (int i = 1; i <= PlayerList.size(); i++) {
			if (i != player.getId()) {
				Player player2 = PlayerList.get(i);
				if (Math.abs(targetX - player2.getX()) < Constants.IMAGE_WIDTH
						&& Math.abs(targetY - player2.getY()) < Constants.IMAGE_HEIGHT
						&& player2.getState().equals(StateType.EXHAUST)) {
					Organ o = player2.StealedOrgan();
					player.StealOrganIncressHp(o);
//					player2.remove(o);
//					player.addOrgan(o);
					System.out.println("STEAL SUCCESS "+o.getName());
				}
			}
		}
		player.setEnergy(player.getEnergy() - 1);
	}

}
