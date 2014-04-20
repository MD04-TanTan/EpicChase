package com.md04.gee3.epicchase.games;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;

public class LevelManager {
	
	private static RecordStore tomLevelRS;
	
	private static int tomID = 0;
	
	public static void SaveTomLevel(int tom)
	{
		OpenTomLevel();
		
		//ByteArrayOutputStream bout = new ByteArrayOutputStream();
		//DataOutputStream dout = new DataOutputStream(bout);


		/*(try {
			dout.writeInt(tom);

			dout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		try {
			byte tomLevelArr[] = {(byte) tom};
			if (tomID != 0) {
			tomLevelRS.deleteRecord(tomID);
			}
			tomID = tomLevelRS.addRecord(tomLevelArr, 0, tomLevelArr.length);
			//System.out.println(id);

		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
		CloseTomLevel();
	}

	private static void CloseTomLevel() {
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

	private static void OpenTomLevel() {
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
		OpenTomLevel();
		try {
			System.out.println((int)tomLevelRS.getRecord(tomID)[0]);
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
		CloseTomLevel();
	}
}
	
