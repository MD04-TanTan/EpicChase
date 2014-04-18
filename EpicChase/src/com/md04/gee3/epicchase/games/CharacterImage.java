package com.md04.gee3.epicchase.games;

import javax.microedition.lcdui.Image;

/*
 * Class for character image.
 */
public class CharacterImage {

    public final Image image;
    public final int[] protectedSeq; // images for the tank/character in protected mode
    public final int[] normalSeq; // images for the tank/character in normal mode

    public CharacterImage(Image image, int[] protectedSeq, int[] normalSeq) {
        this.image = image;
        this.protectedSeq = protectedSeq;
        this.normalSeq = normalSeq;
    }
}