package com.md04.gee3.epicchase.game.dialog;



import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;


import com.md04.gee3.epicchase.game.menu.MenuItem;

public class LoseDialog extends Dialog {

	public static final int HELP = 0;
	public static final int RETRY = 1;
    public static final int MENU = 2;
    private int level;
    private int width;
    private int height;
    private int marginBottom = 15;
    private int distanceWidthItem = 25;
    private final Font fontBold = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD,
        Font.SIZE_LARGE);
    private final Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN,
        Font.SIZE_SMALL);
    
	public LoseDialog(int level, int w, int h, Listener listener) {
		super(3, w, h, listener);
		bg = loadImage("lose.png");
		this.level = level;
		setItem(HELP, new MenuItem(loadSprite("back_footer.png", 2)));
		setItem(RETRY, new MenuItem(loadSprite("replay_footer.png", 2)));
		setItem(MENU, new MenuItem(loadSprite("jerry.png", 2)));
		setSize(w, h);
	}
	
	public final void setSize(int w, int h) {
		width = w;
        height = h;
        final int bgW = bg.getWidth();
        final int bgH = bg.getHeight();
        final int leftOffset = (w - bgW) /2;
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
	}
	
	protected void paint(Graphics g) {
		g.setColor(255, 255, 255);
        g.fillRect(0, 0, width, height);
        g.setColor(0, 255, 0);
        //int x = width/2;
        //int y = height/9;
        //int a = Graphics.BASELINE | Graphics.HCENTER;
        
        g.drawImage(bg, width/2, height/2,
               Graphics.HCENTER | Graphics.VCENTER);
        g.setFont(fontBold);
        g.drawString("BETTER LUCK NEXT TIME", width/2, height/2 - 10, 
        		Graphics.HCENTER | Graphics.BASELINE);
        super.paint(g);
		
    }
	
}
	


