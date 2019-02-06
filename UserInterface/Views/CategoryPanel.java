package UserInterface.Views;

import ManageImages.*;
import UserInterface.ActionListener.*;
import java.awt.*;
import javax.swing.*;

public class CategoryPanel extends JPanel {
	private int category_id;
	private Album album;
	private JSeparator sep;
	private AlbumFrame main_frame;
	private ImagesPreview ip;

	public CategoryPanel(int category_id, Album album, JSeparator sep, AlbumFrame main_frame) {
		this.category_id = category_id;
		this.album = album;
		this.sep = sep;
		this.main_frame = main_frame;
		this.draw();
	}

	private void draw() {
		this.setLayout(new BorderLayout());
		JPanel header_panel = new JPanel(new BorderLayout());
		JPanel label_panel = new JPanel(new FlowLayout());

		Category current_cat = album.findCategoryById(category_id);
		current_cat.setCategoryPanel(this);

		JLabel label = new JLabel(current_cat.getName());
		Font font = new Font("Courier", Font.BOLD,12);
		label.setFont(font);
		label_panel.add(label);

		JButton mod_button = new JButton("Modifica");
		label_panel.add(mod_button);

		JButton delete_button = new JButton("Elimina");
		label_panel.add(delete_button);

		JButton add_image_button = new JButton("Aggiungi Immagine");
		label_panel.add(add_image_button);

		JButton add_image_from_link_button = new JButton("Aggiungi Immagine da Link");
		label_panel.add(add_image_from_link_button);

		JLabel info = new JLabel(current_cat.getCreatedAt() + ", " + current_cat.imagesNumber() + " foto");
		label_panel.add(info);

		header_panel.add(label_panel, BorderLayout.WEST);

		JPanel description_panel = new JPanel(new FlowLayout());
		String description = current_cat.getDescription() == null ? "" : current_cat.getDescription().toString();
		JLabel description_label = new JLabel("<html><div style=\"float: left\">" + description + "</div></html>");
		description_label.setPreferredSize(new Dimension(1000, 80));
		description_panel.add(description_label);
		description_panel.setPreferredSize(new Dimension(1000, 80));

		header_panel.add(description_panel, BorderLayout.SOUTH);

		// images preview
		JPanel body_panel = new JPanel(new BorderLayout());
		ip = new ImagesPreview(category_id, album, this);
		body_panel.add(ip, BorderLayout.WEST);

		add_image_from_link_button.addActionListener(new AddImageListener(ip, category_id, album, main_frame, info, true));
		add_image_button.addActionListener(new AddImageListener(ip, category_id, album, main_frame, info, false));
		mod_button.addActionListener(new EditCategoryListener(category_id, album, label, description_label));
		delete_button.addActionListener(new RemoveCategoryListener(category_id, album, this, sep, main_frame));

		this.add(header_panel, BorderLayout.NORTH);
		this.add(body_panel, BorderLayout.SOUTH);
	}

	public void refresh() {
		this.removeAll();
		this.draw();
		this.revalidate();
		this.repaint();
	}
}