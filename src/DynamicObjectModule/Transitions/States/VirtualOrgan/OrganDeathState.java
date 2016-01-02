package DynamicObjectModule.Transitions.States.VirtualOrgan;

import Common.StateType;
import DynamicObjectModule.Entities.VirtualOrgan;
import DynamicObjectModule.Transitions.States.DeathState;

public class OrganDeathState extends DeathState<VirtualOrgan> {

	public OrganDeathState(VirtualOrgan sprite) {
		super(sprite);
		_returnState = StateType.EMPTY;
	}
	
	@Override
	public void enter() {
		_entity.setX(_entity.getOwner().getX());
		_entity.setY(_entity.getOwner().getY());
		
		_entity.setAnimation(getType(), _entity.getDirection());
		_entity.getCurrentAnimation().loop(10);
	};

	@Override
	public void execute() {
		_entity.setY(_entity.getY() + 10);
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
