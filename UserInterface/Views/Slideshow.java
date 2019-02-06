package UserInterface.Views;

import ManageImages.*;
import UserInterface.ActionListener.*;
import Utils.*;
import Exceptions.*;
import UserInterface.Component.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.net.URL;

public class Slideshow extends JFrame {

	private int category_id;
	private Album album; 
	private CategoryPanel cat_panel;
	private ImagesPreview image_preview;
	private ImageIterator image_iterator;

	public Slideshow(int category_id, Album album, CategoryPanel cat_panel, ImagesPreview image_preview) {
		super("Slideshow");
		this.category_id = category_id;
		this.album = album;
		this.cat_panel = cat_panel;
		this.image_preview = image_preview;
		this.draw();
	}

	public void refresh() {
		this.removeAll();
		this.draw();
		this.revalidate();
		this.repaint();
	}

	private void draw() {                   
		this.setSize(730, 520);
		this.setLocation(120, 120);

		ImageIterator iterator;
		Category current_category = album.findCategoryById(category_id);
		if(image_iterator == null) {
			iterator = new ImageIterator(current_category);
		} else {
			iterator = image_iterator;
		}

		JPanel main_panel = new JPanel();
		main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.LINE_AXIS));


		JMenuBar menuBar = new JMenuBar();;
		JMenu menu_cat = new JMenu("Azioni");
		JMenuItemRemove remove_image = new JMenuItemRemove("Rimuovi Immagine");
		JMenuItem move_image = new JMenuItem("Sposta Immagine");

		menu_cat.add(remove_image);
		menu_cat.add(move_image);
		menuBar.add(menu_cat);
		this.setJMenuBar(menuBar);

		// big image
		JLabel big_label_with_image = new JLabel();
		big_label_with_image.setSize(600, 500);
		ImageIcon big_image_icon = new ImageIcon();
		big_image_icon = getImageIcon(iterator.currentImage(), big_label_with_image);
		big_label_with_image.setIcon(big_image_icon);

		//next image
		JButton next_image = new JButton(">>");
		next_image.setPreferredSize(new Dimension(50, 100));

		//prev image
		JButton prev_image = new JButton("<<");
		prev_image.setPreferredSize(new Dimension(50, 100));

		main_panel.add(prev_image);
		main_panel.add(big_label_with_image);
		main_panel.add(next_image);

		prev_image.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ImageIcon prev_big_image_icon = getImageIcon(iterator.prevImage(), big_label_with_image);
				big_label_with_image.setIcon(prev_big_image_icon);
			}
		});

		next_image.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ImageIcon next_big_image_icon = getImageIcon(iterator.nextImage(), big_label_with_image);
				big_label_with_image.setIcon(next_big_image_icon);
			}
		});

		JFrame self = this;
		remove_image.addActionListener(new RemoveImageListener(category_id, album, cat_panel, iterator, prev_image, next_image, image_preview));
		move_image.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String[] options_for_combo = album.getCategoriesName();
				JComboBox<String> combo = new JComboBox<String>(options_for_combo);
				String[] options = {"OK", "annulla"};
				int selection = JOptionPane.showOptionDialog(null, combo, "Seleziona una categoria", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				if (selection == 0) {
				  String path = iterator.currentImage();
					remove_image.removeIstantanely();
					String category_name = (String)combo.getSelectedItem();
					Category destination = album.findCategoryByName(category_name);
					try {
						album.addImageToCategory(destination.getId(), path);
					} catch(NotAcceptDoubleException ex) {
						JOptionPane.showMessageDialog(self, "La categoria non accetta immagini doppie", "error", JOptionPane.ERROR_MESSAGE);
					}
					destination.refreshPanel();
				}
			}
		});

		this.getContentPane().add(main_panel);
		this.setVisible(true);
	}

	private void refreshIterator(int index) {
		Category current_category = album.findCategoryById(category_id);
		this.image_iterator = new ImageIterator(current_category, index);
	}

	private BufferedImage readImage(String path) throws Exception {
		BufferedImage img = null;
		img = ImageIO.read(new File(path));
		return img;
	}

	private ImageIcon getImageIcon(String path, JLabel big_label_with_image) {
		ImageIcon big_image_icon = new ImageIcon();
		Boolean raised_expection = false;
		try {
			BufferedImage img = readImage(path);
			Image dimg = img.getScaledInstance(big_label_with_image.getWidth(), big_label_with_image.getHeight(), Image.SCALE_SMOOTH);
			big_image_icon = new ImageIcon(dimg);
		} catch(Exception ex) {
			raised_expection = true;
		}

		if(raised_expection) {
			try {
				URL url = new URL(path);
				Image image = ImageIO.read(url);
				image = image.getScaledInstance(big_label_with_image.getWidth(), big_label_with_image.getHeight(), Image.SCALE_SMOOTH);
				big_image_icon = new ImageIcon(image);
			} catch(Exception ex) {
				ex.printStackTrace();
			}	
		}
		return big_image_icon;
	}
}