package com.md04.gee3.epicchase.game.dialog;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;

import com.md04.gee3.epicchase.games.Resources;



/*
 * Generic class for implementing dialogs.
 * Thông báo
 */
public class Dialog {

    protected final Resources resources;
    private int width = 0;
    private int height = 0;
    private TiledLayer background;
    protected volatile boolean visible = false;
    protected volatile int level = 0;
    protected volatile int score = 0;

    protected Dialog(Resources r) {
        resources = r;
    }

    /**
     * Shows the dialog telling the score gained in a level
     *
     * @param score Score to be shown
     * @param level Sequence number of level to be shown
     */
    public void show(int score, int level) {
        this.level = level;
        this.score = score;
        visible = true;
    }

    /**
     * Hides the dialog
     */
    public void hide() {
        visible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    /**
     * Paints the dialog.
     *
     * @param g Graphics object
     * @param w Width of view port
     * @param h Height of view port
     */
    public void paint(Graphics g, int w, int h) {
        if (background == null || width != w || height != h) {
            width = w;
            height = h;
            Image bg = resources.hudBackground;
            int columns = (w - 1) / bg.getWidth() + 1;
            int rows = (h - 1) / bg.getHeight() + 1;
            background = new TiledLayer(columns, rows, bg, bg.getWidth(), bg.
                getHeight());
            background.fillCells(0, 0, columns, rows, 1);
            background.setPosition(0, 0);
        }
        background.paint(g);
    }
}

