package DynamicObjectModule.Entities;

import java.awt.Graphics;
import java.io.IOException;

import Common.StateType;
import DynamicObjectModule.Transitions.FiniteStateMachines.FiniteStateMachine;
import DynamicObjectModule.Transitions.States.VirtualOrgan.*;

public class VirtualOrgan extends Sprite {
	
	protected String _name;
	protected String _packageName;
	protected VirtualCharacter _owner;

	public VirtualOrgan(String name, String packageName, VirtualCharacter owner) {
		super(0, 0);

		assert (name != null && !name.isEmpty());
		assert (packageName != null && !packageName.isEmpty());
		assert (owner != null);

		_name = name;
		_packageName = packageName;
		_owner = owner;
		_totalHealth = 100;
		_health = _totalHealth;
	}

	public VirtualCharacter getOwner() {
		return _owner;
	}

	@Override
	public void setHealth(int health) {
		super.setHealth(health);

		if (_health == 0) {
			// setState(StateType.DEATH);
		}
	}

	public void setOwner(VirtualCharacter owner) {
		assert (owner != null);
		_owner = owner;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		assert (name != null && !name.isEmpty());
		_name = name;
	}

	@Override
	public int getDirection() {
		assert (false);
		return -1;
	}

	@Override
	public void setDirection(int direction) {
		assert (false);
	}

	@Override
	protected void initPackageToDirectionMap() {
		_packageToDirection.put("Right", Sprite.DEFAULT_DIRECTION);
	}

	@Override
	protected void initPackageToStateMap() {
		_packageToState.put("Death", StateType.DEATH);
	}

	@Override
	protected void initFiniteStateMachine() {
		_fsm = new FiniteStateMachine<VirtualOrgan>(this);
	}

	@Override
	protected void initTransitionTable() {
		_fsm.addTransition(StateType.IDLE, StateType.DEATH);
	}

	@Override
	protected void initStateEntityTranslationTable() {
		_fsm.addStateTranslation(StateType.IDLE, new OrganIdleState(this));
		_fsm.addStateTranslation(StateType.DEATH, new OrganDeathState(this));
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(_currentAnimation.getImage(), _x, _y, null);
	}

	@Override
	protected void loadAnimations() throws IOException {
		loadAnimations("Sprite/VirtualOrgan/" + _packageName);
	}
}
