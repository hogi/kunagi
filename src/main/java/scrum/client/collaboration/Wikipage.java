package scrum.client.collaboration;

import java.util.Map;

import scrum.client.project.Project;

public class Wikipage extends GWikipage {

	public Wikipage(Project project) {
		setProject(project);
	}

	public Wikipage(Map data) {
		super(data);
	}

}
