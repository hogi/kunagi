package scrum.server.sprint;

import scrum.server.admin.User;
import scrum.server.common.BurndownSnapshot;
import scrum.server.project.Project;

public class SprintDaySnapshot extends GSprintDaySnapshot implements BurndownSnapshot {

	public void update() {
		setRemainingWork(getSprint().getRemainingWork());
		setBurnedWork(getSprint().getBurnedWork());
	}

	public Project getProject() {
		return getSprint().getProject();
	}

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	@Override
	public String toString() {
		return getDate() + ": " + getBurnedWork() + ", " + getRemainingWork();
	}

}
