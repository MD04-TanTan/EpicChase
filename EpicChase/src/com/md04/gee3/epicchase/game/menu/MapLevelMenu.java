package com.md04.gee3.epicchase.game.menu;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import com.md04.gee3.epicchase.game.menu.Menu.Listener;
import com.md04.gee3.epicchase.games.LevelManager;


/*
 * Class for implementing Load Map Level
 */
public class MapLevelMenu extends Menu{
	public static final int BACK = 0;
    public static final int PLAY = 1;
    public static final int NEWGAME = 2;
    private int width;
    private int height;
    private int marginBottom = 15;
    private int distanceWidthItem = 25;
   
    private final Font fontBold = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD,
        Font.SIZE_SMALL);
    private final Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN,
        Font.SIZE_SMALL);
    
    private int tomLevel;
	private int jerryLevel;
    private boolean chooseTom = false;
   	private boolean chooseJerry = false;
    
    private  Image bgChoose;
	
    public MapLevelMenu(int w, int h, Listener l) {
        super(3, w, h, l);
        
        bgChoose = loadImage("/map/bg_map.png");
        setItem(PLAY, new MenuItem(loadSprite("play_footer.png", 2)));
        setItem(BACK, new MenuItem(loadSprite("back_footer.png", 2)));
        setItem(NEWGAME, new MenuItem(loadSprite("newgame_footer.png", 2)));
        setSize(w, h);
    }
    
    public void paintBackground(){
    	if(chooseTom){
    		switch (tomLevel) {
			case 1:
				bgChoose = loadImage("/map/level1.png");
				setBackground(bgChoose);
				chooseTom = false;
				break;
			default:
				break;
			}
    	}
    	if(chooseJerry){
    		switch (jerryLevel) {
			case 2:
				bgChoose = loadImage("/map/level2.png");
				setBackground(bgChoose);
				chooseJerry = false;
				break;

			default:
				break;
			}
    	}
    }
    
    public final void setSize(int w, int h) {
    	width = w;
        height = h;
        final int bgW = bgChoose.getWidth();
        final int bgH = bgChoose.getHeight();
        final int leftOffset = (w - bgW) / 2;
        final int topOffset = (h - bgH) / 2;
        final int menuW = Math.min(w, bgW);
        final int menuH = Math.min(h, bgH);
        int x = leftOffset + menuW / 5;
        int y = topOffset + menuH - marginBottom;
        for (int i = 0; i < getSize(); i++) {
            MenuItem item = getItem(i);
            item.setCenter(x, y);
            //y += item.getHeight();
            x = x + item.getWidth() + distanceWidthItem;
            
        }
        System.out.println("Width: " + w);
        System.out.println("Height: " + h);
        System.out.println("leftOffset: " + leftOffset);
        System.out.println("topOffset: " + topOffset);
        System.out.println("X: " + x);
        System.out.println("Y: " + y);
    }

    
    protected void paint(Graphics g) {
        g.setColor(0x00000000);
        g.fillRect(0, 0, width, height);
        g.setColor(0x00ffffff);
        int x = width / 2;
        int y = height / 9;
        int a = Graphics.BASELINE | Graphics.HCENTER;
        
        g.drawImage(bgChoose, width / 2, height / 2,
                Graphics.HCENTER | Graphics.VCENTER);
        g.setFont(fontBold);

        super.paint(g);
    }
    
	public void setChooseTom(boolean choose){
		this.chooseTom = choose;
	}
	
	public void setChooseJerry(boolean choose){
		this.chooseJerry = choose;
	}
	
	public void setTomLevel(int level){
		this.tomLevel = level;
	}
	
	public void setJerryLevel(int level){
		this.jerryLevel = level;
	}
	
	public void setBackground(Image bg){
		this.bgChoose = bg;
	}
	
}
