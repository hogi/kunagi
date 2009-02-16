package scrum.server.project;

import ilarkesto.base.time.Date;
import ilarkesto.fp.Predicate;

public class ProjectSprintSnapshotDao extends GProjectSprintSnapshotDao {

	public ProjectSprintSnapshot getProjectSprintSnapshot(final Project project, final Date date, boolean autoCreate) {
		ProjectSprintSnapshot snapshot = getEntity(new Predicate<ProjectSprintSnapshot>() {

			public boolean test(ProjectSprintSnapshot e) {
				return e.isProject(project) && e.isDate(date);
			}
		});

		if (autoCreate && snapshot == null) {
			snapshot = newEntityInstance();
			snapshot.setProject(project);
			snapshot.setDate(date);
			saveEntity(snapshot);
		}

		return snapshot;
	}

}
