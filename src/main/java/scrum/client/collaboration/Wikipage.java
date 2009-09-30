package scrum.client.collaboration;

import java.util.Map;

import scrum.client.project.Project;

public class Wikipage extends GWikipage {

	public Wikipage(Project project, String name) {
		setProject(project);
		setName(name);
		setText("New Wiki Page...");
	}

	public Wikipage(Map data) {
		super(data);
	}

	@Override
	public String toString() {
		return getName();
	}

}
