package UserInterface.Views;

import ManageImages.*;
import UserInterface.ActionListener.*;
import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.net.URL;

public class ImagesPreview extends JPanel {

	private int category_id; 
	private Album album; 
	private CategoryPanel cat_panel;

	public ImagesPreview(int category_id, Album album, CategoryPanel cat_panel) {
		this.category_id = category_id;
		this.album = album;
		this.cat_panel = cat_panel;
		this.drawPreview();
	}

	public void refresh() {
		this.removeAll();

		this.drawPreview();

		this.revalidate();
		this.repaint();
	}

	private void drawPreview() {
		this.setLayout(new BorderLayout());

		JPanel images_list = new JPanel(new FlowLayout());
		ManageImages.Category current_cat = this.album.findCategoryById(this.category_id);
		String images[] = current_cat.getImagesPath();


		if(!current_cat.isPasswordCategory()) {
			for(int i = 0; i<images.length && i<10; i++) {
				JLabel label_with_image = new JLabel();
				label_with_image.setSize(80, 80);
				setImageOnLabel(images[i], label_with_image);
				images_list.add(label_with_image);
			}
		} else {
			try {
				JLabel label_with_image = new JLabel();
				label_with_image.setSize(80, 80);
				setImageOnLabel(Album.LOCK_IMAGE_PATH, label_with_image);
				images_list.add(label_with_image);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}

		if(images.length > 0) {
			JButton see_slideshow = new JButton(">>");
			see_slideshow.setSize(80, 80);
			see_slideshow.addActionListener(new SeeSlideshowListener(this.album, this.category_id, this.cat_panel, this));
			this.add(see_slideshow, BorderLayout.EAST);
		}

		this.add(images_list, BorderLayout.WEST);
	}

	private BufferedImage readImage(String path) throws Exception {
		BufferedImage img = null;
		img = ImageIO.read(new File(path));
		return img;
	}

	private void setImageOnLabel(String image_path, JLabel label) {
		ImageIcon imageIcon = new ImageIcon();
		Boolean raised_expection = false;
		try {
			BufferedImage img = readImage(image_path);
			Image dimg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(dimg);
		} catch(Exception ex) {
			raised_expection = true;
		}

		if(raised_expection) {
			try {
				URL url = new URL(image_path);
				Image image = ImageIO.read(url);
				image = image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(image);
			} catch(Exception ex) {
				ex.printStackTrace();
			}	
		}

		label.setIcon(imageIcon);

	}
}