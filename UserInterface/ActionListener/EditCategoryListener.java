package UserInterface.ActionListener;

import UserInterface.Views.*;
import ManageImages.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EditCategoryListener 
							implements ActionListener {
	private int category_id;
	private Album album;
	private JLabel name_label; 
	private JLabel description_label;

	public EditCategoryListener(int id, Album album, JLabel name_label, JLabel description_label) {
		super();
		this.category_id = id;
		this.album = album;
		this.name_label = name_label;
		this.description_label = description_label;
	}

	public void actionPerformed(ActionEvent event) {
		Category current_cat = album.findCategoryById(category_id);
		String category_name = current_cat.getName();
		CategoryFormInputs cat_form = new CategoryFormInputs(current_cat, this.album, this.name_label, this.description_label, category_name, null, null);
		cat_form.setButtonListenerToEdit();
	} 
}