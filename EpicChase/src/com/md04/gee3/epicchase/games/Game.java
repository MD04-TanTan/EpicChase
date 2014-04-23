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
	int bgCurX = 0;
	boolean isFlying;
	boolean isJump;
	Character man;
	Sprite spriteMan;
	public Game() {
		
		// TODO Auto-generated constructor stub
		manager = new LayerManager();

		isFlying = true;
		isJump = false;
		initImage();	
		initCharacter();
		initGroundLayer();
		initBackgroundLayer();
		
	}
	
	public void initImage()
	{
		try {
			imgBG = Image.createImage("/bggame/atlas.png");
			imgGround = Image.createImage("/bggame/ground.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initBackgroundLayer()
	{
		backgroundLayer = new TiledLayer(1, 1, imgBG, 320, 240);
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
		spriteMan.setPosition(30, 152);
		manager.append(spriteMan);
		
	}
//	public void startGame()
//	{
//		Thread t= new Thread(this);
//		t.start();
//	}
	
	public void upDate()
	{
		
		groundLayer.setPosition(bgCurX--, 200);
		spriteMan.nextFrame();
		if (isJump) {	
			man.jump();	
			if(spriteMan.getY() <= 180)
			{
				isJump = false;
			}	
		}
		else
			{
				man.update();
				//System.out.println(spriteMan.getY());
			}
	}

//	public void run() {
//		// TODO Auto-generated method stub
//		while (isFlying) {
//			upDate();
//			prepareGraphics(g);
//			flushGraphics();
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//	}
	
//	public void pointerPressed(int x,int y)
//	{
//		isJump = true;
//		//man.jump();
//		//isJump = false;
//		System.out.println("jump = true");
//		
//	}
	public void prepareGraphics(Graphics g)
	{

		manager.paint(g, 0, 0);
	}
	

}
