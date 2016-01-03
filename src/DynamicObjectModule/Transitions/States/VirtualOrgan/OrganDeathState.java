package DynamicObjectModule.Transitions.States.VirtualOrgan;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Common.StateType;
import DynamicObjectModule.Animations.Animation;
import DynamicObjectModule.Entities.VirtualOrgan;
import DynamicObjectModule.Transitions.FiniteStateMachines.FiniteStateMachine;
import DynamicObjectModule.Transitions.States.DeathState;
import Utility.SoundPlayer.SoundPlayer;

public class OrganDeathState extends DeathState<VirtualOrgan> {
	private int _counter;
	private static final int _INITIAL_COUNTER = 3;

	public OrganDeathState(VirtualOrgan sprite) {
		super(sprite);
		_returnState = StateType.EMPTY;
		_counter = _INITIAL_COUNTER;
	}
	
	@Override
	public void enter() {
		super.enter();
		_entity.setX(_entity.getOwner().getX());
		_entity.setY(_entity.getOwner().getY());
		try {
			SoundPlayer.play("SoundEffects/organ_died.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};

	@Override
	public void execute() {
		super.execute();
		_counter -= 1;
		
		if (_counter == 0) {
			_entity.setY(_entity.getY() - 1);
			_counter = _INITIAL_COUNTER;
		}
	}
	
	@Override
	protected void setupAnimation() {
		_entity.setAnimation(getType(), _entity.getDirection());
		_entity.getCurrentAnimation().loop(10);
	}
	
	@Override
	protected void updateAnimation() {
		Animation animation = _entity.getCurrentAnimation();
		FiniteStateMachine<?> fsm = _entity.getFSM();
		
		if (animation != null) {
			if (animation.isStopped()) {
				String returnState = fsm.getCurrentState().getReturnState();
				fsm.setState(returnState);
			}
			else {
				animation.update();
			}
		}
	}

}
