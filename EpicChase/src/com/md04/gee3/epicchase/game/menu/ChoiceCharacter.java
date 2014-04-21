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
	
    public static final int TOM = 0;
    public static final int BACK = 1;
    public static final int JERRY=2;
    private int width;
    private int height;
    private int marginBottom = 15;
    private int distanceWidthItem = 25;
    private final Font fontBold = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD,
        Font.SIZE_SMALL);
    private final Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN,
        Font.SIZE_SMALL);
    
    private  Image bgChoose;
	
    public ChoiceCharacter(int w, int h, Listener l) {
        super(3, w, h, l);
        bgChoose = loadImage("bg_choose.png");
        setItem(TOM, new MenuItem(loadSprite("back_footer.png", 2)));
        setItem(BACK, new MenuItem(loadSprite("back_footer.png", 2)));
        setItem(JERRY, new MenuItem(loadSprite("back_footer.png", 2)));
        setSize(w, h);
    }
    
    public final void setSize(int w, int h) {
//        width = w;
//        height = h;
//
//        int x = width / 2;
//        int y = 11 * height / 11 -10;
//        for (int i = 0; i < getSize(); i++) {
//            MenuItem item = getItem(i);
//            item.setCenter(x, y);
//            y += item.getHeight();
//        }
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
