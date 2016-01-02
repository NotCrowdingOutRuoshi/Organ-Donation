package DynamicObjectModule.Transitions.States;

import DynamicObjectModule.Entities.Sprite;

public abstract class DeathState<EntityType extends Sprite> extends State<EntityType> {

	protected DeathState(EntityType sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}

}
