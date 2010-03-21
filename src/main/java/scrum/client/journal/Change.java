package scrum.client.journal;

import ilarkesto.core.base.Str;

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
		String oldValue = getOldValue();
		if (Str.isEmpty(oldValue)) return "created " + getFieldLabel();
		String newValue = getNewValue();
		if (Str.isEmpty(newValue)) return "deleted " + getFieldLabel();
		return "changed " + getFieldLabel();
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

	public String getDiff() {
		String oldValue = getOldValue();
		if (Str.isEmpty(oldValue)) return getNewValue();
		String newValue = getNewValue();
		if (Str.isEmpty(newValue)) return null;
		return createDiff(oldValue, newValue);
	}

	private static String createDiff(String oldValue, String newValue) {
		return oldValue + "\n\n          < old | new >\n\n" + newValue;
	}

}