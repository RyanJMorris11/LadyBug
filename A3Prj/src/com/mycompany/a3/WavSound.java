package com.mycompany.a3;

import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class WavSound {
	private Media m;
	private int pausedTime;

	public WavSound(String fileName) {
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
			m = MediaManager.createMedia(is, "audio/wav");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play() {
		// start playing the sound from time zero (beginning of the sound file)
		m.setTime(0);
		m.play();
	}
	public void loop() {
		
	}
	public void stop() {
		
		m.pause();
		m.setTime(0);
	}
	
	public void restart() {
		m.setTime(0);
	}
	
	public void pause() {
		pausedTime =m.getTime();
	}
	
	public void resume() {
		if(pausedTime!=-1) {
			m.setTime(pausedTime);
			m.play();
		}else {
			play();
		}
		
	}
}
