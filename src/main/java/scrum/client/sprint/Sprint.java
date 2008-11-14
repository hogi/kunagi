package scrum.client.sprint;

import java.util.List;
import java.util.Map;

import scrum.client.project.BacklogItem;
import scrum.client.project.Project;

public class Sprint extends GSprint {

	public Sprint(Project project, String label) {
		setProject(project);
		setLabel(label);
	}

	public Sprint(Map data) {
		super(data);
	}

	public List<BacklogItem> getBacklogItems() {
		return getDao().getBacklogItemsBySprint(this);
	}

}
