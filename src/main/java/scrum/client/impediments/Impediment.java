package scrum.client.impediments;

public class Impediment extends GImpediment {

	public Impediment(String id, String label, String description) {
		setId(id);
		setLabel(label);
		setDescription(description);
	}

	public String getSummary() {
		if (isSolved()) return "Solved.";
		String solution = getSolution();
		return (solution == null || "".equals(solution)) ? "Unsolved." : "Unsolved, with solution proposal.";
	}
}
