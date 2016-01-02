package DynamicObjectModule.Transitions.States.VirtualCharacter;

import Common.StateType;
import DynamicObjectModule.Entities.VirtualCharacter;
import DynamicObjectModule.Transitions.States.DeathState;

public class CharacterDeathState extends DeathState<VirtualCharacter> {

	public CharacterDeathState(VirtualCharacter sprite) {
		super(sprite);

		_returnState = StateType.EMPTY;
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

}
