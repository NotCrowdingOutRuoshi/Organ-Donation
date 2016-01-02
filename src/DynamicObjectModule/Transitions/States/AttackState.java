package DynamicObjectModule.Transitions.States;

import Common.StateType;
import DynamicObjectModule.Entities.Sprite;

public abstract class AttackState<EntityType extends Sprite> extends State<EntityType> {
	protected AttackState(EntityType sprite) {
		super(sprite);
	}

	@Override
	public String getType() {
		return StateType.ATTACK;
	}
}
