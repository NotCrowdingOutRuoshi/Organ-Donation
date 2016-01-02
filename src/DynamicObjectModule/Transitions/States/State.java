package DynamicObjectModule.Transitions.States;

import DynamicObjectModule.Entities.Sprite;

public abstract class State<EntityType extends Sprite> {
	protected EntityType _entity;

	protected State(EntityType sprite) {
		assert sprite != null;

		_entity = sprite;
	}

	public abstract void enter();

	public abstract void execute();

	public abstract void exit();

	public abstract String getType();
}
