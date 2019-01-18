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

public class Slideshow extends JFrame {
	public Slideshow(int category_id, Album album) {
		super("Slideshow");                     
		this.setSize(730, 520);
		this.setLocation(120, 120);

		Category current_category = album.findCategoryById(category_id);
		ImageIterator iterator = new ImageIterator(current_category);

		JPanel main_panel = new JPanel();
		main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.LINE_AXIS));

		// TODO menu (delete, move, visualizing the nth image)


		// big image

		JLabel big_label_with_image = new JLabel();
		big_label_with_image.setSize(600, 500);

		BufferedImage big_img = readImage(iterator.currentImage());
		Image big_dmig = big_img.getScaledInstance(big_label_with_image.getWidth(), big_label_with_image.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon big_image_icon = new ImageIcon(big_dmig);
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
				BufferedImage prev_big_img = readImage(iterator.prevImage());
				Image prev_big_dmig = prev_big_img.getScaledInstance(big_label_with_image.getWidth(), big_label_with_image.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon prev_big_image_icon = new ImageIcon(prev_big_dmig);
				big_label_with_image.setIcon(prev_big_image_icon);
			}
		});

		next_image.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				BufferedImage next_big_img = readImage(iterator.nextImage());
				Image next_big_dmig = next_big_img.getScaledInstance(big_label_with_image.getWidth(), big_label_with_image.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon next_big_image_icon = new ImageIcon(next_big_dmig);
				big_label_with_image.setIcon(next_big_image_icon);
			}
		});

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

	private BufferedImage readImage(String path) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(path));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return img;
	}
}