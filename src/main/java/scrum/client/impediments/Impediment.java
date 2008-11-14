package scrum.client.impediments;

import java.util.Map;

import scrum.client.project.Project;

public class Impediment extends GImpediment {

	public static final String INIT_LABEL = "New Impediment";

	public Impediment(Project project) {
		setLabel(INIT_LABEL);
		setProject(project);
	}

	public Impediment(Map data) {
		super(data);
	}

	public String getSummary() {
		if (isSolved()) return "Solved.";
		String solution = getSolution();
		return (solution == null || "".equals(solution)) ? "Unsolved." : "Unsolved, with solution proposal.";
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
