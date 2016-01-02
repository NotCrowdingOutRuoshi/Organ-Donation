package DynamicObjectModule.Transitions.States.VirtualCharacter;

import Common.StateType;
import DynamicObjectModule.Entities.VirtualCharacter;
import DynamicObjectModule.Transitions.States.WalkState;

public class CharacterWalkState extends WalkState<VirtualCharacter> {

	public CharacterWalkState(VirtualCharacter sprite) {
		super(sprite);

		_returnState = StateType.IDLE;
	}

	@Override
	public void enter() {
		_entity.setAnimation(getType(), _entity.getDirection());
		_entity.getCurrentAnimation().loop();
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

}
