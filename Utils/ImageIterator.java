package Utils;

import ManageImages.*;
import Utils.*;

public class ImageIterator {
	private int index;
	private Category current_cat;

	public ImageIterator(Category current_cat) {
		this.index = 0;
		this.current_cat = current_cat;
	}

	public String currentImage() {
		return current_cat.getImagePathAt(index);
	}

	public String nextImage() {
		if(index < current_cat.imagesNumber() - 1) {
			index += 1;
		}
		return current_cat.getImagePathAt(index);
	}

	public String prevImage() {
		if(index > 0) {
			index -= 1;
		}
		return current_cat.getImagePathAt(index);
	}

	private void Log() {
		System.out.println(index);
	}

}