package DynamicObjectModule.Transitions.States;

import Common.StateType;
import DynamicObjectModule.Animations.Animation;
import DynamicObjectModule.Entities.Sprite;
import DynamicObjectModule.Transitions.FiniteStateMachines.FiniteStateMachine;

public abstract class State<EntityType extends Sprite> {
	protected EntityType _entity;
	protected String _returnState;

	protected State(EntityType sprite) {
		assert sprite != null;

		_entity = sprite;
	}

	public void enter() {
		Animation currentAnimation = _entity.getCurrentAnimation();
		if (currentAnimation != null) {
			currentAnimation.reset();
		}
		
		_entity.setAnimation(getType(), _entity.getDirection());
		_entity.getCurrentAnimation().start();
	}

	public void execute() {
		updateAnimation();
	}

	public abstract void exit();
	
	public String getReturnState() {
		assert (_returnState != null && _returnState != "");
		return _returnState;
	}

	public abstract String getType();
	
	protected void updateAnimation() {
		Animation animation = _entity.getCurrentAnimation();
		FiniteStateMachine<?> fsm = _entity.getFSM();
		
		if (animation != null) {
			if (animation.isStopped()) {
				String returnState = fsm.getCurrentState().getReturnState();
				if (returnState != StateType.EMPTY) {
					animation.reset();
					fsm.setState(returnState);
				}
			}
			else {
				animation.update();
			}
		}
	}
}
