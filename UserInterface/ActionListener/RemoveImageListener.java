package UserInterface.ActionListener;

import UserInterface.Views.*;
import ManageImages.*;
import Utils.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RemoveImageListener
							implements ActionListener {

	private int category_id;
	private Album album;
	private CategoryPanel cat_panel;
	private ImageIterator iterator;
	private JButton prev_image;
	private JButton next_image;
	private ImagesPreview image_preview;

	public RemoveImageListener(int category_id, Album album, CategoryPanel cat_panel, ImageIterator iterator, JButton prev_image, JButton next_image, ImagesPreview image_preview) {
		this.album = album;
		this.category_id = category_id;
		this.cat_panel = cat_panel;
		this.iterator = iterator;
		this.prev_image = prev_image;
		this.next_image = next_image;
		this.image_preview = image_preview;
	}

	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand() == "removed_istantanely") {
			String current_image = iterator.currentImage();
			if(current_image != null) {
				album.removeImageFromCategory(category_id, current_image);
			}

			// refreshing a view
			cat_panel.refresh();
			image_preview.refresh();
			iterator.refreshMaxImage();
			next_image.doClick();
			prev_image.doClick();
			return;
		}

		Object[] options = { "Si", "No" };
		int dialogResult = JOptionPane.showOptionDialog(null, "Sei sicuro di volere eliminare l'immagine?", "Eliminazione", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if(dialogResult == JOptionPane.YES_OPTION) {
			// action on object
			String current_image = iterator.currentImage();
			if(current_image != null) {
				album.removeImageFromCategory(category_id, current_image);
			}

			// refreshing a view
			cat_panel.refresh();
			image_preview.refresh();
			iterator.refreshMaxImage();
			next_image.doClick();
			prev_image.doClick();
		}
	}

}