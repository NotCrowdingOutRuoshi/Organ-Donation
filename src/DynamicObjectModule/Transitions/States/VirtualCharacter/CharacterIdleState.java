package DynamicObjectModule.Transitions.States.VirtualCharacter;

import Common.StateType;
import DynamicObjectModule.Entities.VirtualCharacter;
import DynamicObjectModule.Transitions.States.IdleState;

public class CharacterIdleState extends IdleState<VirtualCharacter> {

	public CharacterIdleState(VirtualCharacter sprite) {
		super(sprite);

		_returnState = StateType.IDLE;
	}

	@Override
	public void enter() {
		super.enter();
		_entity.getCurrentAnimation().loop();
	}

}
