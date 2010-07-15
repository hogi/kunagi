package scrum.server.sprint;

import ilarkesto.base.time.Date;
import ilarkesto.fp.Predicate;
import scrum.server.project.Project;

public class SprintDao extends GSprintDao {

	public Sprint getSprintByNumber(final int number, final Project project) {
		return getEntity(new Predicate<Sprint>() {

			@Override
			public boolean test(Sprint sprint) {
				return sprint.isNumber(number) && sprint.isProject(project);
			}
		});
	}

	@Override
	public Sprint newEntityInstance() {
		Sprint sprint = super.newEntityInstance();
		sprint.setLabel("New Sprint");
		return sprint;
	}

	// --- test data ---

	public Sprint createTestSprint(Project project) {
		Date begin = Date.beforeDays(15);
		Date end = Date.inDays(15);

		Sprint sprint = newEntityInstance();
		sprint.setProject(project);
		sprint.setLabel("Our first Sprint!");
		sprint.setBegin(begin);
		sprint.setEnd(end);
		if (end.isPast()) sprint.setVelocity(20f);
		saveEntity(sprint);

		project.setCurrentSprint(sprint);

		return sprint;
	}

}
