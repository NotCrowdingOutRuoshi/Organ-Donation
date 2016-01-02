package DynamicObjectModule.Entities;

import java.util.ArrayList;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import Common.Constants;
import Common.StateType;
import DynamicObjectModule.Transitions.FiniteStateMachines.FiniteStateMachine;
import DynamicObjectModule.Transitions.States.VirtualCharacter.*;

public class VirtualCharacter extends Sprite {
	public static final int DEFAULT_SPEED = 0;

	protected int _organTotalHealth;
	protected int _speed;
	protected ArrayList<VirtualOrgan> _organs;

	public VirtualCharacter(int id, int x, int y, int direction, int speed) {
		super(x, y, null);

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

	// For test only.
//	public static void main(String[] args) {
//		VirtualCharacter player = new VirtualCharacter(0, 0, 0, Sprite.DEFAULT_DIRECTION, 0);
//		State s = player.getCurrentState();
//		System.out.println(s.toString());
//
//		player.setState(StateType.ATTACK);
//		s = player.getCurrentState();
//		System.out.println(s.toString());
//
//		player.setState(StateType.STEAL);
//		s = player.getCurrentState();
//		System.out.println(s.toString());
//	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(3.0f));
		g.setColor(Color.red);
		
		if(getEnergy()>500){
			g.drawLine(_x, _y-20, _x+Constants.IMAGE_WIDTH, _y-20);
		}
		else{
			g.drawLine(_x, _y-20, _x+(int) (Constants.IMAGE_WIDTH*((double)getHealth()/500)), _y-20);
		}
		System.out.println(getEnergy());
		g.setColor(Color.green);
		g.drawLine(_x, _y-10, _x+(int) (Constants.IMAGE_WIDTH*((double)getEnergy()/500)), _y-10);
		g.drawImage(_currentAnimation.getImage(), _x, _y, null);
		
		for (VirtualOrgan organ : _organs) {
			organ.draw(g);
		}
		
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
		_packageToState.put("Death", StateType.DEATH);
	}

	@Override
	protected void loadAnimations() throws IOException {
		loadAnimations("Sprite/VirtualCharacter");
	}

	@Override
	protected void initFiniteStateMachine() {
		_fsm = new FiniteStateMachine<VirtualCharacter>(this);
	}

	@Override
	protected void initTransitionTable() {
		//_fsm.addTransition(StateType.IDLE, StateType.IDLE);
		_fsm.addTransition(StateType.IDLE, StateType.WALK);
		_fsm.addTransition(StateType.IDLE, StateType.ATTACK);
		_fsm.addTransition(StateType.IDLE, StateType.STEAL);
		_fsm.addTransition(StateType.IDLE, StateType.EXHAUST);

		_fsm.addTransition(StateType.WALK, StateType.WALK);
		_fsm.addTransition(StateType.WALK, StateType.IDLE);
		_fsm.addTransition(StateType.WALK, StateType.ATTACK);
		_fsm.addTransition(StateType.WALK, StateType.STEAL);
		_fsm.addTransition(StateType.WALK, StateType.EXHAUST);

		_fsm.addTransition(StateType.ATTACK, StateType.IDLE);
		_fsm.addTransition(StateType.STEAL, StateType.IDLE);
		_fsm.addTransition(StateType.EXHAUST, StateType.IDLE);

		_fsm.addTransition(StateType.IDLE, StateType.DEATH);
		_fsm.addTransition(StateType.WALK, StateType.DEATH);
		_fsm.addTransition(StateType.ATTACK, StateType.DEATH);
		_fsm.addTransition(StateType.STEAL, StateType.DEATH);
		_fsm.addTransition(StateType.EXHAUST, StateType.DEATH);
	}

	@Override
	protected void initStateEntityTranslationTable() {
		_fsm.addStateTranslation(StateType.IDLE, new CharacterIdleState(this));
		_fsm.addStateTranslation(StateType.WALK, new CharacterWalkState(this));
		_fsm.addStateTranslation(StateType.ATTACK, new CharacterAttackState(this));
		_fsm.addStateTranslation(StateType.STEAL, new CharacterStealState(this));
		_fsm.addStateTranslation(StateType.EXHAUST, new CharacterExhaustState(this));
		_fsm.addStateTranslation(StateType.DEATH, new CharacterDeathState(this));
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
			if (virtualOrgan.getName().equals(name)) {
				return virtualOrgan;
			}
		}

		return null;
	}
	
	public boolean removeOrgan(VirtualOrgan organ) {
		return _organs.remove(organ);
	}

	public void updateOrganList(ArrayList<VirtualOrgan> organs) {
		assert (organs != null);

		_organs = organs;
	}

	private void initOrgans() {
		// _organs.add(new VirtualOrgan("heart", this));
		// _organs.add(new VirtualOrgan("liver", this));
		// _organs.add(new VirtualOrgan("lung", this));
		// _organs.add(new VirtualOrgan("pancreas", this));
		// _organs.add(new VirtualOrgan("kidney", this));
		// _organs.add(new VirtualOrgan("small intestine", this));
		// _organs.add(new VirtualOrgan("large intestine", this));
	}
}
