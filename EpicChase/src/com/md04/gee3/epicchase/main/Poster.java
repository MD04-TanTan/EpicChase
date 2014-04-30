package com.md04.gee3.epicchase.main;

import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import com.md04.gee3.epicchase.game.audio.Audio;

public class Poster extends Canvas {
	
	Display display;
	Displayable nextDisplay;
	Image imgPoster;
	
	public Poster(Display d, Displayable next)
	{
		display = d;
		nextDisplay = next;
		
		try {
			imgPoster = Image.createImage("/poster/poster.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pointerPressed(int x,int y)
	{
		display.setCurrent(nextDisplay);
	}
	protected void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(imgPoster, 0, 0, Graphics.TOP| Graphics.LEFT);
	}
	
	protected void showNotify() {
		EpicChaseCanvas.menuMusic = new Audio(Audio.MENU_MUSIC, -1);
		EpicChaseCanvas.menuMusic.start();
	}
	
	protected void hideNotify() {
		EpicChaseCanvas.menuMusic.stop();
	}
}
