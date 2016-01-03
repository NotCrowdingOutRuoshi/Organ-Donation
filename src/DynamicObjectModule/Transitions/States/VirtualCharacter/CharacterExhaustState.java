package DynamicObjectModule.Transitions.States.VirtualCharacter;

import Common.StateType;
import DynamicObjectModule.Entities.VirtualCharacter;
import DynamicObjectModule.Transitions.States.ExhaustState;

public class CharacterExhaustState extends ExhaustState<VirtualCharacter> {

	public CharacterExhaustState(VirtualCharacter sprite) {
		super(sprite);

		_returnState = StateType.IDLE;
	}

	@Override
	public void enter() {
		super.enter();
		_entity.getCurrentAnimation().loop(7);
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

}
