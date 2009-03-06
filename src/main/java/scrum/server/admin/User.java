package scrum.server.admin;

import ilarkesto.base.Crypt;

public class User extends GUser {

	private String password;

	@Override
	public String getRealName() {
		return getName();
	}

	@Override
	public boolean isAdmin() {
		return false;
	}

	@Override
	public boolean matchesPassword(String password) {
		return Crypt.cryptWebPassword(password).equals(this.password);
	}

	@Override
	public void setPassword(String value) {
		this.password = Crypt.cryptWebPassword(value);
		fireModified();
	}

	@Override
	public String getAutoLoginString() {
		return null;
	}

	@Override
	public String toString() {
		return getName();
	}

}
