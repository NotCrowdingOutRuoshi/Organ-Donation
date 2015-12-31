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
		// _packageToDirection.put("Up", Direction.UP);
		// _packageToDirection.put("Down", Direction.DOWN);
	}

	@Override
	protected void initPackageToState() {
		_packageToState.put("Idle", StateType.IDLE);
	}

	@Override
	protected void loadAnimations() throws IOException {
		File file = Resources.getResourceFile("Sprite");
		Map<String, Animation> directedAnimation = new HashMap<>();

		for (final File statePackage : file.listFiles()) {
			if (_packageToState.containsKey(statePackage.getName())) {
				for (final File directionPackage : statePackage.listFiles()) {
					ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();

					for (final File image : directionPackage.listFiles()) {
						images.add(ImageIO.read(image));
					}

					if (_packageToDirection.containsKey(directionPackage.getName())) {
						directedAnimation.put(_packageToDirection.get(directionPackage.getName()),
								new Animation(images, 10));
					}
				}
			}
		}

		_animations.put(StateType.IDLE, directedAnimation);
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
