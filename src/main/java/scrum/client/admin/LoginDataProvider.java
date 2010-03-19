package scrum.client.admin;

public interface LoginDataProvider {

	String getUsername();

	String getPassword();

	void setFailed();

	void clear();

}
