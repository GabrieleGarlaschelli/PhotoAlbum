package Utils;

import ManageImages.*;
import Utils.*;

public class ImageIterator {
	private static final String NO_IMAGES_PATH = "./data/No_image.png";
	private int max_image;
	private int index;
	private Category current_cat;

	public ImageIterator(Category current_cat) {
		this.index = 0;
		this.current_cat = current_cat;
		this.max_image = current_cat.imagesNumber();
	}

	public ImageIterator(Category current_cat, int index) {
		this.index = index;
		this.current_cat = current_cat;
		this.max_image = current_cat.imagesNumber();
	}

	public String currentImage() {
		if (max_image == 0) {
			return null;
		}
		return current_cat.getImagePathAt(index);
	}

	public String nextImage() {
		if (max_image == 0) {
			return NO_IMAGES_PATH;
		} else if(index < max_image - 1) {
			index += 1;
		}
		return current_cat.getImagePathAt(index);
	}

	public String prevImage() {
		if (max_image == 0) {
			return NO_IMAGES_PATH;
		} else if(index > 0) {
			index -= 1;
		}
		return current_cat.getImagePathAt(index);
	}

	public void refreshMaxImage() {
		this.max_image = current_cat.imagesNumber();
		if(index >= max_image) {
			index = max_image - 1;
		}
	}

	private void Log() {
		System.out.println(index);
	}

}