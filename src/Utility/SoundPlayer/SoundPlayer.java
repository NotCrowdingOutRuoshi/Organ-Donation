package Utility.SoundPlayer;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {

	private SoundPlayer() {

	}

	/**
	 * Example: play("SoundEffects/punched.wav");
	 * @param resourceLocation
	 *            the name of the file that is going to be played
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 */
	public static void play(String resourceLocation) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		(new SoundThread(resourceLocation)).start();
	}
}
