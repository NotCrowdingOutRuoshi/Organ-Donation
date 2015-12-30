package DynamicObjectModule.Entities;

import java.awt.Graphics;
import java.util.Map;

import Common.StateType;
import DynamicObjectModule.Animation;

public abstract class Sprite {
	public static final int DEFAULT_X = 0;
	public static final int DEFAULT_Y = 0;
	public static final int DEFAULT_TOTAL_HEALTH = 500;

	protected int _id;
	protected int _x;
	protected int _y;
	protected int _totalHealth;
	protected int _health;
	protected String _state;
	protected Map<StateType, Animation> _animations;

	public Sprite(int id, int x, int y) {
		assert (id >= 0);
		assert (x >= 0);
		assert (y >= 0);

		_id = id;
		_x = x;
		_y = y;
		_totalHealth = DEFAULT_TOTAL_HEALTH;
		_health = _totalHealth;
		_state = StateType.EMPTY;
		
		loadAnimations();
	}

	public abstract void draw(Graphics g);
	
	protected abstract void loadAnimations();

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		assert (id >= 0);
		_id = id;
	}

	public int getX() {
		return _x;
	}

	public void setX(int x) {
		assert x >= 0;
		_x = x;
	}

	public int getY() {
		return _y;
	}

	public void setY(int y) {
		assert y >= 0;
		_y = y;
	}
	
	public int getHealth() {
		return _health;
	}
	
	public void setHealth(int health) {
		assert (health >= 0 && health <= _totalHealth);
		
		_health = health;
	}
	
	public int getTotalHealth() {
		return _totalHealth;
	}
}
