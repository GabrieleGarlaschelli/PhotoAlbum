package Threads;

import ManageImages.*;

public class SaveThread extends Thread {

	private Album album;
	public SaveThread(Album album) {
		this.album = album;
	}

	public void run() {
		try {
			album.saveOnFile();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}