package scrum.server.admin;

import ilarkesto.base.Crypt;
import ilarkesto.base.Utl;

import java.util.ArrayList;
import java.util.List;

public class User extends GUser {

	private String password;

	@Override
	public String getRealName() {
		return getName();
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
	public void ensureIntegrity() {
		super.ensureIntegrity();
		if (!isColorSet()) setColor(getDefaultColor());
	}

	public boolean isVisibleFor(User user) {
		return true;
	}

	@Override
	public String toString() {
		return getName();
	}

	public static String getDefaultColor() {
		return Utl.randomElement(getDefaultColors());
	}

	public static List<String> getDefaultColors() {
		List<String> colors = new ArrayList<String>();
		colors.add("black");
		colors.add("darkred");
		colors.add("darkgreen");
		colors.add("darkblue");
		colors.add("darkorange");
		colors.add("darkorchid");
		colors.add("darkslateblue");
		colors.add("darkgray");
		colors.add("orange");
		colors.add("green");
		return colors;
	}
}
