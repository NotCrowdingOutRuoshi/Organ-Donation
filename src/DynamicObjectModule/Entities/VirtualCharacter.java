package DynamicObjectModule.Entities;

import java.awt.Graphics;
import java.io.IOException;

import Common.Direction;
import Common.StateType;

public class VirtualCharacter extends Sprite {
	public static final int DEFAULT_SPEED = 0;

	private int _speed;

	public VirtualCharacter(int id, int x, int y, String direction, int speed) {
		super(id, x, y);

		assert (speed >= 0);

		_direction = direction;
		_speed = speed;
		
		try {
			loadAnimations("Sprite/VirtualCharacter");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(_currentAnimation.getImage(), _x, _y, null);
		_currentAnimation.update();
	}

	@Override
	protected void initPackageToDirectionMap() {
		_packageToDirection.put("Left", Direction.LEFT);
		_packageToDirection.put("Right", Direction.RIGHT);
		_packageToDirection.put("Up", Direction.UP);
		_packageToDirection.put("Down", Direction.DOWN);
	}

	@Override
	protected void initPackageToStateMap() {
		_packageToState.put("Idle", StateType.IDLE);
		_packageToState.put("Walk", StateType.WALK);
		_packageToState.put("Attack", StateType.ATTACK);
		_packageToState.put("Steal", StateType.STEAL);
		_packageToState.put("Exhaust", StateType.EXHAUST);
	}

	public int getSpeed() {
		return _speed;
	}

	public void setSpeed(int speed) {
		assert (speed >= 0);
		_speed = speed;
	}
}
