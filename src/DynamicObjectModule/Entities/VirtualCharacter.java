package DynamicObjectModule.Entities;

import java.util.ArrayList;

import java.awt.Graphics;
import java.io.IOException;

import Common.Constants;
import Common.StateType;

public class VirtualCharacter extends Sprite {
	public static final int DEFAULT_SPEED = 0;

	protected int _organTotalHealth;
	protected int _speed;
	protected ArrayList<VirtualOrgan> _organs;

	public VirtualCharacter(int id, int x, int y, int direction, int speed) {
		super(x, y);

		assert (id >= 0);
		assert (speed >= 0);

		_id = id;
		_direction = direction;
		_speed = speed;
		_organs = new ArrayList<VirtualOrgan>();
		initOrgans();

		_organTotalHealth = 0;
		for (VirtualOrgan organ : _organs) {
			_organTotalHealth += organ.getTotalHealth();
		}
		
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
		_packageToDirection.put("Left", Constants.ACTIONCODE_WEST);
		_packageToDirection.put("Right", Constants.ACTIONCODE_EAST);
		_packageToDirection.put("Up", Constants.ACTIONCODE_NORTH);
		_packageToDirection.put("Down", Constants.ACTIONCODE_SOUTH);
	}

	@Override
	protected void initPackageToStateMap() {
		_packageToState.put("Idle", StateType.IDLE);
		_packageToState.put("Walk", StateType.WALK);
		_packageToState.put("Attack", StateType.ATTACK);
		_packageToState.put("Steal", StateType.STEAL);
		_packageToState.put("Exhaust", StateType.EXHAUST);
	}
	
	@Override
	public int getHealth() {
		int result = 0;
		
		for (VirtualOrgan organ : _organs) {
			result += organ.getHealth();
		}
		
		return result;
	}
	
	@Override
	public void setHealth(int health) {
		assert (false);
	}
	
	public int getEnergy() {
		return _health;
	}
	
	public void setEnergy(int energy) {
		assert (energy >= 0);
		
		_health = energy;
	}

	public int getSpeed() {
		return _speed;
	}

	public void setSpeed(int speed) {
		assert (speed >= 0);
		_speed = speed;
	}
	
	public VirtualOrgan findOrgan(String name) {
		for (VirtualOrgan virtualOrgan : _organs) {
			if (virtualOrgan.getName() == name) {
				return virtualOrgan;
			}
		}
		
		return null;
	}
	
	public void updateOrganList(ArrayList<VirtualOrgan> organs) {
		assert (organs != null);
		
		_organs = organs;
	}
	
	private void initOrgans() {
		_organs.add(new VirtualOrgan("heart", this));
		_organs.add(new VirtualOrgan("liver", this));
		_organs.add(new VirtualOrgan("lung", this));
		_organs.add(new VirtualOrgan("pancreas", this));
		_organs.add(new VirtualOrgan("kidney", this));
		_organs.add(new VirtualOrgan("small intestine", this));
		_organs.add(new VirtualOrgan("large intestine", this));
	}
}
