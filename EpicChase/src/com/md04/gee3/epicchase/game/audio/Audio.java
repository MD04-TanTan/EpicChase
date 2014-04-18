package com.md04.gee3.epicchase.game.audio;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;

public class Audio {
	
	private Player player;
	private VolumeControl volumeControl;
	
	private String filePath;
	private int loop;
	private boolean soundsEnabled = true;
	private int volume = 20;
	
	public Audio(String filePath, int loop)
	{
		this.filePath = filePath;
		this.loop = loop;
		try 
		  {
		    InputStream is = getClass().getResourceAsStream(filePath);
		    player = Manager.createPlayer(is, "audio/mpeg"); 
		    player.setLoopCount(loop);
		    player.prefetch();
		    volumeControl = (VolumeControl) player.getControl("VolumeControl");
		  }
		  catch (IOException ioe) { } 
		  catch (MediaException me) { }
	}
	
	public void start()
	{
		try {
			volumeControl.setLevel(volume);
			player.start();
		} catch (MediaException e) {
			e.printStackTrace();
		}
	}
	
	public void stop()
	{
		player.close();
	}
	
	public boolean areSoundsEnabled() {
		return soundsEnabled;
	}
	
	public void enableSounds() {
		volumeControl.setMute(false);
		soundsEnabled = true;
	}
	
	public void disableSounds() {
		volumeControl.setMute(true);
		soundsEnabled = false;
	}

}
