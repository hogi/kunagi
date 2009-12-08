package scrum.server.files;

import scrum.server.admin.User;

public class File extends GFile {

	public void updateNumber() {
		if (isNumber(0)) setNumber(getProject().generateFileNumber());
	}

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	@Override
	public String toString() {
		return getFilename();
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		updateNumber();
	}

}