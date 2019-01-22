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

public class ImagesPreview extends JPanel {

	private int category_id; 
	private Album album; 

	public ImagesPreview(int category_id, Album album) {
		this.category_id = category_id;
		this.album = album;
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
		// this.setPreferredSize(new Dimension(AlbumFrame.WIDTH, 80));

		JPanel images_list = new JPanel(new FlowLayout());
		ManageImages.Category current_cat = this.album.findCategoryById(this.category_id);
		String images[] = current_cat.getImagesPath();


		if(!current_cat.isPasswordCategory()) {
			// TODO set max number of image in preview
			for(int i = 0; i<images.length; i++) {
				JLabel label_with_image = new JLabel();
				label_with_image.setSize(80, 80);
	
				// TODO better incapsulate with try catch
				BufferedImage img = readImage(images[i]);
				Image dimg = img.getScaledInstance(label_with_image.getWidth(), label_with_image.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(dimg);
	
				label_with_image.setIcon(imageIcon);
				images_list.add(label_with_image);
			}
		} else {
			try {
				JLabel label_with_image = new JLabel();
				label_with_image.setSize(80, 80);

				BufferedImage img = readImage(Album.LOCK_IMAGE_PATH);
				Image dimg = img.getScaledInstance(label_with_image.getWidth(), label_with_image.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(dimg);
				
				label_with_image.setIcon(imageIcon);
				images_list.add(label_with_image);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}

		if(images.length > 0) {
			JButton see_slideshow = new JButton(">>");
			see_slideshow.setSize(80, 80);
			see_slideshow.addActionListener(new SeeSlideshowListener(this.album, this.category_id));
			this.add(see_slideshow, BorderLayout.EAST);
		}

		this.add(images_list, BorderLayout.WEST);
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