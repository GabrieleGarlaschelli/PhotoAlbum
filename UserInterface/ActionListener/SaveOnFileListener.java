package UserInterface.ActionListener;

import ManageImages.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SaveOnFileListener 
							implements ActionListener {
	private Album album;
	private JFrame main_frame;
	public SaveOnFileListener(Album album, JFrame main_frame) {
		this.album = album;
		this.main_frame = main_frame;
	}

	public void actionPerformed(ActionEvent event) {
		try {
			album.saveOnFile();
			JOptionPane.showMessageDialog(main_frame, "Salvataggio avvenuto con successo", "success", JOptionPane.PLAIN_MESSAGE);
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(main_frame, "Salvataggio non avvenuto", "error", JOptionPane.ERROR_MESSAGE);
		}
	}
}