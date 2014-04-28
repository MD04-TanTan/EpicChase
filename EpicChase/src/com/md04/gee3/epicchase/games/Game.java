package com.md04.gee3.epicchase.games;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.lcdui.game.TiledLayer;
import com.md04.gee3.epicchase.game.entities.Character;

/*
 * Class to handle game logic - logic game
 */
public class Game {
	Graphics g;
	LayerManager manager;
	TiledLayer backgroundLayer,groundLayer;
	Image imgBG,imgGround;
//	int mAnimatedIndex = 0;

	private int Xman;
	private int Yman;
	int bgCurX;
	boolean isJump;
	Character man;
	Sprite spriteMan;
	public Game() {
		
		// TODO Auto-generated constructor stub
		manager = new LayerManager();
		bgCurX = 0;
		isJump = false;
		initImage();	
		initCharacter();
		//initGroundLayer();	
		initBackgroundLayer();		
	}
	
	public void initImage()
	{
		try {
			imgBG = Image.createImage("/bggame/bg5.png");
			imgGround = Image.createImage("/bggame/ground.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initBackgroundLayer()
	{
		backgroundLayer = new TiledLayer(1, 1, imgBG, 640, 240);
		backgroundLayer.setCell(0, 0, 1);
		backgroundLayer.setPosition(0, 0);
		manager.append(backgroundLayer);
	}
	
	private void initGroundLayer()
	{
		groundLayer = new TiledLayer(1, 1, imgGround, 332, 78);
		groundLayer.setCell(0, 0, 1);
		groundLayer.setPosition(0, 200);
		manager.append(groundLayer);
	}
	public void initCharacter()
	{
		man = new Character();
		spriteMan = man.createSprite();
		manager.append(spriteMan);	
	}
//	public void startGame()
//	{
//		Thread t= new Thread(this);
//		t.start();
//	}
	
	public void upDate(int state)
	{
		bgCurX-=2;
		if(bgCurX == -320)
			bgCurX = 0;
		else{	
			backgroundLayer.setPosition(bgCurX, 0);
			//groundLayer.setPosition(bgCurX, 200);	
		}		
		spriteMan.nextFrame();
		
		switch (state) {	
		case 1://Nhay
			isJump = true;
			if(isJump)
			{
				man.jump();
				isJump = false;
			}		
			else
//			if(isJump == false)
			{
				System.out.println("aaaa");
				man.update();
			}
			System.out.println(isJump);
			break;
		case 2:
			
		default:
			break;
		}
		//System.out.println("Game.upDate()" + bgCurX);
//		if (isJump) {	
//			man.jump();	
//			if(spriteMan.getY() <= 180)
//			{
//				isJump = false;
//			}	
//		}
//		else
//			{
//				man.update();
//				//System.out.println(spriteMan.getY());
//			}
	}


	public void prepareGraphics(Graphics g)
	{

		manager.paint(g, 0, 0);
	}
	

}
