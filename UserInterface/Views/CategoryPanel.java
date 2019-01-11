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

		JPanel description_panel = new JPanel(new FlowLayout());
		String description = current_cat.getDescription() == null ? "" : current_cat.getDescription().toString();
		JLabel description_label = new JLabel("<html>" + description + "</html>");
		description_label.setPreferredSize(new Dimension(300, 100));
		description_panel.add(description_label);
		description_panel.setPreferredSize(new Dimension(300, 100));

		header_panel.add(description_panel, BorderLayout.EAST);

		// images preview
		JPanel body_panel = new JPanel(new BorderLayout());
		ImagesPreview ip = new ImagesPreview(category_id, album);
		body_panel.add(ip, BorderLayout.WEST);

		add_image_button.addActionListener(new AddImageListener(ip, category_id, album, main_frame));
		mod_button.addActionListener(new EditCategoryListener(category_id, album, label, description_label));
		delete_button.addActionListener(new RemoveCategoryListener(category_id, album, this, sep, main_frame));

		this.add(header_panel, BorderLayout.NORTH);
		this.add(body_panel, BorderLayout.SOUTH);
	}
}