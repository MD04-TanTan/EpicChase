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
		curX = 30;
		curY = 180;
		iJump = curY - 50;
	}
	
	//Tao sprite cho character
	public Sprite createSprite()
	{
		spriteChar = new Sprite(imgChar, 50, 48);
		spriteChar.setPosition(curX, curY);
		return spriteChar;
	}
	
	//Khoi tao img
	public void initImage()
	{
		try {
			imgChar = Image.createImage("/sprite/jerry_run.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Ham nhay
	public void jump() {
		
		if(curY > iJump)
		{
			curY-=10;
			spriteChar.setPosition(curX,curY);
		}		
	}
	
	//Ham chet
	public void died() {
		
	}
	
	public void update() {	
		if(curY < 190)
		{
			curY+=5;
			spriteChar.setPosition(curX, curY);
			System.out.println(curY);
		}
//		iJump = curY+=10;
//		spriteChar.setPosition(curX, iJump);
		//spriteChar.setPosition(curX, curY);
	}
	
}
