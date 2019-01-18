import ManageImages.*;
import UserInterface.Views.*;
import Threads.*;
import java.io.*;

public class Main {
	public static void main(String args[]) {
		Album myAlbum = new Album();
		try {
			myAlbum = Album.recoverFromFile();
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		Runtime.getRuntime().addShutdownHook(new SaveThread(myAlbum));
		
		// try {
		// 	myAlbum.createCategory("Il mio compleanno");
		// 	myAlbum.createCategory("la mia famiglia");
		// 	myAlbum.createCategory("I miei amici");
		// 	myAlbum.createCategory("Immagini private");
		// 	myAlbum.createCategory("Lavoro");
		// 	myAlbum.createCategory("Sito");
		// 	myAlbum.addImageToCategory("Il mio compleanno", "./download.png");
		// 	myAlbum.addImageToCategory("la mia famiglia", "./download.png");
		// 	myAlbum.addImageToCategory("I miei amici", "./download.png");
		// 	myAlbum.saveOnFile();
		// } catch(Exception ex) {
		// 	ex.printStackTrace();
		// }

		AlbumFrame f = new AlbumFrame("PhotoAlbum", myAlbum);

	}
}