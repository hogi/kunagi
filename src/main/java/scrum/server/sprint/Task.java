package scrum.server.sprint;

public class Task extends GTask {

	// --- dependencies ---

	public Task(Task template) {
		super(template);
	}

	public Task() {
		super(null);
	}

	// --- ---

	public boolean isDone() {
		if (!isEffortSet()) return true;
		return getEffort() == 0;
	}

	public boolean isSprint(Sprint sprint) {
		return getBacklogItem().isSprint(sprint);
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
