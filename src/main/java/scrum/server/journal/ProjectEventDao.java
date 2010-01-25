package scrum.server.journal;

import ilarkesto.base.time.DateAndTime;
import scrum.server.project.Project;

public class ProjectEventDao extends GProjectEventDao {

	@Override
	public ProjectEvent newEntityInstance() {
		ProjectEvent event = super.newEntityInstance();
		event.setDateAndTime(DateAndTime.now());
		return event;
	}

	public ProjectEvent postEvent(Project project, String label) {
		ProjectEvent event = newEntityInstance();
		event.setProject(project);
		event.setLabel(label);
		saveEntity(event);
		return event;
	}

	public void createTestEvent(Project project, int variant) {
		switch (variant) {
			case 1:
				postEvent(project, "duke commented on req1");
				break;
			case 2:
				postEvent(project, "cartman claimed tsk3");
				break;
			case 3:
				postEvent(project, "Sprint finished: First sprint");
				break;
			case 4:
				postEvent(project, "All tasks in req1 finished");
				break;
			default:
				postEvent(project, "Some custom event");
				break;
		}
	}

}