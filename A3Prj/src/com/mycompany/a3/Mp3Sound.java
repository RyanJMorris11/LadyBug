package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Mp3Sound implements Runnable{
	private Media m;

	public Mp3Sound(String fileName) {
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
			
			m = MediaManager.createMedia(is, "audio/mp3", this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public void run() {
		// start playing the sound from time zero (beginning of the sound file)
		m.setTime(0);
		m.play();
	}
	
	//continue playing
	public void play() {
		m.play();
	}
	
	public void pause() {
		m.pause();
	}

}
