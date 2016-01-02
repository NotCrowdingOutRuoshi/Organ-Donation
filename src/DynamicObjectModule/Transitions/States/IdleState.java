package DynamicObjectModule.Transitions.States;

import Common.StateType;
import DynamicObjectModule.Entities.Sprite;

public abstract class IdleState<EntityType extends Sprite> extends State<EntityType> {
	protected IdleState(EntityType sprite) {
		super(sprite);
	}

	@Override
	public String getType() {
		return StateType.IDLE;
	}
}
