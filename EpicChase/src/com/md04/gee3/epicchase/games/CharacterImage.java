package com.md04.gee3.epicchase.games;

import javax.microedition.lcdui.Image;

/*
 * Lớp hình ảnh nhân vật
 */
public class CharacterImage {

    public final Image image;
    public final int[] protectedSeq; 	// Hình ảnh nhân vật khi đang được bảo vệ
    public final int[] normalSeq; 		// Hình ảnh nhân vật khi ở chế độ bình thường

    public CharacterImage(Image image, int[] protectedSeq, int[] normalSeq) {
        this.image = image;
        this.protectedSeq = protectedSeq;
        this.normalSeq = normalSeq;
    }
}