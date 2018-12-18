package UserInterface.ActionListener;

import ManageImages.*;
import UserInterface.Views.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// this is not working
// I have to remove the shootdown hook on the old album and create another one on the new album
// 

public class ResetAlbumListener 
							implements ActionListener {
	private Album album;
	private JFrame main_frame;
	public ResetAlbumListener(Album album, JFrame main_frame) {
		this.album = album;
		this.main_frame = main_frame;
	}

	public void actionPerformed(ActionEvent event) {
		Object[] options = { "Si", "No" };
		int dialogResult = JOptionPane.showOptionDialog(null, "Sei sicuro di volere resettare l'album?", "Reset Album", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if(dialogResult == JOptionPane.YES_OPTION) {
			Album new_album = new Album();
			main_frame.setVisible(false);
			try {
				new_album.saveOnFile();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
			AlbumFrame f = new AlbumFrame("NewPhotoAlbum", new_album);
		}
	}
}