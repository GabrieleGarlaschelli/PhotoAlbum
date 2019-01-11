package UserInterface.ActionListener;

import UserInterface.Views.*;
import ManageImages.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CreateCategoryListener 
							implements ActionListener {
	private Album album; 
	private JPanel album_panel; 
	private AlbumFrame album_frame;

	public CreateCategoryListener(Album album, JPanel album_panel, AlbumFrame album_frame) {
		this.album = album;
		this.album_panel = album_panel;
		this.album_frame = album_frame;
	}

	public void actionPerformed(ActionEvent event) {
		CategoryFormInputs cat_form = new CategoryFormInputs(null, this.album, null, null, "Nuova Categoria", this.album_panel, this.album_frame);
		cat_form.setButtonListenerToNew();
	}
}