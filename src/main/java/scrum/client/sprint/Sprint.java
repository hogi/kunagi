package scrum.client.sprint;

import java.util.List;
import java.util.Map;

import scrum.client.project.Project;
import scrum.client.project.Story;

public class Sprint extends GSprint {

	public Sprint(Project project, String label) {
		setProject(project);
		setLabel(label);
	}

	public Sprint(Map data) {
		super(data);
	}

	public List<Story> getBacklogItems() {
		return getDao().getStorysBySprint(this);
	}

	@Override
	public String toString() {
		return getLabel();
	}

}
