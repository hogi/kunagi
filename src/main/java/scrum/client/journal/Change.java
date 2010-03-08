package scrum.client.journal;

import java.util.Comparator;
import java.util.Map;

public class Change extends GChange {

	public Change(Map data) {
		super(data);
	}

	public String getLabel() {
		return getFieldChangeLabel();
	}

	private String getFieldChangeLabel() {
		String value = getValue();
		if (value == null) return "created " + getFieldLabel();
		return "changed " + getFieldLabel() + " from:";
	}

	private String getFieldLabel() {
		return getKey();
	}

	@Override
	public String toString() {
		return getUser() + " on " + getDateAndTime() + ": " + getParent() + " ." + getKey();
	}

	public static transient final Comparator<Change> DATE_AND_TIME_COMPARATOR = new Comparator<Change>() {

		public int compare(Change a, Change b) {
			return b.getDateAndTime().compareTo(a.getDateAndTime());
		}
	};

}