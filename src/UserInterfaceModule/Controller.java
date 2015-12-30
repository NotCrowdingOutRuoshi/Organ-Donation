package UserInterfaceModule;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import CentralizedDataCenter.Entities.Sprite;
import CentralizedDataCenter.Entities.StateType;
import Common.Direction;


public class Controller implements KeyListener {
	protected static Map<Integer, Direction> _keyCodeToDirection = new HashMap<Integer, Direction>() {
		{
			put(KeyEvent.VK_UP, Direction.UP);
			put(KeyEvent.VK_DOWN, Direction.DOWN);
			put(KeyEvent.VK_LEFT, Direction.LEFT);
			put(KeyEvent.VK_RIGHT, Direction.RIGHT);
		}
	};

	protected Sprite _entity;
	protected boolean _isDirectionKeyPressed = false;
	protected String data;
	protected Controller(Sprite sprite) {
		_entity = sprite;
		_isDirectionKeyPressed = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (_keyCodeToDirection.containsKey(e.getKeyCode())) {
			_isDirectionKeyPressed = true;

			data = _entity.getID()+" "+_keyCodeToDirection.get(e.getKeyCode())+" "+StateType.WALK;
			
		}

		if (e.getKeyCode() == KeyEvent.VK_Z) {
			data = _entity.getID()+" "+StateType.ATTACK;
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_X) {
			data = _entity.getID()+" "+StateType.STEAL;
			
		}
		
		GameManager.getInstance().sendtoTcp(data);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (_keyCodeToDirection.containsKey(e.getKeyCode())) {
			_isDirectionKeyPressed = false;
			
			data = _entity.getID()+" "+StateType.IDLE;
		}
		GameManager.getInstance().sendtoTcp(data);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}