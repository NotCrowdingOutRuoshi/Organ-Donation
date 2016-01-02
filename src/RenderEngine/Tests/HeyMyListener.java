package RenderEngine.Tests;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Common.Constants;
import Common.StateType;
import DynamicObjectModule.Entities.VirtualCharacter;

public class HeyMyListener implements KeyListener {
	private VirtualCharacter _player;

	public HeyMyListener(VirtualCharacter player) {
		_player = player;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("UP");
			_player.setDirection(Constants.ACTIONCODE_NORTH);
			_player.setState(StateType.WALK);
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("DOWN");
			_player.setDirection(Constants.ACTIONCODE_SOUTH);
			_player.setState(StateType.WALK);
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("LEFT");
			_player.setDirection(Constants.ACTIONCODE_WEST);
			_player.setState(StateType.WALK);
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("RIGHT");
			_player.setDirection(Constants.ACTIONCODE_EAST);
			_player.setState(StateType.WALK);
			break;
		case KeyEvent.VK_SPACE:
			_player.setState(StateType.ATTACK);
			break;
		case KeyEvent.VK_Z:
			_player.setState(StateType.STEAL);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			_player.setDirection(Constants.ACTIONCODE_NORTH);
			_player.setState(_player.getCurrentState().getReturnState());
			break;
		case KeyEvent.VK_DOWN:
			_player.setDirection(Constants.ACTIONCODE_SOUTH);
			_player.setState(_player.getCurrentState().getReturnState());
			break;
		case KeyEvent.VK_LEFT:
			_player.setDirection(Constants.ACTIONCODE_WEST);
			_player.setState(_player.getCurrentState().getReturnState());
			break;
		case KeyEvent.VK_RIGHT:
			_player.setDirection(Constants.ACTIONCODE_EAST);
			_player.setState(_player.getCurrentState().getReturnState());
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
