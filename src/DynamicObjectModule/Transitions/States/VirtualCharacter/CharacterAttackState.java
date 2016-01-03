package DynamicObjectModule.Transitions.States.VirtualCharacter;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Common.StateType;
import DynamicObjectModule.Entities.VirtualCharacter;
import DynamicObjectModule.Transitions.States.AttackState;
import Utility.SoundPlayer.SoundPlayer;

public class CharacterAttackState extends AttackState<VirtualCharacter> {
	boolean _isSoundPlayed;

	public CharacterAttackState(VirtualCharacter sprite) {
		super(sprite);

		_returnState = StateType.IDLE;
		_isSoundPlayed = false;
	}
	
	@Override
	public void enter() {
		super.enter();
		playSound("SoundEffects/attack_shaut.wav");
	}
	
	@Override
	public void execute() {
		super.execute();
		if (_entity.getCurrentAnimation().getCurrentFrameCount() == 6 && !_isSoundPlayed) {
			try {
				SoundPlayer.play("SoundEffects/swipe.wav");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_isSoundPlayed = true;
		}
	}
	
	@Override
	public void exit() {
		super.exit();
		_isSoundPlayed = false;
	}

	@Override
	protected void setupAnimation() {
		_entity.setAnimation(getType(), _entity.getDirection());
		_entity.getCurrentAnimation().loop(1);
		_entity.getCurrentAnimation().start();
	}
}
