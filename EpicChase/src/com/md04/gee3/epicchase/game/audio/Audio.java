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
	
	private final String PATH  = "/audio/";
	private String file;
	private int loop;
	private boolean soundsEnabled = true;
	private int volume = 20;
	
	public static final int BUTTONS = 0;
	public static final int CHOICE = 1;
	public static final int COLLIDE = 2;
	public static final int FALL = 3;
	public static final int FOOTSTEPS = 4;
	public static final int GAMEPLAY_MUSIC = 5;
	public static final int JUMP = 6;
	public static final int LOSE = 7;
	public static final int MENU_MUSIC = 8;
	public static final int WIN = 9;
	
	public Audio(int audio, int loop)
	{
		switch (audio) {
			case BUTTONS: 
				file = "Buttons.mp3";
				break;
			case CHOICE:
				file = "Choice.mp3";
				break;
			case COLLIDE:
				file = "Collide.mp3";
				break;
			case FALL:
				file = "Fall.mp3";
				break;
			case FOOTSTEPS:
				file = "Footsteps.mp3";
				break;
			case GAMEPLAY_MUSIC:
				file = "Gameplay Music.mp3";
				break;
			case JUMP:
				file = "Jump.mp3";
				break;
			case LOSE:
				file = "Lose.mp3";
				break;
			case MENU_MUSIC:
				file = "Menu Music.mp3";
				break;
			case WIN:
				file = "Win.mp3";
				break;
		}
		this.loop = loop;
		try 
		  {
		    InputStream is = getClass().getResourceAsStream(PATH + file);
		    player = Manager.createPlayer(is, "audio/mpeg"); 
		    player.setLoopCount(loop);
		    player.prefetch();
		    volumeControl = (VolumeControl) player.getControl("VolumeControl");
		  }
		  catch (IOException ioe) { } 
		  catch (MediaException me) { }
	}
	
	public void setLoop(int loop) {
		player.setLoopCount(loop);
	}
	
	public void start()
	{
		try {
			player.prefetch();
			volumeControl.setLevel(volume);
			
			player.start();
		} catch (MediaException e) {
			e.printStackTrace();
		}
	}
	
	public void stop()
	{
		try {
			player.stop();
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
