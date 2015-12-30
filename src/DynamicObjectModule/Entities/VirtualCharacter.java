package DynamicObjectModule.Entities;

import java.awt.Graphics;

import Common.Direction;

public class VirtualCharacter extends Sprite {
	public static final int DEFAULT_SPEED = 0;
	public static final String DEFAULT_DIRECTION = Direction.RIGHT;

	private int _speed;
	private String _direction;

	public VirtualCharacter(int id, int x, int y, String direction, int speed) {
		super(id, x, y);

		assert (speed >= 0);

		_direction = direction;
		_speed = speed;
	}

	@Override
	public void draw(Graphics g) {

	}
	
	@Override
	protected void loadAnimations() {
		// TODO Auto-generated method stub
		
	}

	public String getDirection() {
		return _direction;
	}

	public void setDirection(String direction) {
		_direction = direction;
	}

	public int getSpeed() {
		return _speed;
	}

	public void setSpeed(int speed) {
		assert (speed >= 0);
		_speed = speed;
	}
}
