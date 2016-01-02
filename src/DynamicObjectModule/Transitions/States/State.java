package DynamicObjectModule.Transitions.States;

import DynamicObjectModule.Entities.Sprite;

public abstract class State<EntityType extends Sprite> {
	protected EntityType _entity;
	protected String _returnState;

	protected State(EntityType sprite) {
		assert sprite != null;

		_entity = sprite;
	}

	public void enter() {
		_entity.setAnimation(getType(), _entity.getDirection());
		_entity.getCurrentAnimation().start();
	}

	public abstract void execute();

	public abstract void exit();
	
	public String getReturnState() {
		assert (_returnState != null && _returnState != "");
		return _returnState;
	}

	public abstract String getType();
}
