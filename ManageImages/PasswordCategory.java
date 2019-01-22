package ManageImages;

import java.util.*;

class PasswordCategory extends Category {
	private char[] password;

	public PasswordCategory(String name, int id, char[] password) {
		super(name, id);
		this.password = password;
	}

	public char[] getPassword() {
		return this.password;
	}

	protected void setPassword(char[] new_pass) {
		this.password = new_pass;
	}

	@Override
	public Boolean isPasswordCategory() {
		return true;
	}

	public boolean isPasswordCorrect(char[] input) {
    boolean isCorrect = true;
    char[] correctPassword = this.password;

    if (input.length != correctPassword.length) {
        isCorrect = false;
    } else {
        isCorrect = Arrays.equals(input, correctPassword);
    }

    return isCorrect;
	}
	
}
