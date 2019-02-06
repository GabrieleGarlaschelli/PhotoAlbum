package ManageImages;

import java.io.*;

class Image 
				implements Serializable {
	private String file_path;

	public Image(String file_path) {
		this.file_path = file_path;
	}

	public String getPath() {
		return file_path;
	}

	public void setPath(String path) {
		this.file_path = file_path;
	}

	@Override
	public String toString() {
		return file_path;
	}
}
