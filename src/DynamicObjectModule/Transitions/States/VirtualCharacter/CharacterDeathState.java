package DynamicObjectModule.Transitions.States.VirtualCharacter;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Common.StateType;
import DynamicObjectModule.Entities.VirtualCharacter;
import DynamicObjectModule.Transitions.States.DeathState;
import Utility.SoundPlayer.SoundPlayer;

public class CharacterDeathState extends DeathState<VirtualCharacter> {
	private boolean _isPlayed;

	public CharacterDeathState(VirtualCharacter sprite) {
		super(sprite);

		_returnState = StateType.EMPTY;
		_isPlayed = false;
	}
	
	@Override
	public void enter() {
		super.enter();
		try {
			SoundPlayer.play("SoundEffects/death.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void execute() {
		super.execute();
		if (_entity.getCurrentAnimation().isAtFrameEnd() && !_isPlayed) {
			try {
				SoundPlayer.play("SoundEffects/ground.wav");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_isPlayed = true;
		}
	}

}
