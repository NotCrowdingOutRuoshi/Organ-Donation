package Utility.Audio;

public class AudioManager {
	private static AudioManager singleton;
	private AudioPlayer _currentAudio = null;
	
	public static AudioManager getInstance() {
		if (singleton == null) {
			singleton = new AudioManager();
		}
		return singleton;
	}
	
	private AudioManager() {
		
	}
	
	public void addBackGroundMusic(String s) {
		if(_currentAudio != null) {
			_currentAudio.close();	
			
		} 
		AudioPlayer audioPlayer = new AudioPlayer(s);
		_currentAudio = audioPlayer;
	}
	
	public void play() {
		_currentAudio.play();
	}
	
	public void setLoop() {
		_currentAudio.setLoop();
	}
	
	public void stop() {
		_currentAudio.close();
	}
}
