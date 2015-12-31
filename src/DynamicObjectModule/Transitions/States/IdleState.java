package DynamicObjectModule.Transitions.States;

import Common.StateType;
import DynamicObjectModule.Entities.Sprite;

public class IdleState extends State {
	public IdleState(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void enter() {
		
	}

	@Override
	public void execute() {
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getType() {
		return StateType.IDLE;
	}
}
