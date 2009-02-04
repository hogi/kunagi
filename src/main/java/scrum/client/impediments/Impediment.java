package scrum.client.impediments;

import java.util.Map;

import scrum.client.common.Date;
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

	public boolean isSolved() {
		return getSolveDate() != null;
	}

	public void setSolved() {
		setSolveDate(Date.today());
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
