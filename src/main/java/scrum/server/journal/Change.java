package scrum.server.journal;

public class Change extends GChange {

	@Override
	public String toString() {
		return getUser() + " on " + getDateAndTime() + ": " + getParent() + " ." + getProperty();
	}

}