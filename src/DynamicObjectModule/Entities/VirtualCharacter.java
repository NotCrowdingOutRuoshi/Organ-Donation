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
import DynamicObjectModule.Animation;
import Resources.Resources;

public class VirtualCharacter extends Sprite {
	public static final int DEFAULT_SPEED = 0;

	private int _speed;

	public VirtualCharacter(int id, int x, int y, String direction, int speed) {
		super(id, x, y);

		assert (speed >= 0);

		_direction = direction;
		_speed = speed;

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(_currentAnimation.getImage(), _x, _y, null);
		_currentAnimation.update();
	}

	@Override
	protected void initPackageToDirection() {
		_packageToDirection.put("Left", Direction.LEFT);
		_packageToDirection.put("Right", Direction.RIGHT);
		_packageToDirection.put("Up", Direction.UP);
		_packageToDirection.put("Down", Direction.DOWN);
	}

	@Override
	protected void initPackageToState() {
		_packageToState.put("Idle", StateType.IDLE);
		_packageToState.put("Walk", StateType.WALK);
		_packageToState.put("Attack", StateType.ATTACK);
		_packageToState.put("Steal", StateType.STEAL);
		_packageToState.put("Exhaust", StateType.EXHAUST);
	}

	@Override
	protected void loadAnimations() throws IOException {
		File file = Resources.getResourceFile("Sprite");

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

	public int getSpeed() {
		return _speed;
	}

	public void setSpeed(int speed) {
		assert (speed >= 0);
		_speed = speed;
	}
}
