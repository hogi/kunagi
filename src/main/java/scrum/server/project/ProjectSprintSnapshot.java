package scrum.server.project;

import ilarkesto.base.time.Date;
import scrum.server.admin.User;
import scrum.server.common.BurndownSnapshot;

public class ProjectSprintSnapshot extends GProjectSprintSnapshot implements Comparable<ProjectSprintSnapshot>,
		BurndownSnapshot {

	public void update() {
	// setRemainingWork(getProject().getRemainingWork());
	// setBurnedWork(getProject().getBurnedWork());
	}

	public boolean isProject(Project project) {
		return isSprintSet() && getSprint().isProject(project);
	}

	public Project getProject() {
		return getSprint().getProject();
	}

	public Date getDate() {
		return getSprint().getEnd();
	}

	@Override
	public int compareTo(ProjectSprintSnapshot other) {
		return getDate().compareTo(other.getDate());
	}

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	public boolean isEditableBy(User user) {
		return false;
	}

	@Override
	public String toString() {
		return getDate() + ": " + getBurnedWork() + ", " + getRemainingWork();
	}

}
