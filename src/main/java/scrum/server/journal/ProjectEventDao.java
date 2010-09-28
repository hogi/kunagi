package scrum.server.journal;

import ilarkesto.base.time.DateAndTime;
import ilarkesto.persistence.AEntity;
import scrum.server.project.Project;

public class ProjectEventDao extends GProjectEventDao {

	@Override
	public ProjectEvent newEntityInstance() {
		ProjectEvent event = super.newEntityInstance();
		event.setDateAndTime(DateAndTime.now());
		return event;
	}

	public ProjectEvent postEvent(Project project, String label, AEntity subject) {
		ProjectEvent event = newEntityInstance();
		event.setProject(project);
		event.setLabel(label);
		event.setSubject(subject);
		saveEntity(event);
		return event;
	}

}