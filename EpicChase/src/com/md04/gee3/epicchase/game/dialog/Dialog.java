package com.md04.gee3.epicchase.game.dialog;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import com.md04.gee3.epicchase.game.menu.Menu;

/*
 * Thông báo
 */
public class Dialog extends Menu {
	
    protected Image bg;
    protected boolean visible = false;
	
    protected Dialog(int capacity, int w, int h, Listener listener) {
		super(capacity, w, h, listener);
		// TODO Auto-generated constructor stub
	}
   
    protected void paint(Graphics g) {
    
    	super.paint(g);

    }
}

   

