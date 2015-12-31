package CentralizedDataCenter;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import CentralizedDataCenter.Entities.Organ;
import CentralizedDataCenter.Entities.Player;
import Common.Constants;
import Common.StateType;
import Common.Interfaces.ICentralizedDataCenter;

public class CDC implements ICentralizedDataCenter {
	Map<Integer, Player> PlayerList;
	Map<Integer, Organ> OrganList;
	private String _gameState = Constants.GAME_STATE_MENU;
	private Vector<String> _gameStateList;
	private Timer _enviromentTimer;
	private Timer _logicTimer;

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
		PlayerList.put(id, player);
	}

	public void updateDir(int id, int dir) {
		Player player = PlayerList.get(id);
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

	public void GameLogicThread() {

		Thread thread = new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					;
					enviromentLogic();
					logic();
				}

			}
		};
		thread.start();
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

	@Override
	public void startGameLogicSchedule() {
		// TODO Auto-generated method stub
		_enviromentTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				enviromentLogic();
			}
		}, 1000);

		_logicTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				updatePlayersLocation();
				logic();
			}
		}, 10);

	}

	private void updatePlayersLocation() {

		for (int i = 0; i < PlayerList.size(); i++) {
			Player player = PlayerList.get(i + 1);
			if (player.getState().equals(StateType.WALK)) {
				if (player.getDir() == Constants.ACTIONCODE_NORTH) {
					player.setY(player.getY() - player.getSpeed() / 2);
				} else if (player.getDir() == Constants.ACTIONCODE_SOUTH) {
					player.setY(player.getY() + player.getSpeed() / 2);
				} else if (player.getDir() == Constants.ACTIONCODE_EAST) {
					player.setX(player.getX() + player.getSpeed() / 2);
				} else if (player.getDir() == Constants.ACTIONCODE_WEST) {
					player.setX(player.getX() - player.getSpeed() / 2);
				}
			}
		}

	}

	private void logic() {
		int alive = PlayerList.size();
		for (int i = 1; i <= PlayerList.size(); i++) {
			Player player = PlayerList.get(i);
			if (player.getHealth() == 0) {
				player.setState(StateType.EXHAUST);
			}
			if (player.getEnergy() == 0) {
				player.setState(StateType.DEATH);
			}
			if (player.getState().equals(StateType.DEATH)) {
				alive -= 1;

				if (alive <= 1) {
					setGameState(Constants.GAME_STATE_OVER);
				}
			}
		}
	}

	private void enviromentLogic() {
		for (int i = 1; i <= PlayerList.size(); i++) {
			Player player = PlayerList.get(i);
			player.decreaseOrganHp(20);
		}
	}

	private void playerAttack(Player player) {
		int targetX = player.getX();
		int targetY = player.getY();

		if (player.getDir() == Constants.ACTIONCODE_NORTH) {
			targetY -= 1;
		} else if (player.getDir() == Constants.ACTIONCODE_SOUTH) {
			targetY += 1;
		} else if (player.getDir() == Constants.ACTIONCODE_EAST) {
			targetX += 1;
		} else if (player.getDir() == Constants.ACTIONCODE_WEST) {
			targetX -= 1;
		}

		for (int i = 1; i <= PlayerList.size(); i++) {
			if (i != player.getId()) {
				Player player2 = PlayerList.get(i);
				if (targetX == player2.getX() && targetY == player2.getY()) {
					player2.setHealth(player2.getHealth() - 50);
				}
			}
		}
		player.setHealth(player.getHealth() - 5);
	}

	private void playerSteal(Player player) {
		int targetX = player.getX();
		int targetY = player.getY();

		if (player.getDir() == Constants.ACTIONCODE_NORTH) {
			targetY -= 1;
		} else if (player.getDir() == Constants.ACTIONCODE_SOUTH) {
			targetY += 1;
		} else if (player.getDir() == Constants.ACTIONCODE_EAST) {
			targetX += 1;
		} else if (player.getDir() == Constants.ACTIONCODE_WEST) {
			targetX -= 1;
		}

		for (int i = 1; i <= PlayerList.size(); i++) {
			if (i != player.getId()) {
				Player player2 = PlayerList.get(i);
				if (targetX == player2.getX() && targetY == player2.getY()
						&& player2.getState().equals(StateType.EXHAUST)) {
					Organ o = player2.StealedOrgan();
					player2.remove(o);
					player.addOrgan(o);
				}
			}
		}
	}

}
