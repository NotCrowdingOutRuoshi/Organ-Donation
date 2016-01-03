package DynamicObjectModule.Transitions.States;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Common.StateType;
import DynamicObjectModule.Animations.Animation;
import DynamicObjectModule.Entities.Sprite;
import DynamicObjectModule.Transitions.FiniteStateMachines.FiniteStateMachine;
import Utility.SoundPlayer.SoundPlayer;

public abstract class State<EntityType extends Sprite> {
	protected EntityType _entity;
	protected String _returnState;
	protected ArrayList<Integer> _playedSoundFrames;

	protected State(EntityType sprite) {
		assert sprite != null;

		_entity = sprite;
		_playedSoundFrames = new ArrayList<>();
	}

	public void enter() {
		resetSoundFrame();
		setupAnimation();
	}

	public void execute() {
		updateAnimation();
	}

	public void exit() {
		resetSoundFrame();
	}

	public String getReturnState() {
		assert (_returnState != null && _returnState != "");
		return _returnState;
	}

	public abstract String getType();
	
	protected void setupAnimation() {
		Animation currentAnimation = _entity.getCurrentAnimation();
		if (currentAnimation != null) {
			currentAnimation.reset();
		}

		_entity.setAnimation(getType(), _entity.getDirection());
		_entity.getCurrentAnimation().start();
	}

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
			} else {
				animation.update();
			}
		}
	}

	protected void playSound(String resourceLocation) {
		if (isSoundPlayed()) {
			return;
		}
		
		String[] resource = new String[1];
		resource[0] = resourceLocation;
		playSound(resource);
	}

	protected void playSound(String[] resourceLocatios) {
		if (isSoundPlayed()) {
			return;
		}
		
		_playedSoundFrames.add(_entity.getCurrentAnimation().getCurrentFrameCount());
		
		for (String resource : resourceLocatios) {
			try {
				SoundPlayer.play(resource);
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	protected void resetSoundFrame() {
		_playedSoundFrames.clear();
	}
	
	protected boolean isSoundPlayed() {
		return _playedSoundFrames.contains(_entity.getCurrentAnimation().getCurrentFrameCount());
	}
}
