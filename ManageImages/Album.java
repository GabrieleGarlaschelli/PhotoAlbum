package ManageImages;

import Exceptions.*; 
import java.util.*;
import java.io.*;

public class Album 
				implements Serializable {
	private ArrayList<Category> category_list;
	private int last_used_id; 
	private static final String RECOVERY_PATH = "./data/recovery.dat";


	public Album() {
		category_list = new ArrayList<Category>(10); // TODO constantize it
	}

	public int getCategoriesLength() {
		return category_list.size();
	}

	public String[] getCategoriesName() {
		String names[] = new String[category_list.size()];
		for(int i = 0; i < names.length; i++) {
			names[i] = category_list.get(i).getName();
		}
		return names; 
	}

	public int[] getCategoriesId() {
		int names[] = new int[category_list.size()];
		for(int i = 0; i < names.length; i++) {
			names[i] = category_list.get(i).getId();
		}
		return names; 
	}

	public Boolean updateCategoryName(int id, String new_name) {
		Category cat_to_change = findCategoryById(id);
		if(categoryExist(new_name) || cat_to_change == null) {
			return false;
		} else {
			cat_to_change.setName(new_name);
			return true; 
		}
	}

	public void setAcceptsDoubles(int id, Boolean flag) {
		Category c = findCategoryById(id);
		c.setAcceptDoubles(flag);
	}

	public void removeCategory(int category_id) {
		Category c = findCategoryById(category_id);
		category_list.remove(c);
	}

	public int createCategory(String name) {
		if(categoryExist(name)) {
			return -1;
		}
		Category cat_to_add = new Category(name, this.createId());
		category_list.add(cat_to_add);
		return cat_to_add.getId(); 
	}

	// TODO now is returning false both if category does not exist or if image is double
	// do it with expection
	public Boolean addImageToCategory(String cat_name, String path) throws NotAcceptDoubleException {
		Category current_cat = this.findCategoryByName(cat_name);
		if(current_cat != null) {
			if(current_cat.addImage(path)) {
				return true;
			} else {
				throw new NotAcceptDoubleException();
			}
		} else {
			return false;
		}
	}

	public Boolean addImageToCategory(int id, String path) throws NotAcceptDoubleException {
		Category current_cat = this.findCategoryById(id);
		if(current_cat != null) {
			if(current_cat.addImage(path)) {
				return true; 
			} else {
				throw new NotAcceptDoubleException();
			}
		} else {
			return false;
		}
	}

	public Boolean categoryExist(String cat_to_search_name) {
		for(Category c: this.category_list) {
			if (c.getName().equals(cat_to_search_name)) {
				return true;
			}
		}
		return false;
	}

	public void saveOnFile() throws IOException {
		FileOutputStream file_out = new FileOutputStream(RECOVERY_PATH);
		ObjectOutputStream obj_out = new ObjectOutputStream(file_out);
		obj_out.writeObject(this);
		obj_out.close();
	}

	public static Album recoverFromFile() throws IOException, ClassNotFoundException {
		FileInputStream file_in = new FileInputStream(RECOVERY_PATH);
		if(file_in.available() <= 0) {
			return new Album();
		}
		ObjectInputStream obj_in = new ObjectInputStream(file_in);
		Album to_return = (Album)obj_in.readObject();
		obj_in.close();
		return to_return;
	}

	@Override
	public String toString() {
		String final_string = ""; 
		for(Category c: this.category_list) {
			final_string += c.toString();
			final_string += "\n";
		}
		return final_string;
	}


	public Category findCategoryByName(String name) {
		for(Category c: this.category_list) {
			if (c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}

	public Category findCategoryById(int id) {
		for(Category c: this.category_list) { 
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}

	private int createId() {
		this.last_used_id += 1;
		return last_used_id;
	}
}
