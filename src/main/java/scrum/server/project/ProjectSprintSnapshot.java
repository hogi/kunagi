package scrum.server.project;

import ilarkesto.base.time.Date;
import scrum.server.common.BurndownSnapshot;

public class ProjectSprintSnapshot extends GProjectSprintSnapshot implements Comparable<ProjectSprintSnapshot>,
		BurndownSnapshot {

	public void update() {
		setRemainingWork(getProject().getRemainingWork());
		setBurnedWork(getProject().getBurnedWork());
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

	@Override
	public String toString() {
		return getDate() + ": " + getBurnedWork() + ", " + getRemainingWork();
	}

}
