package ManageImages;

class PasswordCategory extends Category {
	private String password;

	public PasswordCategory(String name, int id, String password) {
		super(name, id);
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	protected void setPassword(String new_pass) {
		this.password = new_pass;
	}

	@Override
	public Boolean isPasswordCategory() {
		return true;
	}
	
}
