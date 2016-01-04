package DynamicObjectModule.Entities;

import java.awt.Graphics;
import java.io.IOException;

import Common.Constants;
import Common.StateType;
import DynamicObjectModule.Transitions.FiniteStateMachines.FiniteStateMachine;
import DynamicObjectModule.Transitions.States.VirtualOrgan.*;
import UserInterfaceModule.GameManager;

public class VirtualOrgan extends Sprite {

	protected String _name;
	protected VirtualCharacter _owner;

	public VirtualOrgan(String name, String packageName, VirtualCharacter owner) {
		super(0, 0, packageName);

		assert (name != null && !name.isEmpty());
		assert (owner != null);

		_name = name;
		_owner = owner;
		_totalHealth = 100;
		_health = _totalHealth;
	}

	public VirtualCharacter getOwner() {
		return _owner;
	}
	
	@Override
	public void setY(int y) {
		_y = y;
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
		if (_fsm.getCurrentState().getType() == StateType.DEATH) {
			int mainCharacterId = GameManager.getInstance().getClientId();
			Sprite mainCharacter = GameManager.getInstance().getDOM().findSprite(mainCharacterId);
			
			g.drawImage(_currentAnimation.getImage(), _x-mainCharacter.getX()+(Constants.WINDOW_WIDTH/2), _y-mainCharacter.getY()+(Constants.WINDOW_HEIGHT/2), null);
		}
		
		_fsm.executeState();
	}

	@Override
	protected void loadAnimations() throws IOException {
		loadAnimations("Sprite/VirtualOrgan/" + _packageName, 60);
	}
}
