package scrum.server.project;

import ilarkesto.fp.Predicate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectSprintSnapshotDao extends GProjectSprintSnapshotDao {

	public List<ProjectSprintSnapshot> getProjectSprintSnapshotsByProject(final Project project) {
		List<ProjectSprintSnapshot> ret = new ArrayList<ProjectSprintSnapshot>(
				getEntities(new Predicate<ProjectSprintSnapshot>() {

					public boolean test(ProjectSprintSnapshot e) {
						return e.isProject(project);
					}
				}));
		Collections.sort(ret);
		return ret;
	}

}
