package scrum.server.sprint;

public class Task extends GTask {

	// --- dependencies ---

	public Task(Task template) {
		super(template);
	}

	public Task() {
		super(null);
		setRemainingWork(1);
	}

	// --- ---

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
