package DynamicObjectModule.Transitions.States;

import Common.StateType;
import DynamicObjectModule.Entities.Sprite;

public class AttackState extends State {
	public AttackState(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub

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
		return StateType.ATTACK;
	}
}
