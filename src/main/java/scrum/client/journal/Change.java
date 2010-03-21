package scrum.client.journal;

import ilarkesto.core.base.Str;

import java.util.Comparator;
import java.util.Map;

import scrum.client.issues.Issue;

public class Change extends GChange {

	public Change(Map data) {
		super(data);
	}

	public String getLabel() {
		return getFieldChangeLabel();
	}

	private String getFieldChangeLabel() {
		String key = getKey();
		if (getParent() instanceof Issue) {
			if (key.equals("closeDate")) return Str.isEmpty(getNewValue()) ? "reopened issue" : "closed issue";
		}

		String oldValue = getOldValue();
		if (Str.isEmpty(oldValue)) return "created " + getFieldLabel();
		String newValue = getNewValue();
		if (Str.isEmpty(newValue)) return "deleted " + getFieldLabel();
		return "changed " + getFieldLabel();
	}

	public String getDiff() {
		String key = getKey();
		if (getParent() instanceof Issue) {
			if (key.equals("closeDate")) return null;
		}

		String oldValue = getOldValue();
		if (Str.isEmpty(oldValue)) return getNewValue();
		String newValue = getNewValue();
		if (Str.isEmpty(newValue)) return null;
		return createDiff(oldValue, newValue);
	}

	private String getFieldLabel() {
		String key = getKey();
		return key;
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

	private static String createDiff(String oldValue, String newValue) {
		return oldValue + "\n\n          < old | new >\n\n" + newValue;
	}

}