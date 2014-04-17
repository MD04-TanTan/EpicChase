package com.md04.gee3.epicchase.game.menu;

import javax.microedition.lcdui.game.Sprite;



/*
 * Class for a menu item that can be toggled,
 * e.g. sound on and off.
 */
public class ToggleMenuItem
    extends MenuItem {

    private volatile boolean on = false;

    public ToggleMenuItem(Sprite sprite) {
        super(sprite);
    }

    public void setOn(boolean on) {
        this.on = on;
        sprite.setFrame(getFrame());
    }

    protected int getFrame() {
        return super.getFrame() + (on ? 0 : 2);
    }

    public boolean isOn() {
        return on;
    }
}
