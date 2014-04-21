package com.md04.gee3.epicchase.games;

import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;

public class LevelManager {
	
	private static RecordStore tomLevelRS;
	private static RecordStore jerryLevelRS;	
	
	public static void saveTomLevel(int tom)
	{
		try {
			RecordStore.deleteRecordStore("tomLevel");
		} catch (RecordStoreNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RecordStoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		openTomLevel();
		
		try {
			byte tomLevelArr[] = {(byte) tom};
			tomLevelRS.addRecord(tomLevelArr, 0, tomLevelArr.length);
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
		closeTomLevel();
		
	}

	private static void closeTomLevel() {
		// TODO Auto-generated method stub
			try {
				tomLevelRS.closeRecordStore();
			} catch (RecordStoreNotOpenException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RecordStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private static void openTomLevel() {
		// TODO Auto-generated method stub
			try {
				tomLevelRS = RecordStore.openRecordStore("tomLevel", true);
			} catch (RecordStoreFullException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RecordStoreNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RecordStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void printTomLevel() {
		openTomLevel();
		try {
			System.out.println("Tom Level: "+(int)tomLevelRS.getRecord(1)[0]);
		} catch (RecordStoreNotOpenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RecordStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeTomLevel();
	}
	
	public static void saveJerryLevel(int jerry)
	{
		try {
			RecordStore.deleteRecordStore("jerryLevel");
		} catch (RecordStoreNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RecordStoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		openJerryLevel();
		try {
			byte jerryLevelArr[] = {(byte) jerry};
			jerryLevelRS.addRecord(jerryLevelArr, 0, jerryLevelArr.length);
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
		closeJerryLevel();
	
	}

	private static void closeJerryLevel() {
		// TODO Auto-generated method stub
			try {
				jerryLevelRS.closeRecordStore();
			} catch (RecordStoreNotOpenException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RecordStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private static void openJerryLevel() {
		// TODO Auto-generated method stub
			try {
				jerryLevelRS = RecordStore.openRecordStore("jerryLevel", true);
			} catch (RecordStoreFullException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RecordStoreNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RecordStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void printJerryLevel() {
		openJerryLevel();
		try {
			System.out.println("Jerry Level: "+(int)jerryLevelRS.getRecord(1)[0]);
		} catch (RecordStoreNotOpenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidRecordIDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RecordStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeJerryLevel();
	}
	
	public static void resetAll() {
		try {
			RecordStore.deleteRecordStore("tomLevel");
			RecordStore.deleteRecordStore("jerryLevel");
		} catch (RecordStoreNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RecordStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
