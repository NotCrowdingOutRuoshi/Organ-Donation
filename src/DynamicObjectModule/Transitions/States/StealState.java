package DynamicObjectModule.Transitions.States;

import Common.StateType;
import DynamicObjectModule.Entities.Sprite;

public abstract class StealState<EntityType extends Sprite> extends State<EntityType> {
	protected StealState(EntityType sprite) {
		super(sprite);
	}

	@Override
	public String getType() {
		return StateType.STEAL;
	}

}
