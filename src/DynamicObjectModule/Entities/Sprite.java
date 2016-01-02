package DynamicObjectModule.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import Common.Constants;
import Common.StateType;
import DynamicObjectModule.Animations.Animation;
import DynamicObjectModule.Transitions.FiniteStateMachines.FiniteStateMachine;
import DynamicObjectModule.Transitions.States.State;
import Resources.Resources;

public abstract class Sprite {
	public static final int EMPTY_ID = -1;
	public static final int DEFAULT_X = 0;
	public static final int DEFAULT_Y = 0;
	public static final int DEFAULT_TOTAL_HEALTH = 500;
	public static final int DEFAULT_DIRECTION = Constants.ACTIONCODE_EAST;

	protected int _id;
	protected int _x;
	protected int _y;
	protected int _totalHealth;
	protected int _health;
	protected int _direction;
	protected String _packageName;

	// Animations.
	protected Map<String, Map<Integer, Animation>> _animations;
	protected Animation _currentAnimation;
	protected Map<String, Integer> _packageToDirection;
	protected Map<String, String> _packageToState;

	// Finite state machine.
	protected FiniteStateMachine<?> _fsm;

	public Sprite(int x, int y, String packageName) {
		assert (x >= 0);
		assert (y >= 0);

		_packageName = packageName;
		
		_id = EMPTY_ID;
		_x = x;
		_y = y;
		_totalHealth = DEFAULT_TOTAL_HEALTH;
		_health = _totalHealth;
		_direction = DEFAULT_DIRECTION;
		_packageToDirection = new HashMap<>();
		_packageToState = new HashMap<>();
		_animations = new HashMap<String, Map<Integer, Animation>>();

		initPackageToDirectionMap();
		initPackageToStateMap();
		try {
			loadAnimations();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		initFiniteStateMachine();
		initTransitionTable();
		initStateEntityTranslationTable();
		setState(StateType.IDLE);
	}

	public abstract void draw(Graphics g);

	protected void loadAnimations(String imageResourceRoot, int delay) throws IOException {
		File file = Resources.getResourceFile(imageResourceRoot);

		for (final File statePackage : file.listFiles()) {
			if (_packageToState.containsKey(statePackage.getName())) {
				String currentState = _packageToState.get(statePackage.getName());
				Map<Integer, Animation> directedAnimation = new HashMap<>();

				for (final File directionPackage : statePackage.listFiles()) {
					if (_packageToDirection.containsKey(directionPackage.getName())) {
						int currentDirection = _packageToDirection.get(directionPackage.getName());
						ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();

						for (final File image : directionPackage.listFiles()) {
							images.add(ImageIO.read(image));
						}

						directedAnimation.put(currentDirection, new Animation(images, delay));
					}
				}

				_animations.put(currentState, directedAnimation);
			}
		}
	}

	protected abstract void initPackageToDirectionMap();

	protected abstract void initPackageToStateMap();

	protected abstract void loadAnimations() throws IOException;

	protected abstract void initFiniteStateMachine();

	protected abstract void initTransitionTable();

	protected abstract void initStateEntityTranslationTable();

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
		if (health <= 0) {
			_health = 0;
		}
		else {
			_health = health;
		}
	}

	public int getTotalHealth() {
		return _totalHealth;
	}

	public String getState() {
		return _fsm.getCurrentState().getType();
	}

	public boolean setState(String state) {
		assertStates(state);
		
		if (_fsm.setState(state)) {
			_fsm.executeState();
			return true;
		}

		updateAnimation();
		return false;
	}

	public State<?> getCurrentState() {
		return _fsm.getCurrentState();
	}

	public int getDirection() {
		return _direction;
	}

	public void setDirection(int direction) {
		_direction = direction;

		updateAnimation();
	}

	public Animation getCurrentAnimation() {
		return _currentAnimation;
	}

	public void setAnimation(String stateType, int direction) {
		_currentAnimation = _animations.get(stateType).get(_direction);
	}

	public void updateAnimation() {
		if (_currentAnimation != null && _currentAnimation.isStopped()) {
			String returnState = _fsm.getCurrentState().getReturnState();
			if (returnState != StateType.EMPTY) {
				_currentAnimation.reset();
				_fsm.setState(returnState);
			}
		}
	}

	private void assertStates(String stateType) {		
		assert (stateType.equals(StateType.IDLE) || stateType.equals(StateType.WALK) || stateType.equals(StateType.ATTACK)
				|| stateType.equals(StateType.STEAL) || stateType.equals(StateType.EXHAUST) || stateType.equals(StateType.DEATH));
	}
}
