package com.md04.gee3.epicchase.game.menu;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.TiledLayer;



/*
 * Class for implementing Choose Character
 */
public class ChoiceCharacter
    extends Menu {
	
    public static final int BACK = 0;
    private int width;
    private int height;
    private final Font fontBold = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD,
        Font.SIZE_SMALL);
    private final Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN,
        Font.SIZE_SMALL);
    
    private  Image bgChoose;
	
    public ChoiceCharacter(int w, int h, Listener l) {
        super(1, w, h, l);
        bgChoose = loadImage("bg_about.png");
        setItem(BACK, new MenuItem(loadSprite("back_footer.png", 2)));
        setSize(w, h);
    }

    public final void setSize(int w, int h) {
        width = w;
        height = h;

        int x = width / 2;
        int y = 11 * height / 13;
        for (int i = 0; i < getSize(); i++) {
            MenuItem item = getItem(i);
            item.setCenter(x, y);
            y += item.getHeight();
        }
    }
//	protected void pointerPressed(int x, int y){
//		Graphics g =  getGraphics();
//		if (menuView){
//			if ((x>80)&&(x<160)&&(y>175)&&(y<210)){
//				playingGame = true;
//			}	
//		}
//		/**
//		 *  Hàm vẽ lựa chọn	
//		 */
//			private void createMenu(){
//				Graphics g = getGraphics();	
//				g.setColor(255, 255, 255);
//				menuView = true;
//				g.setClip(0, 0, getWidth(), getHeight());
//				g.drawImage(imgMenu, 0, 0, Graphics.TOP | Graphics.LEFT);
////				g.drawRect(80, 175, 80, 35);
//				flushGraphics();
//			}	
    
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
}
