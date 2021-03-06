package com.md04.gee3.epicchase.main;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

import com.md04.gee3.epicchase.game.audio.Audio;
import com.md04.gee3.epicchase.game.dialog.LoseDialog;
import com.md04.gee3.epicchase.game.dialog.WinDialog;
import com.md04.gee3.epicchase.game.menu.AboutMenu;
import com.md04.gee3.epicchase.game.menu.ChoiceCharacter;
import com.md04.gee3.epicchase.game.menu.EpicChaseMenu;
import com.md04.gee3.epicchase.game.menu.HelpMenu;
import com.md04.gee3.epicchase.game.menu.MapLevelMenu;
import com.md04.gee3.epicchase.game.menu.Menu;
import com.md04.gee3.epicchase.game.menu.Menu.Listener;
import com.md04.gee3.epicchase.games.Game;
import com.md04.gee3.epicchase.games.LevelManager;



/*
 * Game canvas class for the user interface.
 */
public class EpicChaseCanvas
	extends GameCanvas
	implements GameThread.Listener, CommandListener{
	
	private static final boolean HW_BACK_KEY_EXISTS;
    private static final int MAX_RENDERING_FPS = 12;
    private static final int LEFT_SOFTKEY = -6;
    private static final int RIGHT_SOFTKEY = -7;
    private volatile int pointerKeyState = 0;
    private int tomLv = 1;
    private int jerryLv = 2;
    private Main main;
    // menus
    private EpicChaseMenu menu;
    private HelpMenu helpMenu;
    private AboutMenu aboutMenu;
    private LoseDialog loseMenu;
    private ChoiceCharacter choiceMenu;
    private MapLevelMenu mapMenu;
    
    // current visible menu
    private Menu visibleMenu;
    private Game gamecv;
    
    // touch handler
    private PointerEventHandler pointerEventHandler;
    // the game loop that is run MAX_RENDERING_FPS timer per second
    private GameThread gameLoop;
    private Graphics graphics;
    //trang thai game
    private int GAME_STATE;
    public static Audio menuMusic;
    private Command backCommand;
    //Trang thai nhan vat
    boolean isJump;
	int stateChar;
    static {
        HW_BACK_KEY_EXISTS = System.getProperty("com.nokia.keyboard.type").equalsIgnoreCase("OnekeyBack");
    }

    /**
     * Initializes the canvas
     *
     * @param main Main midlet
     */
    public EpicChaseCanvas(Main main) {
        super(false);
        setFullScreenMode(true);
        this.main = main;
        GAME_STATE = 0;
        //nhan vat chay
        isJump = false;
		stateChar = 0;
        // create menus
        createMenu();
        createLoseDialog();
        createGame();
        createHelpMenu();
        createAboutMenu();
        createNewGame();
        createMapLevelMenu();
        createPointerEventHandler();
        gamecv = new Game();
        if (HW_BACK_KEY_EXISTS) {
            backCommand = new Command("Back", Command.BACK, 0);
            this.addCommand(backCommand);
            this.setCommandListener(this);
        }

    }

    private void createLevel() { 
		// TODO Auto-generated method stub

    	LevelManager.saveTomLevel(2);
    	LevelManager.saveJerryLevel(20);

	}
    
    private void createLoseDialog() {
    	loseMenu = new LoseDialog(10, getWidth(), getHeight(), new Menu.Listener() {

			public void itemClicked(int item) {
				// TODO Auto-generated method stub
				switch(item) {
					case WinDialog.REPLAY:
						break;
					case WinDialog.CONTINUE:
						break;
					case WinDialog.MENU:
						hideCurrentMenu();
						showMenu();
						break;
				}
			}});
    }

	/**
     * Gets the states of the physical game keys.
     *
     * @return An integer containing the key state information (one bit per
     * key), or 0 if the GameCanvas is not currently shown.
     */
    public int getKeyStates() {
        int keyStates = super.getKeyStates();
        if (keyStates != 0) {
            pointerKeyState = 0;
        }
        else {
            keyStates = pointerKeyState;
            if (pointerKeyState == FIRE_PRESSED) {
                pointerKeyState = 0;
            }
        }
        return keyStates;
    }

    /**
     * Shows menu view.
     */
    public void showMenu() {
        if (visibleMenu == menu) {
            return;
        }
        visibleMenu = menu;
        //menu.setSounds(game.soundsEnabled);
        menu.selectItem(hasPointerEvents() ? -1 : 0);
    }

    /**
     * Hides current menu view.
     */
    public void hideCurrentMenu() {
//        if (visibleMenu == menu && AudioManager.areSoundsEnabled()
//            != game.soundsEnabled) {
//            AudioManager.setSoundsEnabled(game.soundsEnabled);
//        }
        visibleMenu = null;
    }

    /**
     * Saves the current state of the game to RecordStore
     */
//    public void saveGame() {
//        if (game == null) {
//            return;
//        }
//        try {
//            RecordStore gameState = RecordStore.openRecordStore("GameState",
//                true);
//            if (gameState.getNumRecords() == 0) {
//                gameState.addRecord(null, 0, 0);
//            }
//            byte[] data = game.getState();
//            gameState.setRecord(getRecordId(gameState), data, 0, data.length);
//            gameState.closeRecordStore();
//        }
//        catch (Exception e) {
//            try {
//                RecordStore.deleteRecordStore("GameState");
//            }
//            catch (RecordStoreException rse) {
//                // Nothing to do here.
//            }
//        }
//    }

    /**
     * Shows Help view.
     */
    public void showHelpMenu() {
        showMenu();
        helpMenu.selectItem(hasPointerEvents() ? -1 : 0);
        visibleMenu = helpMenu;
        
    }

    /**
     * Shows About view.
     */
    public void showAboutMenu() {
        showMenu();
        aboutMenu.selectItem(hasPointerEvents() ? -1 : 0);
        visibleMenu = aboutMenu;
    }
    
    /**
     * Show Create New Game
     */
    public void showChoiceMenu() {
        showMenu();
        choiceMenu.selectItem(hasPointerEvents() ? -1 : 0);
        visibleMenu = choiceMenu;
    }
    
    public void showLoseDialog() {
    	showMenu();
    	loseMenu.selectItem(hasPointerEvents() ? -1 : 0);
    	visibleMenu = loseMenu;
    }
    
    /**
     * Show Map Level
     */
    public void showMapLevel() {
        showMenu();
        mapMenu.selectItem(hasPointerEvents() ? -1 : 0);
        visibleMenu = mapMenu;
    }
     

    /**
     * Handle key press events.
     * @see javax.microedition.lcdui.Canvas#keyPressed(int)
     * @param key the pressed key
     */
    protected void keyPressed(int key) {
        // delegate key event to proper menu instance
        if (visibleMenu != null) {
            switch (getGameAction(key)) {
                case UP:
                    visibleMenu.selectPrev();
                    break;
                case DOWN:
                    visibleMenu.selectNext();
                    break;
                case FIRE:
                    visibleMenu.clickSelected();
                    break;
            }
        }
        switch (key) {
            case LEFT_SOFTKEY:
                leftSoftkey();
                break;
            case RIGHT_SOFTKEY:
                rightSoftkey();
                break;
        }
    }

    /**
     * delegate pointer events to proper menu
     * @see javax.microedition.lcdui.Canvas#pointerPressed(int, int)
     * @param x coordinate of press
     * @param y coordinate of press
     */
    protected void pointerPressed(int x, int y) {
    	//System.out.println(isJump);
        if (visibleMenu != null) {
            visibleMenu.pointerEvent(Menu.POINTER_PRESSED, x, y);
            //nhan vat nhay
            isJump = true;
			if(GAME_STATE ==1) {
         	   stateChar = 1;
            }

           // System.out.println(isJump);
        }
        else {
            pointerEventHandler.pointerPressed(x, y);
        }
    }

    /**
     * delegate pointer drag events to proper menu
     * @see javax.microedition.lcdui.Canvas#pointerDragged(int, int)
     * @param x coordinate
     * @param y coordinate
     */
    protected void pointerDragged(int x, int y) {
        if (visibleMenu != null) {
            visibleMenu.pointerEvent(Menu.POINTER_DRAGGED, x, y);
        }
        else {
            pointerEventHandler.pointerDragged(x, y);
        }
    }

    /**
     * delegate pointer release events to proper menu
     * @see javax.microedition.lcdui.Canvas#pointerReleased(int, int)
     * @param x coordinate
     * @param y coordinate
     */
    protected void pointerReleased(int x, int y) {
    	
        if (visibleMenu != null) {
            visibleMenu.pointerEvent(Menu.POINTER_RELEASED, x, y);
        }
        else {
            pointerEventHandler.pointerReleased(x, y);
        }
        
    }

    /**
     * Called when this canvas is shown.
     * @see javax.microedition.lcdui.Canvas#showNotify()
     */
    protected void showNotify() {
        graphics = getGraphics();
        //menuMusic.setLoop(-1);
        startGameLoop();
        
        
        // show menu view first
        showMenu();
    }

    /**
     * Called when this canvas is hidden.
     * @see javax.microedition.lcdui.Canvas#hideNotify()
     */
    protected void hideNotify() {
        //AudioManager.disableSounds();
        stopGameLoop();
        
        graphics = null;
    }

    /**
     * Handles command events.
     * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command, javax.microedition.lcdui.Displayable)
     * @param c Command received
     * @param d Displayable that sent the command
     */
    public void commandAction(Command c, Displayable d) {
        if (c == backCommand) {
            if (visibleMenu == menu) {
                main.close();
            } else {
                hideCurrentMenu();
                showMenu();
            }
        }
    }

    /**
     * Called when the drawable area of the Canvas has been changed.
     * @see javax.microedition.lcdui.Canvas#sizeChanged(int, int)
     * @param w the new width in pixels of the drawable area of the Canvas
     * @param h the new height in pixels of the drawable area of the Canvas
     */
    protected void sizeChanged(int w, int h) {
        if (menu != null) {
            menu.setSize(w, h);
        }
        if (helpMenu != null) {
            helpMenu.setSize(w, h);
        }
        if (aboutMenu != null) {
            aboutMenu.setSize(w, h);
        }
//        if (game != null) {
//            game.setViewportSize(w, h);
//        }
        if (pointerEventHandler != null) {
            pointerEventHandler.setSize(w, h);
        }
    }

    private void leftSoftkey() {
//        if (visibleMenu != menu && game != null) {
//            stopGameLoop();
//            try {
//                game.leftSoftkeyPressed();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//
//            startGameLoop();
//        }
    }

    private void rightSoftkey() {
        if (visibleMenu != null) {
            hideCurrentMenu();
        }
        else {
            showMenu();
        }
    }

    private void startGameLoop() {
    	

        stopGameLoop();
        startMenuMusic();
    	//menuMusic.start();
        gameLoop = new GameThread(this, MAX_RENDERING_FPS);
        gameLoop.start();
    }
    
    private void startMenuMusic() {
    	//menuMusic = new Audio("/audio/Menu Music.mp3", -1);
    	menuMusic.start();
    }

    private void createHelpMenu() {
        helpMenu = new HelpMenu(getWidth(), getHeight(), hasPointerEvents(),
            new Menu.Listener() {

                public void itemClicked(int item) {
                    switch (item) {
                        case HelpMenu.BACK:
                            hideCurrentMenu();
                            showMenu();
                            break;
                    }
                }
            });
    }

    private void createAboutMenu() {
        aboutMenu = new AboutMenu(getWidth(), getHeight(), new Menu.Listener() {

            public void itemClicked(int item) {
                switch (item) {
                    case AboutMenu.BACK:
                        hideCurrentMenu();
                        showMenu();
                        break;
                }
            }
        });
    }

    /**
     * Create game. If there is a saved game in the RecordStore,
     * load the previous game state. Otherwise create a new game.
     */
    private void createGame() {
//        game = new Game(getWidth(), getHeight());
//        try {
//            RecordStore gameState = RecordStore.openRecordStore("GameState",
//                true);
//            if (gameState.getNumRecords() == 0
//                || !game.load(gameState.getRecord(getRecordId(gameState)))) {
//                newGame();
//            }
//            gameState.closeRecordStore();
//        }
//        catch (RecordStoreException e) {
//            newGame();
//        }
    }


    private void createNewGame() {
    	   choiceMenu = new ChoiceCharacter(getWidth(), getHeight(), new Menu.Listener() {

               public void itemClicked(int item) {
                   switch (item) {
                       case ChoiceCharacter.BACK:
                           hideCurrentMenu();
                           showMenu();
                           break;
                       case ChoiceCharacter.TOM:
                           System.out.println("Choose Tom!");
                           mapMenu.setChooseTom(true);
                           mapMenu.paintBackground();
                           showMapLevel();
                           break;
                       case ChoiceCharacter.JERRY:
                          System.out.println("Choose Jerry!");                          
                          mapMenu.setChooseJerry(true);
                          mapMenu.paintBackground();
                          showMapLevel();
                           break;
                   }
               }
           });
    }
    
    private void createMapLevelMenu(){	
    	mapMenu = new MapLevelMenu(getWidth(), getHeight(), new Menu.Listener(){
    		public void itemClicked(int item) {
                switch (item) {
                    case MapLevelMenu.BACK:
                        hideCurrentMenu();
                        showChoiceMenu();
                        break;
                    case MapLevelMenu.PLAY:
                        //Khi nhan nut Play
                        GAME_STATE = 1;
                        System.out.println("Play Game! game state  = 1");
                        break;
                    case MapLevelMenu.NEWGAME:
                       System.out.println("New Game!");
                        break;
                }
            }
    	});
    	mapMenu.setJerryLevel(jerryLv);
    	mapMenu.setTomLevel(tomLv);
    }

    /*private int getRecordId(RecordStore store)
        throws RecordStoreException {
        RecordEnumeration e = store.enumerateRecords(null, null, false);
        try {
            return e.nextRecordId();
        }
        finally {
            e.destroy();
        }
    }*/

    private void createPointerEventHandler() {
        pointerEventHandler = new PointerEventHandler(getWidth(), getHeight(),
            new PointerEventHandler.Listener() {

                public void onMoveLeft() {
                    pointerKeyState = LEFT_PRESSED;
                }

                public void onMoveRight() {
                    pointerKeyState = RIGHT_PRESSED;
                }

                public void onMoveUp() {
                    pointerKeyState = UP_PRESSED;
                }

                public void onMoveDown() {
                    pointerKeyState = DOWN_PRESSED;
                }

                public void onFire() {
                    pointerKeyState = FIRE_PRESSED;
                }

                public void onLeftSoftKey() {
                    leftSoftkey();
                }

                public void onRightSoftKey() {
                    rightSoftkey();
                }
            });
    }

    private void stopGameLoop() {
        if (gameLoop != null) {
            gameLoop.cancel();
            menuMusic.stop();
        }
    }

    private void createMenu() {
        menu = new EpicChaseMenu(getWidth(), getHeight(), new Menu.Listener() {

            public void itemClicked(int item) {
                switch (item) {
                    case EpicChaseMenu.RESUME:
                        createLoseDialog();
                    	showLoseDialog();
                        //hideCurrentMenu();
                        //menuMusic.stop();
                    	
                        break;
                    case EpicChaseMenu.NEWGAME:
                        showChoiceMenu();
                        LevelManager.saveTomLevel(2);
                    	LevelManager.saveJerryLevel(20);
                        
                        break;
                    case EpicChaseMenu.SOUNDS:
                        menu.toggleSounds();
                        if (menuMusic.areSoundsEnabled())
                        	menuMusic.disableSounds();
                        else menuMusic.enableSounds();
                        break;
                    case EpicChaseMenu.HELP:
                        showHelpMenu();
                        LevelManager.printTomLevel();
                        LevelManager.printJerryLevel();
                        break;
                    case EpicChaseMenu.ABOUT:
                        showAboutMenu();
                        LevelManager.saveTomLevel(100);
                        LevelManager.saveJerryLevel(25);
                        break;
                    case EpicChaseMenu.EXIT:
                        main.close();
                        break;
                }
            }
        });
    }
    
    public void runGameLoop() {
    	// TODO Auto-generated method stub
        if (visibleMenu != null) {
            visibleMenu.render(graphics);
            
             if(GAME_STATE == 1) {   	        
          	gamecv.prepareGraphics(graphics);
          	gamecv.upDate(stateChar);
          }
        }
        flushGraphics();      
    }


}
