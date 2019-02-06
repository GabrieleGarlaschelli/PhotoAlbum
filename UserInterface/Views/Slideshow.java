package UserInterface.Views;

import ManageImages.*;
import UserInterface.ActionListener.*;
import Utils.*;
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

		// TODO menu (delete, move, visualizing the nth image)

		JMenuBar menuBar = new JMenuBar();;
		JMenu menu_cat = new JMenu("Azioni");
		JMenuItem remove_image = new JMenuItem("Rimuovi Immagine");

		menu_cat.add(remove_image);
		menuBar.add(menu_cat);
		this.setJMenuBar(menuBar);

		// big image

		JLabel big_label_with_image = new JLabel();
		big_label_with_image.setSize(600, 500);

		ImageIcon big_image_icon = new ImageIcon();
		


		big_image_icon = getImageIcon(iterator.currentImage(), big_label_with_image);





		big_label_with_image.setIcon(big_image_icon);

		JButton next_image = new JButton(">>");
		next_image.setPreferredSize(new Dimension(50, 100));

		JButton prev_image = new JButton("<<");
		prev_image.setPreferredSize(new Dimension(50, 100));

		main_panel.add(prev_image);
		main_panel.add(big_label_with_image);
		main_panel.add(next_image);

		prev_image.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// BufferedImage prev_big_img = readImage(iterator.prevImage());
				// Image prev_big_dmig = prev_big_img.getScaledInstance(big_label_with_image.getWidth(), big_label_with_image.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon prev_big_image_icon = getImageIcon(iterator.prevImage(), big_label_with_image);
				big_label_with_image.setIcon(prev_big_image_icon);
			}
		});

		next_image.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// BufferedImage next_big_img = readImage(iterator.nextImage());
				// Image next_big_dmig = next_big_img.getScaledInstance(big_label_with_image.getWidth(), big_label_with_image.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon next_big_image_icon = getImageIcon(iterator.nextImage(), big_label_with_image);
				big_label_with_image.setIcon(next_big_image_icon);
			}
		});

		remove_image.addActionListener(new RemoveImageListener(category_id, album, cat_panel, iterator, prev_image, next_image, image_preview));

		// image list

		// scroll
		// JScrollPane scrollPanel = new JScrollPane(images_list);
		// scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  	// scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		// scrollPanel.setBounds(100, 100, 100, 100);
		// images_list.setAutoscrolls(true);
		// main_panel.add(images_list);
		// JPanel content_pane = new JPanel(new BorderLayout());
		// content_pane.add(scrollPanel);
		// this.setContentPane(content_pane);

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