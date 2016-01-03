package DynamicObjectModule.Transitions.States.VirtualCharacter;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Common.StateType;
import DynamicObjectModule.Entities.VirtualCharacter;
import DynamicObjectModule.Transitions.States.StealState;
import Utility.SoundPlayer.SoundPlayer;

public class CharacterStealState extends StealState<VirtualCharacter> {

	public CharacterStealState(VirtualCharacter sprite) {
		super(sprite);

		_returnState = StateType.IDLE;
	}

	@Override
	public void enter() {
		super.enter();
		try {
			SoundPlayer.play("SoundEffects/steal.wav");
			SoundPlayer.play("SoundEffects/swipe.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
