package DynamicObjectModule.Entities;

import java.awt.Graphics;

import DynamicObjectModule.Transitions.FiniteStateMachines.FiniteStateMachine;

public class VirtualOrgan extends Sprite {
	protected String _name;
	protected VirtualCharacter _owner;

	public VirtualOrgan(String name, VirtualCharacter owner) {
		super(0, 0);

		assert (name != null && !name.isEmpty());
		assert (owner != null);

		_name = name;
		_owner = owner;
		_totalHealth = 100;
		_health = _totalHealth;
	}

	@Override
	public void draw(Graphics g) {

	}

	public VirtualCharacter getOwner() {
		return _owner;
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
	protected void initPackageToDirectionMap() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initPackageToStateMap() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void loadAnimations() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initFiniteStateMachine() {
		_fsm = new FiniteStateMachine<VirtualOrgan>(this);
	}

	@Override
	protected void initTransitionTable() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initStateEntityTranslationTable() {
		// TODO Auto-generated method stub

	}
}
