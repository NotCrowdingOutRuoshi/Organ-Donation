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
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
