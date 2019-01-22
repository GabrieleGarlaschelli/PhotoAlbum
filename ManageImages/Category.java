package ManageImages;

import java.util.*;
import java.io.*;

public class Category 
						implements Serializable {
	private ArrayList<Image> image_list;
	private String name;
	private String description;
	private Boolean accept_doubles;
	private int id;

	// Constructors

	protected Category(String name, int id) {
		image_list = new ArrayList<Image>(10); // TODO constantize it
		this.name = name;
		this.accept_doubles = false; // TODO constantize it
		this.id = id;
	}

	// setters and getters

	public String getName() {
		return this.name;
	}

	protected void setName(String new_name) {
		this.name = new_name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String new_name) {
		this.description = new_name;
	}

	public Boolean acceptDoubles() {
		return this.accept_doubles;
	}

	public int getId() {
		return this.id;
	}

	protected void setAcceptDoubles(Boolean flag) {
		this.accept_doubles = flag;
	}

	// methods

	public String getImagePathAt(int index) {
		return image_list.get(index).getPath();
	}

	public Boolean isPasswordCategory() {
		return false;
	}

	public int imagesNumber() {
		return image_list.size();
	}

	public String[] getImagesPath() {
		String paths[] = new String[image_list.size()];
		for(int i = 0; i < paths.length; i++) {
			paths[i] = image_list.get(i).getPath();
		}
		return paths;
	}

	protected Boolean addImage(Image image_to_add) {
		if(this.accept_doubles) {
			image_list.add(image_to_add);
			return true;
		} else {
			if(this.imageExist(image_to_add.getPath())) {
				return false; 
			} else {
				image_list.add(image_to_add);
				return true;
			}
		}
	}

	protected Boolean addImage(String path) {
		Image image_to_add = new Image(path);
		return this.addImage(image_to_add);
	}

	protected Boolean imageExist(String path) {
		for(Image i: image_list) {
			if(path.equals(i.getPath())) {
				return true;
			}
		}
		return false; 
	}

	public boolean isPasswordCorrect(char[] input) { //useless
		return true;
	} 

	@Override
	public String toString() {
		return this.name;
	}

	protected void removeImage(Image image_to_remove) { 
		// TODO
	}

}
