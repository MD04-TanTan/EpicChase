package com.md04.gee3.epicchase.game.menu;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;





/*
 * Lớp được kế thừa từ menu chính
 */
public class EpicChaseMenu extends Menu {
	public static final int RESUME = 0;
    public static final int NEWGAME = 1;
    public static final int MULTI = 2;
    public static final int SOUNDS = 3;
    public static final int HELP = 4;
    public static final int ABOUT = 5;
    public static final int EXIT = 6;
    private final Image background;
    private final ToggleMenuItem sounds;
    private int width;
    private int height;

    public EpicChaseMenu(int w, int h, Listener l) {
    	//TAN TEST GIT HUB
        super(7, w, h, l);
        background = loadImage("bg_logo.png");
        setItem(RESUME, new MenuItem(loadSprite("resume.png", 2)));
        setItem(NEWGAME, new MenuItem(loadSprite("single.png", 2)));
        setItem(MULTI, new MenuItem(loadSprite("multi.png", 2)));
        sounds = new ToggleMenuItem(loadSprite("soundss.png", 4));
        setItem(SOUNDS, sounds);
        setItem(HELP, new MenuItem(loadSprite("help.png", 2)));
        setItem(ABOUT, new MenuItem(loadSprite("about.png", 2)));
        setItem(EXIT, new MenuItem(loadSprite("exit.png", 2)));
       // setItem(TOM, new MenuItem(loadSprite("tom_menu.png", 2)));
      //  setItem(JERRY, new MenuItem(loadSprite("jerry_menu.png", 2)));
        setSize(w, h);
    }

    /**
     * Set menu size to fit the screen.
     * @param w width of the menu
     * @param h height of the menu
     */
    public final void setSize(int w, int h) {
        width = w;
        height = h;
        final int bgW = background.getWidth();
        final int bgH = background.getHeight();
        final int leftOffset = (w - bgW) / 2;
        final int topOffset = (h - bgH) / 2;
        final int menuW = Math.min(w, bgW);
        final int menuH = Math.min(h, bgH);
        int x = leftOffset + menuW / 2;
        int y = topOffset + menuH / 9;
        for (int i = 0; i < getSize(); i++) {
            MenuItem item = getItem(i);
            item.setCenter(x, y);
            y += item.getHeight();
        }
    }

    protected void paint(Graphics g) {
        g.setColor(0x00000000);
        g.fillRect(0, 0, width, height);
        g.drawImage(background, width / 2, height / 2,
            Graphics.HCENTER | Graphics.VCENTER);
        super.paint(g);
    }

    public void setSounds(boolean on) {
        sounds.setOn(on);
    }

    public boolean toggleSounds() {
        sounds.setOn(!sounds.isOn());
        return sounds.isOn();
    }
}
