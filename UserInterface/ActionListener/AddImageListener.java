package UserInterface.ActionListener;

import Exceptions.*;
import UserInterface.Views.*;
import ManageImages.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddImageListener 
							implements ActionListener {
	private ImagesPreview image_preview; 
	private int category_id; 
	private Album album;
	private JFrame main_frame;

	public AddImageListener(ImagesPreview panel, int category_id, Album album, JFrame main_frame) {
		this.image_preview = panel;
		this.category_id = category_id;
		this.album = album;
		this.main_frame = main_frame;
	}

	public void actionPerformed(ActionEvent event) {
		JFileChooser fc = new JFileChooser("Aggiungi Immagine");
		int retrun_val = fc.showOpenDialog(image_preview);

		try {
			if(retrun_val == JFileChooser.APPROVE_OPTION) {
				// TODO multiple files
				File f = fc.getSelectedFile();
				album.addImageToCategory(this.category_id, f.getCanonicalPath());

				image_preview.refresh();
			}
		} catch(NotAcceptDoubleException ex) {
			JOptionPane.showMessageDialog(main_frame, "La categoria non accetta immagini doppie", "error", JOptionPane.ERROR_MESSAGE);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}