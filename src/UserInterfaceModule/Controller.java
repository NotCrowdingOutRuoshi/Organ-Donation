package UserInterfaceModule;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import Common.Constants;
import Common.StateType;
import DynamicObjectModule.Entities.Sprite;

public class Controller implements KeyListener {
	protected static Map<Integer, Integer> _keyCodeToDirection = new HashMap<Integer, Integer>() {
		{
			put(KeyEvent.VK_UP, Constants.ACTIONCODE_NORTH);
			put(KeyEvent.VK_DOWN, Constants.ACTIONCODE_SOUTH);
			put(KeyEvent.VK_LEFT, Constants.ACTIONCODE_WEST);
			put(KeyEvent.VK_RIGHT, Constants.ACTIONCODE_EAST);
		}
	};

	protected Sprite _entity;
	protected boolean _isDirectionKeyPressed = false;
	protected String data;

	protected Controller(Sprite sprite) {
		_entity = sprite;
		_isDirectionKeyPressed = false;
	}

	public void setEntity(Sprite sprite) {
		_entity = sprite;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		String state = "";
		if (_keyCodeToDirection.containsKey(e.getKeyCode())) {
			_isDirectionKeyPressed = true;
			state = StateType.WALK;
			data = GameManager.getInstance().getClientId() + " " + _keyCodeToDirection.get(e.getKeyCode()) + " "
					+ state;
		}

		if (e.getKeyCode() == KeyEvent.VK_Z) {
			state = StateType.ATTACK;
			data = GameManager.getInstance().getClientId() + " " + state;
		}

		if (e.getKeyCode() == KeyEvent.VK_X) {
			state = StateType.STEAL;
			data = GameManager.getInstance().getClientId() + " " + state;

		}
		if (_entity == null) {
			assert false;
		}
		send(state);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		String state = "";
		if (_keyCodeToDirection.containsKey(e.getKeyCode())) {
			_isDirectionKeyPressed = false;
			state = StateType.IDLE;
			data = GameManager.getInstance().getClientId() + " " + state;
		}
		if (_entity == null) {
			assert false;
		}
		send(state);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void send(String state) {
		if (!state.equals("")) {
			if (_entity.setState(state)) {
				GameManager.getInstance().sendtoTcp(data);
			}
		}
	}

}