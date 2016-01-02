package DynamicObjectModule.Transitions.States;

import DynamicObjectModule.Entities.Sprite;

public abstract class ExhaustState<EntityType extends Sprite> extends State<EntityType> {

	protected ExhaustState(EntityType sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}


}
