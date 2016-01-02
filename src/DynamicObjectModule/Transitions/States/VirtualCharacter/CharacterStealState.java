package DynamicObjectModule.Transitions.States.VirtualCharacter;

import Common.StateType;
import DynamicObjectModule.Entities.VirtualCharacter;
import DynamicObjectModule.Transitions.States.StealState;

public class CharacterStealState extends StealState<VirtualCharacter> {

	public CharacterStealState(VirtualCharacter sprite) {
		super(sprite);

		_returnState = StateType.IDLE;
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

}
