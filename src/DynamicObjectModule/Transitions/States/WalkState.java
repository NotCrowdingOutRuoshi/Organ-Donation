package DynamicObjectModule.Transitions.States;

import Common.StateType;
import DynamicObjectModule.Entities.Sprite;

public abstract class WalkState<EntityType extends Sprite> extends State<EntityType> {
	private int _direction;

	protected WalkState(EntityType sprite) {
		super(sprite);
	}

	@Override
	public String getType() {
		return StateType.WALK;
	}
}
