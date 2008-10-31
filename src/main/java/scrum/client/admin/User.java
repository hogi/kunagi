package scrum.client.admin;

public class User extends GUser {

	private String name;

	public User(String id, String name) {
		setId(id);
		setName(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
