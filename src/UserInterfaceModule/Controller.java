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
		if (_keyCodeToDirection.containsKey(e.getKeyCode())) {
			_isDirectionKeyPressed = true;
			
			data = GameManager.getInstance().getClientId()+" "+_keyCodeToDirection.get(e.getKeyCode())+" "+StateType.WALK;
			
		}

		if (e.getKeyCode() == KeyEvent.VK_Z) {
			data = GameManager.getInstance().getClientId()+" "+StateType.ATTACK;
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_X) {
			data = GameManager.getInstance().getClientId()+" "+StateType.STEAL;
			
		}
		if(_entity == null) {
			assert false;
		}
		GameManager.getInstance().sendtoTcp(data);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (_keyCodeToDirection.containsKey(e.getKeyCode())) {
			_isDirectionKeyPressed = false;
			
			data = GameManager.getInstance().getClientId()+" "+StateType.IDLE;
		}
		GameManager.getInstance().sendtoTcp(data);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}