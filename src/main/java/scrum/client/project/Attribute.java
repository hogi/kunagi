package scrum.client.project;

import java.util.Map;

public class Attribute extends GAttribute {

	public static final String INITIAL_LABEL = "New Attribute";

	public Attribute(Project project) {
		setProject(project);
		setLabel(INITIAL_LABEL);
	}

	public Attribute(Map data) {
		super(data);
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
