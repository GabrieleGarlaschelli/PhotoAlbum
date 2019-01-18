package UserInterface.ActionListener;

import UserInterface.Views.*;
import ManageImages.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NewCategoryListener 
							implements ActionListener {
	private Album album; 
	private JPanel album_panel; 
	private JTextField name_texfield;
	private JTextField description_texfield;
	private JCheckBox di_check;
	private JCheckBox with_pass_check; 
	private JFrame form;
	private AlbumFrame album_frame;

	public NewCategoryListener(JFrame form, JPanel album_panel, Album album, JTextField name_texfield, JTextField description_texfield, JCheckBox di_check, JCheckBox with_pass_check, AlbumFrame album_frame) {
		this.album = album;
		this.album_panel = album_panel;
		this.form = form;
		this.album = album;
		this.name_texfield = name_texfield;
		this.di_check = di_check;
		this.album_frame = album_frame;
		this.description_texfield = description_texfield;
		this.with_pass_check = with_pass_check;
	}

	public void actionPerformed(ActionEvent event) {
		String category_name = name_texfield.getText();
		if(category_name != "") {
			int id = -1;

			if(with_pass_check.isSelected()) {
				String password = "";
				id = this.album.createCategory(category_name, password);
			} else {
				id = this.album.createCategory(category_name);
			}

			if(id == -1) { // TODO constantize it
				JOptionPane.showMessageDialog(null, "Categoria esistente", "error", JOptionPane.ERROR_MESSAGE);
			} else {
				this.album.setAcceptsDoubles(id, di_check.isSelected());
				this.album.updateCategoryDescription(id, description_texfield.getText());
				JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
				album_panel.add(sep);
				CategoryPanel current_panel = new CategoryPanel(id, this.album, sep, this.album_frame);
				album_panel.add(current_panel);
				album_panel.revalidate();
				album_panel.repaint();

				// hiding the edit frame
				form.setVisible(false);
			}
		}
	}
}