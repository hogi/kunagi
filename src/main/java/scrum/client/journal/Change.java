package scrum.client.journal;

import java.util.Map;


public class Change extends GChange {

	public Change(Map data) {
		super(data);
	}

	@Override
	public String toString() {
		return getUser() + " on " + getDateAndTime() + ": " + getParent() + " ." + getProperty();
	}

}