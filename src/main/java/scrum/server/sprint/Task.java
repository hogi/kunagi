package scrum.server.sprint;

public class Task extends GTask {

	public boolean isDone() {
		return getRemainingWork() == 0;
	}

	public boolean isSprint(Sprint sprint) {
		return getRequirement().isSprint(sprint);
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
