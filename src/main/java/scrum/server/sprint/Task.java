package scrum.server.sprint;

public class Task extends GTask {

	public boolean isDone() {
		return getRemainingWork() == 0;
	}

	public boolean isSprint(Sprint sprint) {
		return getRequirement().isSprint(sprint);
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		if (getNumber() == 0) setNumber(getRequirement().getProject().generateTaskNumber());
	}

	@Override
	public String toString() {
		return "t" + getNumber() + " " + getLabel();
	}
}
