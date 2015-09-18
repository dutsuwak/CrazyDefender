package logic;

import java.applet.*;

public class Sound {
	
	
	public static final AudioClip explo = Applet.newAudioClip(
			Sound.class.getResource(ReadProperties.file.getSetting("sndExplo"))
			);
	public static final AudioClip blast = Applet.newAudioClip(
			Sound.class.getResource(ReadProperties.file.getSetting("sndBlast"))
			);
	private AudioClip audio;
	
	public Sound(String s){
		audio = Applet.newAudioClip(Sound.class.getResource(s));
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
