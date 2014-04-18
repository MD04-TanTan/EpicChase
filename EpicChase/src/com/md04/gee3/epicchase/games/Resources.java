package com.md04.gee3.epicchase.games;

import java.io.IOException;

import javax.microedition.lcdui.Image;



/*
 * Lớp xử lý nguồn tài nguyên của game
 */
public class Resources {

    public static final int MEDIUM_THRESHOLD = 320;
    public static final int HIGH_THRESHOLD = 640;
    private String resourcePath;
    public final int gridSizeInPixels;

    public final Image tiles;				// tile khi dùng để tạo map
    public final Image ground;				// Đất	
    public final Image spawn;				// Hình ảnh khi nhân vật được tạo ra
    public final CharacterImage Tom;
    public final CharacterImage Jerry;
  	public final CharacterImage Enemy;
    public static final int[] SPAWNING_ANIM_SEQ = {0, 1, 2};
    public final Image item;  			// Vật phẩm
    public final Image obstruction;		// Vật cản
    public final Image hudBackground;

    public Resources(int w, int h) {
        final int max = Math.max(w, h);
        
        /*
         * Check what is the size of the resources to be loaded
         */
        resourcePath = "/entity/";
        gridSizeInPixels = 4;
        tiles = loadImage("tiles.png");
        ground = loadImage("ground.png");
        spawn = loadImage("spawn.png");
        
        // Load hình ảnh cho nhân vật
        Tom = new CharacterImage(loadImage("tank.png"), new int[]{0, 1, 2},
            new int[]{3, 4, 5});
        Jerry = new CharacterImage(loadImage("FT-17_argonne.png"), new int[]{0, 1,
                2}, new int[]{3, 4, 5});
        Enemy = new CharacterImage(loadImage("m1_abrams.png"), new int[]{1, 2,
                3}, new int[]{0});

        item = loadImage("base.png");
        obstruction = loadImage("trees.png");
        hudBackground = loadImage("hud_bg.png");
    }

    /**
     * Changes a pixel coordinate to a grid coordinate.
     *
     * @param x Pixel coordinate
     * @return Grid coordinate of the give pixel coordinate
     */
    public int toGrid(int x) {
        return x / gridSizeInPixels;
    }

    /**
     * Changes a grid coordinate to a pixel coordinate.
     *
     * @param i Grid coordinate
     * @return Pixel coordinate of the given grid coordinate
     */
    public int toPixels(int i) {
        return i * gridSizeInPixels;
    }

    /**
     * Changes a pixel coordinate to the nearest grid coordinate.
     *
     * @param x Pixel coordinate
     * @return The nearest grid coordinate of the given pixel coordinate
     */
    public int roundToGrid(int x) {
        return toPixels(toGrid(x + gridSizeInPixels / 2));
    }

    private Image loadImage(String fileName) {
        try {
            return Image.createImage(resourcePath + fileName);
        }
        catch (IOException e) {
            return null;
        }
    }
}

