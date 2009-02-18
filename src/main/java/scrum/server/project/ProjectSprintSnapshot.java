package scrum.server.project;

import ilarkesto.base.time.Date;

public class ProjectSprintSnapshot extends GProjectSprintSnapshot implements Comparable<ProjectSprintSnapshot> {

public class ProjectSprintSnapshot extends GProjectSprintSnapshot implements Comparable<ProjectSprintSnapshot>,
		BurndownSnapshot {

	public ProjectSprintSnapshot(GProjectSprintSnapshot template) {
		super(template);
	}

	public ProjectSprintSnapshot() {
		super(null);
	}

	public void update() {
		setRemainingWork(getProject().getRemainingWork());
		setBurnedWork(getProject().getBurnedWork());
	}

	public boolean isProject(Project project) {
		return getSprint().isProject(project);
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
