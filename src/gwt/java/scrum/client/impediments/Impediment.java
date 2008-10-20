package scrum.client.impediments;

import scrum.client.common.AEntity;

public class Impediment extends AEntity {

	public static final String LABEL = "label";
	public static final String DESCRIPTION = "description";
	public static final String SOLUTION = "solution";
	public static final String SOLVED = "solved";

	public Impediment(String id, String label, String description) {
		super(id);
		setProperty(LABEL, label);
		setProperty(DESCRIPTION, description);
	}

	public String getLabel() {
		return getProperty(LABEL);
	}

	public Impediment setLabel(String label) {
		if (label == null || label.length() == 0) label = "unlabeled";
		setProperty(LABEL, label);
		return this;
	}

	public String getDescription() {
		return getProperty(DESCRIPTION);
	}

	public Impediment setDescription(String description) {
		setProperty(DESCRIPTION, description);
		return this;
	}

	public String getSolution() {
		return getProperty(SOLUTION);
	}

	public Impediment setSolution(String solution) {
		setProperty(SOLUTION, solution);
		return this;
	}

	public boolean isSolved() {
		return getPropertyAsBool(SOLVED);
	}

	public Impediment setSolved(boolean solved) {
		setProperty(SOLVED, solved);
		return this;
	}

	public String getSummary() {
		if (isSolved()) return "Solved.";
		String solution = getSolution();
		return (solution == null || "".equals(solution)) ? "Unsolved." : "Unsolved, with solution proposal.";
	}
}
