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
	private CategoryPanel cat_panel;
	private ImagesPreview image_preview;

	public SeeSlideshowListener(Album album, int category_id, CategoryPanel cat_panel, ImagesPreview image_preview) {
		this.album = album;
		this.category_id = category_id;
		this.cat_panel = cat_panel;
		this.image_preview = image_preview;
	}

	public void actionPerformed(ActionEvent event) {
		Category current_cat = album.findCategoryById(this.category_id);

		if(current_cat.isPasswordCategory()) {
			JFrame insert_password_frame = new JFrame("Inserisci la password");
			JPanel align_panel = new JPanel(new BorderLayout());
			JPanel insert_password_panel = new JPanel();
			insert_password_panel.setLayout(new BoxLayout(insert_password_panel, BoxLayout.PAGE_AXIS));

			JPasswordField password_field = new JPasswordField(20);
			insert_password_panel.add(password_field);

			JButton button = new JButton("Ok");
			insert_password_panel.add(button);

			SeeSlideshowListener self = this;

			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					char [] pass = password_field.getPassword();
					if(current_cat.isPasswordCorrect(pass)) {
						insert_password_frame.setVisible(false);
						Slideshow slideshow = new Slideshow(self.category_id, self.album, self.cat_panel, self.image_preview);
					}
				}
			});

			align_panel.add(insert_password_panel, BorderLayout.WEST);
			align_panel.setSize(100, 100);
			insert_password_frame.setSize(new Dimension(300, 90));
			insert_password_frame.setLocation(350, 350);
			insert_password_frame.add(align_panel);
			insert_password_frame.setVisible(true);
		} else {
			Slideshow slideshow = new Slideshow(this.category_id, this.album, this.cat_panel, this.image_preview);
		}

		
	}
}