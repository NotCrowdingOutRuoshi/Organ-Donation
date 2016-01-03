package Utility.SoundPlayer;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import Resources.Resources;

public class SoundThread extends Thread {
	private static final int _BUFFER_SIZE = 128000;
	private String _resourceLocation;

	public SoundThread(String resourceLocation) {
		_resourceLocation = resourceLocation;
	}

	public void run() {
		try {
			playSound(_resourceLocation);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	private void playSound(String resourceLocation)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		File soundFile = Resources.getResourceFile(resourceLocation);
		AudioInputStream audioStream;
		AudioFormat audioFormat;
		SourceDataLine sourceLine;

		audioStream = AudioSystem.getAudioInputStream(soundFile);

		audioFormat = audioStream.getFormat();

		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		sourceLine = (SourceDataLine) AudioSystem.getLine(info);
		sourceLine.open(audioFormat);

		sourceLine.start();

		int nBytesRead = 0;
		byte[] abData = new byte[_BUFFER_SIZE];
		while (nBytesRead != -1) {
			try {
				nBytesRead = audioStream.read(abData, 0, abData.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (nBytesRead >= 0) {
				@SuppressWarnings("unused")
				int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
			}
		}

		sourceLine.drain();
		sourceLine.close();
	}
}
