package scrum.client.release;

import java.util.Map;

import scrum.client.project.Project;

public class Release extends GRelease {

	public Release(Project project) {
		setLabel("Next Release");
		setProject(project);
	}

	public Release(Map data) {
		super(data);
	}

	@Override
	public String toString() {
		return super.getLabel();
	}

}
