package UserInterface.Views;

import ManageImages.*;
import UserInterface.ActionListener.*;
import java.awt.*;
import javax.swing.*;

public class CategoryPanel extends JPanel {
	public CategoryPanel(int category_id, Album album, JSeparator sep, AlbumFrame main_frame) {
		this.setLayout(new BorderLayout());
		JPanel header_panel = new JPanel(new BorderLayout());
		JPanel label_panel = new JPanel(new FlowLayout());

		Category current_cat = album.findCategoryById(category_id);

		JLabel label = new JLabel(current_cat.getName());
		label_panel.add(label);

		JButton mod_button = new JButton("Modifica");
		label_panel.add(mod_button);

		JButton delete_button = new JButton("Elimina");
		label_panel.add(delete_button);

		JButton add_image_button = new JButton("Aggiungi Immagine");
		label_panel.add(add_image_button);

		header_panel.add(label_panel, BorderLayout.WEST);

		// images preview
		JPanel body_panel = new JPanel(new BorderLayout());
		ImagesPreview ip = new ImagesPreview(category_id, album);
		body_panel.add(ip, BorderLayout.WEST);

		add_image_button.addActionListener(new AddImageListener(ip, category_id, album, main_frame));
		mod_button.addActionListener(new EditCategoryListener(category_id, album, label));
		delete_button.addActionListener(new RemoveCategoryListener(category_id, album, this, sep, main_frame));

		this.add(header_panel, BorderLayout.NORTH);
		this.add(body_panel, BorderLayout.SOUTH);
	}
}