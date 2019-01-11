package UserInterface.ActionListener;

import UserInterface.Views.*;
import ManageImages.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UpdateCategoryListener
							implements ActionListener {

	private int category_id;
	private Album album;
	private JLabel name_label;
	private JLabel description_label;
	private JTextField txt_new_name;
	private JTextField txt_new_description;
	private JCheckBox accept_doubles; 
	private JFrame edit_frame;
	private EditCategoryListener main_view_listener;

	public UpdateCategoryListener(JFrame edit_frame, Category current_cat, Album album, JLabel name_label, JLabel description_label, JTextField txt_name, JTextField txt_description, JCheckBox accept_doubles) {
		this.category_id = current_cat.getId();
		this.album = album;
		this.txt_new_name = txt_name; 
		this.txt_new_description = txt_description; 
		this.name_label = name_label;
		this.description_label = description_label;
		this.main_view_listener = main_view_listener;
		this.edit_frame = edit_frame;
		this.accept_doubles = accept_doubles;
	}

	public void actionPerformed(ActionEvent event) {
		String new_cat_name = txt_new_name.getText();	
		String new_cat_description = txt_new_description.getText();
		Boolean return_value = album.updateCategoryName(category_id, new_cat_name);

		if(return_value) {
			this.name_label.setText(new_cat_name);
			this.description_label.setText(new_cat_description);
			album.setAcceptsDoubles(category_id, accept_doubles.isSelected());
			album.updateCategoryDescription(category_id, new_cat_description);

			// hiding the edit frame
			edit_frame.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(null, "Operazione non consentita", "error", JOptionPane.ERROR_MESSAGE);
		}
	}

}