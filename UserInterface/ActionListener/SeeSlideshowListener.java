package UserInterface.ActionListener;

import UserInterface.Views.*;
import ManageImages.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SeeSlideshowListener 
							implements ActionListener {
	private Album album; 
	private int category_id;

	public SeeSlideshowListener(Album album, int category_id) {
		this.album = album;
		this.category_id = category_id;
	}

	public void actionPerformed(ActionEvent event) {
		Slideshow slideshow = new Slideshow(this.category_id, this.album);
	}
}