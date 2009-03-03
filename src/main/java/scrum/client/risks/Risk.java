package scrum.client.risks;

import java.util.Map;

import scrum.client.project.Project;

public class Risk extends GRisk {

	public static final String INITIAL_LABEL = "New Risk";

	public Risk(Project project) {
		setProject(project);
		setLabel(INITIAL_LABEL);
	}

	public Risk(Map data) {
		super(data);
	}

	public String getSummary() {
		return "Severe (Extreme and Very likely)"; // TODO impl
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
