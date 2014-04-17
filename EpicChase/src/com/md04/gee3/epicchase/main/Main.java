package com.md04.gee3.epicchase.main;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import com.nokia.mid.ui.VirtualKeyboard;

public class Main extends MIDlet {

//	public Main() {
//		// TODO Auto-generated constructor stub
//		
//	}
	protected static Display display;
	private EpicChaseCanvas epicChaseCanvas = null;
	Poster poster;
	    /**
	     * Initializes display.
	     * @see javax.microedition.midlet.MIDlet#startApp()
	     */
	    public void startApp() {
	        if (epicChaseCanvas == null) {
	        	epicChaseCanvas = new EpicChaseCanvas(this);
	            display = Display.getDisplay(this);
	            poster = new Poster(display, epicChaseCanvas);
	            display.setCurrent(poster);
	        }

	        // Hide virtual keyboard if one exists.
	        if (hasOnekeyBack()) {
	            VirtualKeyboard.hideOpenKeypadCommand(true);
	            VirtualKeyboard.suppressSizeChanged(true);
	        }
	    }

	    /**
	     * Pauses the app.
	     * @see javax.microedition.midlet.MIDlet#pauseApp()
	     */
	    public void pauseApp() {
	        // Nothing to do here.
	    }

	    /**
	     * Saves game before destroying the application.
	     * @see javax.microedition.midlet.MIDlet#destroyApp(boolean)
	     * @param unconditional Should the MIDlet cleanup and release all resources.
	     */
	    public void destroyApp(boolean unconditional) {
	        if (epicChaseCanvas != null) {
	        	//epicChaseCanvas.saveGame();
	        }
	    }

	    public void close() {
	        destroyApp(true);
	        notifyDestroyed();
	    }

	    public EpicChaseCanvas getBattleTankCanvas() {
	        return epicChaseCanvas;
	    }

	    /**
	     * Displays an alert message.
	     *
	     * @param title Title of the message
	     * @param alertText Text of the message
	     * @param type Type of the alert
	     */
	    public static void showAlertMessage(String title, String alertText, AlertType type) {
	        showAlertMessage(display, title, alertText, type);
	    }

	    public static void showAlertMessage(Display display, String title,
	        String alertText, AlertType type) {
	        Alert alert = new Alert(title, alertText, null, type);
	        display.setCurrent(alert, display.getCurrent());
	    }

	    /**
	     * Determine whether the device has hardware keyBack. If so, it has a
	     * virtual keyboard, which isn't needed here. This method is needed to
	     * preserve backwards compatibility with devices that do not have a
	     * virtual keyboard.
	     * @return true or false
	     */
	    private boolean hasOnekeyBack() {
	        String keyboard = System.getProperty("com.nokia.keyboard.type");
	        if (keyboard != null) {
	            return (keyboard.equalsIgnoreCase("OnekeyBack"));
	        }
	        else {
	            return false;
	        }
	    }

}
