package DynamicObjectModule.Entities;

import java.awt.Graphics;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Common.Direction;
import Common.StateType;
import DynamicObjectModule.Animation;

public abstract class Sprite {
	public static final int DEFAULT_X = 0;
	public static final int DEFAULT_Y = 0;
	public static final int DEFAULT_TOTAL_HEALTH = 500;
	public static final String DEFAULT_DIRECTION = Direction.RIGHT;

	protected int _id;
	protected int _x;
	protected int _y;
	protected int _totalHealth;
	protected int _health;
	protected String _state;
	protected String _direction;
	protected Map<String, Map<String, Animation>> _animations;
	protected Animation _currentAnimation;
	protected Map<String, String> _packageToDirection;
	protected Map<String, String> _packageToState;

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
		_direction = DEFAULT_DIRECTION;
		_packageToDirection = new HashMap<>();
		_packageToState = new HashMap<>();
		_animations = new HashMap<String, Map<String, Animation>>();
		
		initPackageToDirection();
		initPackageToState();
		
		try {
			loadAnimations();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public abstract void draw(Graphics g);
	
	protected abstract void loadAnimations() throws IOException;
	protected abstract void initPackageToDirection();
	protected abstract void initPackageToState();

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
	
	public String getState() {
		return _state;
	}
	
	public void setState(String state) {
		_state = state;
		
		if (_currentAnimation != null) {
			_currentAnimation.reset();
		}
		
		_currentAnimation = _animations.get(state).get(_direction);
		_currentAnimation.start();
	}
}
