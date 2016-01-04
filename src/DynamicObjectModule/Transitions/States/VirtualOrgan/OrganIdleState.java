package DynamicObjectModule.Transitions.States.VirtualOrgan;

import Common.StateType;
import DynamicObjectModule.Entities.VirtualOrgan;
import DynamicObjectModule.Transitions.States.IdleState;

public class OrganIdleState extends IdleState<VirtualOrgan> {

	public OrganIdleState(VirtualOrgan sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void enter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute() {
		if (_entity.getHealth() == 0) {
			_entity.setState(StateType.DEATH);
		}
		else {
			if (_entity.getState() == StateType.DEATH && _entity.getHealth() > 0) {
				_entity.setState(StateType.IDLE);
			}
		}
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
