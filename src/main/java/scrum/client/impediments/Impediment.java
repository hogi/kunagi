package scrum.client.impediments;

import java.util.Map;

public class Impediment extends GImpediment {

	public static final String INIT_LABEL = "New Impediment";

	public Impediment(String id) {
		setId(id);
		setLabel(INIT_LABEL);
	}

	public Impediment(Map data) {
		super(data);
	}

	public String getSummary() {
		if (isSolved()) return "Solved.";
		String solution = getSolution();
		return (solution == null || "".equals(solution)) ? "Unsolved." : "Unsolved, with solution proposal.";
	}
}
