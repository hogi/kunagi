package scrum.client.project;

import java.util.Map;

public class Quality extends GQuality {

	public static final String INITIAL_LABEL = "New Quality";

	public Quality(Project project) {
		setProject(project);
		setLabel(INITIAL_LABEL);
	}

	public Quality(Map data) {
		super(data);
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
