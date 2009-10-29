package scrum.client.issues;

import ilarkesto.gwt.client.LabelProvider;

public class IssueTypeLabelProvider implements LabelProvider<String> {

	public String getLabel(String type) {
		if (type.equals(Issue.Types.ISSUE)) return "Issue";
		if (type.equals(Issue.Types.BUG)) return "Bug";
		if (type.equals(Issue.Types.REQUIREMENT)) return "Requirement";
		if (type.equals(Issue.Types.QUALITY)) return "Quality";
		if (type.equals(Issue.Types.IDEA)) return "Idea";
		return type;
	}
}
