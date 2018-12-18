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
	private JLabel label_on_main_view;
	private JTextField txt_new_name;
	private JCheckBox accept_doubles; 
	private JFrame edit_frame;
	private EditCategoryListener main_view_listener;

	public UpdateCategoryListener(JFrame edit_frame, Category current_cat, Album album, JLabel label_on_main_view, JTextField txt, JCheckBox accept_doubles) {
		this.category_id = current_cat.getId();
		this.album = album;
		this.txt_new_name = txt; 
		this.label_on_main_view = label_on_main_view;
		this.main_view_listener = main_view_listener;
		this.edit_frame = edit_frame;
		this.accept_doubles = accept_doubles;
	}

	public void actionPerformed(ActionEvent event) {
		String new_cat_name = txt_new_name.getText();	
		Boolean return_value = album.updateCategoryName(category_id, new_cat_name);
		if(return_value) {
			this.label_on_main_view.setText(new_cat_name);
			album.setAcceptsDoubles(category_id, accept_doubles.isSelected());

			// hiding the edit frame
			edit_frame.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(null, "Operazione non consentita", "error", JOptionPane.ERROR_MESSAGE);
		}
	}

}