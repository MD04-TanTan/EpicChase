package com.md04.gee3.epicchase.game.entities;

import java.io.IOException;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Character {
	
	private int width;
	private int height;
	private Sprite spriteChar;
	private Image imgChar;
	private int frameWidth;
	private int frameHeight;
	
	int curX;
	int curY;
	private  int  iJump;
	public Character() {
		// TODO Auto-generated constructor stub
		initImage();
		curX = 20;
		curY = 230;
		//iJump = curY - 50;
	}
	
	//Tao sprite cho character
	public Sprite createSprite()
	{
		spriteChar = new Sprite(imgChar, 48, 48);
		spriteChar.setPosition(curX, curY);
		return spriteChar;
	}
	
	//Khoi tao img
	public void initImage()
	{
		try {
			imgChar = Image.createImage("/man.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Ham nhay
	public void jump() {
		iJump = curY-=5;
		if(iJump >= 180)
		{
			spriteChar.setPosition(curX,iJump);
		}
			
	}
	
	//Ham chet
	public void died() {
		
	}
	
	public void update() {
		
		if(iJump <= 230)
		{
			iJump = curY+=5;
			spriteChar.setPosition(curX, iJump);
		}
//		iJump = curY+=5;
//		spriteChar.setPosition(curX, iJump);
		//spriteChar.setPosition(curX, curY);
	}
	
}
