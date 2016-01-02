package DynamicObjectModule.Transitions.States.VirtualCharacter;

import Common.StateType;
import DynamicObjectModule.Entities.VirtualCharacter;
import DynamicObjectModule.Transitions.States.AttackState;

public class CharacterAttackState extends AttackState<VirtualCharacter> {

	public CharacterAttackState(VirtualCharacter sprite) {
		super(sprite);
		
		_returnState = StateType.IDLE;
	}

	@Override
	public void enter() {
		_entity.setAnimation(getType(), _entity.getDirection());
		_entity.getCurrentAnimation().loop(1);
		_entity.getCurrentAnimation().start();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
