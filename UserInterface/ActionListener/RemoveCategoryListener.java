package UserInterface.ActionListener;

import UserInterface.Views.*;
import ManageImages.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RemoveCategoryListener
							implements ActionListener {

	private int category_id;
	private Album album;
	private JSeparator separator;
	private CategoryPanel cat_panel;
	private AlbumFrame main_frame;

	public RemoveCategoryListener(int category_id, Album album, CategoryPanel cat_panel, JSeparator separator, AlbumFrame main_frame) {
		this.separator = separator;
		this.album = album;
		this.category_id = category_id;
		this.cat_panel = cat_panel;
		this.main_frame = main_frame;
	}

	public void actionPerformed(ActionEvent event) {
		Object[] options = { "Si", "No" };
		int dialogResult = JOptionPane.showOptionDialog(null, "Sei sicuro di volere eliminare la categoria?", "Eliminazione", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if(dialogResult == JOptionPane.YES_OPTION) {
			// action on object
			album.removeCategory(category_id);
			// refreshing a view
			main_frame.removeCategoryPanel(cat_panel, separator);
		}
	}

}