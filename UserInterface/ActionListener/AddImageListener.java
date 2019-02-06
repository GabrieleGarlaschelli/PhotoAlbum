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
	private JLabel info_lable;
	private Boolean is_link;

	public AddImageListener(ImagesPreview panel, int category_id, Album album, JFrame main_frame, JLabel info_lable, Boolean is_link) {
		this.image_preview = panel;
		this.category_id = category_id;
		this.album = album;
		this.main_frame = main_frame;
		this.info_lable = info_lable;
		this.is_link = is_link;
	}

	public void actionPerformed(ActionEvent event) {
		Category current_cat = album.findCategoryById(this.category_id);

		try {
			if(is_link) {
				JFrame frame = new JFrame();
				String message = "Inserisci l'URL dell'imagine";
				String text = JOptionPane.showInputDialog(frame, message);
				if (text != null) {
				  album.addImageToCategory(this.category_id, text);
				  info_lable.setText(current_cat.getCreatedAt() + ", " + current_cat.imagesNumber() + " foto");
					image_preview.refresh();
				}
				return;
			}

			JFileChooser fc = new JFileChooser("Aggiungi Immagine");
			int retrun_val = fc.showOpenDialog(image_preview);

			if(retrun_val == JFileChooser.APPROVE_OPTION) {
				// TODO multiple files
				File f = fc.getSelectedFile();
				album.addImageToCategory(this.category_id, f.getCanonicalPath());

				info_lable.setText(current_cat.getCreatedAt() + ", " + current_cat.imagesNumber() + " foto");
				image_preview.refresh();
			}
		} catch(NotAcceptDoubleException ex) {
			JOptionPane.showMessageDialog(main_frame, "La categoria non accetta immagini doppie", "error", JOptionPane.ERROR_MESSAGE);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}