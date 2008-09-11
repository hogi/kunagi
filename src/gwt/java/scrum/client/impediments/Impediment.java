package scrum.client.impediments;

import scrum.client.common.AEntity;

public class Impediment extends AEntity {

	private String label;
	private String description;
	private String solution;
	private boolean solved;

	public Impediment(String id, String label, String description) {
		super(id);
		this.label = label;
		this.description = description;
	}

	public String getLabel() {
		return label;
	}

	public Impediment setLabel(String label) {
		if (label == null || label.length() == 0) label = "unlabeled";
		this.label = label;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Impediment setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getSolution() {
		return solution;
	}

	public Impediment setSolution(String solution) {
		this.solution = solution;
		return this;
	}

	public boolean isSolved() {
		return solved;
	}

	public Impediment setSolved(boolean solved) {
		this.solved = solved;
		return this;
	}

	public String getSummary() {
		if (solved) return "Solved.";
		return (solution == null || "".equals(solution)) ? "Unsolved." : "Unsolved, with solution proposal.";
	}
}
