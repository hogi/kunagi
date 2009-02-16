package scrum.server.project;

public class ProjectSprintSnapshot extends GProjectSprintSnapshot implements Comparable<ProjectSprintSnapshot> {

	public ProjectSprintSnapshot(GProjectSprintSnapshot template) {
		super(template);
	}

	public ProjectSprintSnapshot() {
		super(null);
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
