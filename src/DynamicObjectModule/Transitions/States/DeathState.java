package DynamicObjectModule.Transitions.States;

import Common.StateType;
import DynamicObjectModule.Entities.Sprite;

public abstract class DeathState<EntityType extends Sprite> extends State<EntityType> {

	protected DeathState(EntityType sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getType() {
		return StateType.DEATH;
	}
	
}
