package scrum.client.journal;

import ilarkesto.core.base.Str;
import ilarkesto.core.diff.HtmlDiffMarker;
import ilarkesto.core.diff.TokenDiff;
import ilarkesto.gwt.client.AGwtEntity;

import java.util.Comparator;
import java.util.Map;

import scrum.client.impediments.Impediment;
import scrum.client.issues.Issue;
import scrum.client.project.Requirement;
import scrum.client.risks.Risk;
import scrum.client.risks.RiskComputer;

public class Change extends GChange {

	public Change(Map data) {
		super(data);
	}

	public String getLabel() {
		String key = getKey();
		AGwtEntity parent = getParent();
		if ("@created".equals(key)) return "created entity";
		if (parent instanceof Issue) {
			if (key.equals("@reply")) return "emailed a reply";
		}

		return getFieldChangeLabel();
	}

	private String getFieldChangeLabel() {
		String key = getKey();
		AGwtEntity parent = getParent();
		String oldValue = getOldValue();
		String newValue = getNewValue();

		if (parent instanceof Issue) {
			if (key.equals("closeDate")) return Str.isBlank(newValue) ? "reopened issue" : "closed issue";
			if (key.equals("storyId"))
				return "converted issue to story " + getDao().getRequirement(newValue).getReference();
		} else if (parent instanceof Impediment) {
			if (key.equals("closed")) return Str.isTrue(newValue) ? "closed impediment" : "reopened impediment";
		} else if (parent instanceof Requirement) {
			if (key.equals("closed")) return Str.isTrue(newValue) ? "closed story" : "reopened story";
			if (key.equals("sprintId"))
				return newValue == null ? "kicked story from sprint" : "pulled story to sprint";
			if (key.equals("issueId")) return "created story from issue " + getDao().getIssue(newValue).getReference();
		}

		if (Str.isBlank(oldValue)) return "created " + getFieldLabel();
		if (Str.isBlank(newValue)) return "deleted " + getFieldLabel();
		return "changed " + getFieldLabel();
	}

	public String getDiff() {
		String key = getKey();
		AGwtEntity parent = getParent();
		String oldValue = getOldValue();
		String newValue = getNewValue();

		if (parent instanceof Issue) {
			if (key.equals("closeDate")) return null;
			if (key.equals("storyId")) return null;
			if (key.equals("@reply")) return Str.toHtml(newValue);
		} else if (parent instanceof Impediment) {
			if (key.equals("closed")) return null;
		} else if (parent instanceof Risk) {
			if (key.equals("impact"))
				return createDiff(RiskComputer.getImpactLabel(oldValue), RiskComputer.getImpactLabel(newValue));
			if (key.equals("probability"))
				return createDiff(RiskComputer.getProbabilityLabel(oldValue),
					RiskComputer.getProbabilityLabel(newValue));
		} else if (parent instanceof Requirement) {
			if (key.equals("closed")) return null;
			if (key.equals("sprintId")) return null;
			if (key.equals("issueId")) return null;
		}

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

		@Override
		public int compare(Change a, Change b) {
			return b.getDateAndTime().compareTo(a.getDateAndTime());
		}
	};

	private static String createDiff(String oldValue, String newValue) {
		String s = TokenDiff.combinedDiff(oldValue, newValue, new HtmlDiffMarker());
		return s;
	}

}