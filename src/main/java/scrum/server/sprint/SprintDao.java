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
		Date begin = Date.beforeDays(15);
		Date end = Date.inDays(15);

		Sprint sprint = newEntityInstance();
		sprint.setProject(project);
		sprint.setLabel("Sprint -" + variant);
		sprint.setBegin(begin);
		sprint.setEnd(end);
		if (variant > 0) sprint.setVelocity(new Float(20 + variant));
		saveEntity(sprint);

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
