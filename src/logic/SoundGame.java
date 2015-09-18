package logic;


import java.applet.Applet;
import java.applet.AudioClip;
import logic.ReadProperties;

public class SoundGame {
	private AudioClip audio;
	public static SoundGame music = null;
	
	
	private SoundGame(){ //Constructor
		audio = Applet.newAudioClip(Sound.class.getResource(ReadProperties.file.getSetting("sndGame")));

	}
	public static SoundGame getInstance(){
		if(music == null)
			music = new SoundGame();
		return music;
	}
	public void play(){
		audio.play();
	}
	public void start(){
		audio.loop();
	}
	public void stop(){
		audio.stop();
	}
}

