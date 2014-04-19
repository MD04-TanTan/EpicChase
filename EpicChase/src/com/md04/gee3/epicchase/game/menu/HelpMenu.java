package com.md04.gee3.epicchase.game.menu;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;



/*
 * Class for implementing help menu.
 */
public class HelpMenu
    extends Menu {

    public static final int BACK = 0;
    private final boolean hasPointerEvents;
    private int width;
    private int height;
    private final Font fontBold = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD,
        Font.SIZE_SMALL);
    private final Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN,
        Font.SIZE_SMALL);
    private Image bgHelp;

    public HelpMenu(int w, int h, boolean hasPointerEvents, Listener l) {
        super(1, w, h, l);
        bgHelp = loadImage("bg_help.png");
        this.hasPointerEvents = hasPointerEvents;
        setItem(BACK, new MenuItem(loadSprite("back_footer.png", 2)));
        setSize(w, h);
    }

    public final void setSize(int w, int h) {
        width = w;
        height = h;

        int x = width / 2;
        int y = 11 * height / 11 -10;
        for (int i = 0; i < getSize(); i++) {
            MenuItem item = getItem(i);
            item.setCenter(x, y);
            y += item.getHeight();
        }
    }

    protected void paint(Graphics g) {
        g.setColor(0x00000000);
        g.fillRect(0, 0, width, height);
        g.setColor(0x00ffffff);
        int x = width / 2;
        int y = height / 12;
        int a = Graphics.BASELINE | Graphics.HCENTER;
        g.drawImage(bgHelp, width / 2, height / 2,
                Graphics.HCENTER | Graphics.VCENTER);
        g.setFont(fontBold);

        super.paint(g);
    }
}
