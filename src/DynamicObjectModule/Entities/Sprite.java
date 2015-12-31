package DynamicObjectModule.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import Common.Direction;
import Common.StateType;
import DynamicObjectModule.Animations.Animation;
import Resources.Resources;

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
		
		initPackageToDirectionMap();
		initPackageToStateMap();
	}

	public abstract void draw(Graphics g);
	
	protected void loadAnimations(String imageResourceRoot) throws IOException {
		File file = Resources.getResourceFile(imageResourceRoot);

		for (final File statePackage : file.listFiles()) {
			if (_packageToState.containsKey(statePackage.getName())) {
				String currentState = _packageToState.get(statePackage.getName());
				Map<String, Animation> directedAnimation = new HashMap<>();

				for (final File directionPackage : statePackage.listFiles()) {
					if (_packageToDirection.containsKey(directionPackage.getName())) {
						String currentDirection = _packageToDirection.get(directionPackage.getName());
						ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();

						for (final File image : directionPackage.listFiles()) {
							images.add(ImageIO.read(image));
						}

						directedAnimation.put(currentDirection, new Animation(images, 40));
					}
				}

				_animations.put(currentState, directedAnimation);
			}
		}
	}
	protected abstract void initPackageToDirectionMap();
	protected abstract void initPackageToStateMap();

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
		
		updateAnimation();
	}
	
	public String getDirection() {
		return _direction;
	}

	public void setDirection(String direction) {
		_direction = direction;
		
		updateAnimation();
	}
	
	private void updateAnimation() {
		if (_currentAnimation != null) {
			_currentAnimation.reset();
		}
		
		_currentAnimation = _animations.get(_state).get(_direction);
		_currentAnimation.start();
	}
}
