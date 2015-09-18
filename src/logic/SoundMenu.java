package logic;


import java.applet.Applet;
import java.applet.AudioClip;
import logic.ReadProperties;

public class SoundMenu {
	private AudioClip audio;
	public static SoundMenu music = null;
	
	
	private SoundMenu(){ //Constructor
		audio = Applet.newAudioClip(Sound.class.getResource(ReadProperties.file.getSetting("sndMenu")));

	}
	public static SoundMenu getInstance(){
		if(music == null)
			music = new SoundMenu();
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

