package UserInterface.Views;

import ManageImages.*;
import UserInterface.ActionListener.*;
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
		this.setSize(600, 600);
		this.setLocation(120, 120);

		Category current_category = album.findCategoryById(category_id);
		String images[] = current_category.getImagesPath();

		JPanel main_panel = new JPanel();
		main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.PAGE_AXIS));

		// menu (delete, move, visualizing the nth image)


		// big image

		JLabel big_label_with_image = new JLabel();
		big_label_with_image.setSize(600, 500);

		BufferedImage big_img = readImage(images[0]);
		Image big_dmig = big_img.getScaledInstance(big_label_with_image.getWidth(), big_label_with_image.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon big_image_icon = new ImageIcon(big_dmig);
		big_label_with_image.setIcon(big_image_icon);

		main_panel.add(big_label_with_image);

		// image list

		JPanel images_list = new JPanel();
		// TODO set max number of image in preview
		for(int i = 0; i<images.length; i++) {
			JLabel label_with_image = new JLabel();
			label_with_image.setSize(80, 80);

			// TODO better incapsulate with try catch
			// TODO label for no images
			BufferedImage img = readImage(images[i]);
			Image dimg = img.getScaledInstance(label_with_image.getWidth(), label_with_image.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);

			label_with_image.setIcon(imageIcon);
			images_list.add(label_with_image);
		}

		// scroll
		JScrollPane scrollPanel = new JScrollPane(images_list);
		scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPanel.setBounds(100, 100, 100, 100);
		images_list.setAutoscrolls(true);
		main_panel.add(images_list);
		JPanel content_pane = new JPanel(new BorderLayout());
		content_pane.add(scrollPanel);

		this.setContentPane(content_pane);
		this.add(main_panel);
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