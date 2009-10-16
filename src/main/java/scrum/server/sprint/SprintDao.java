package scrum.server.sprint;

import ilarkesto.base.Utl;
import ilarkesto.base.time.Date;
import scrum.server.project.Project;

public class SprintDao extends GSprintDao {

	@Override
	public Sprint newEntityInstance() {
		Sprint sprint = super.newEntityInstance();
		sprint.setLabel("New Sprint");
		return sprint;
	}

	// --- test data ---

	public void createTestSprint(Project project, int variant) {
		Date begin = Date.today().getFirstDateOfMonth();
		Date end = Date.today().getLastDateOfMonth();

		if (variant > 0) {
			begin = begin.addDays(-variant * 3);
			end = begin.addDays(-variant * 3);
		}

		Sprint sprint = newEntityInstance();
		sprint.setProject(project);
		sprint.setLabel("Sprint -" + variant);
		sprint.setBegin(begin);
		sprint.setEnd(end);
		saveEntity(sprint);

		if (variant == 0) {
			project.setCurrentSprint(sprint);
			int remainingWork = 150;
			int burnedWork = 0;
			for (int i = 15; i > 0; i--) {
				SprintDaySnapshot snapshot = sprint.getDaySnapshot(Date.today().addDays(-i));
				int burned = Utl.randomInt(1, 3) * 5;
				burnedWork += burned;
				remainingWork -= burned;
				if (Utl.randomInt(1, 5) == 1) remainingWork += burned + 5;
				snapshot.setBurnedWork(burnedWork);
				snapshot.setRemainingWork(remainingWork);
			}
		}
	}

}
