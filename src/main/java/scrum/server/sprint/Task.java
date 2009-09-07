package scrum.server.sprint;

import scrum.server.common.Numbered;

public class Task extends GTask implements Numbered {

	public boolean isDone() {
		return getRemainingWork() == 0;
	}

	public boolean isSprint(Sprint sprint) {
		return getRequirement().isSprint(sprint);
	}

	public void updateNumber() {
		if (getNumber() == 0) setNumber(getRequirement().getProject().generateTaskNumber());
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		updateNumber();
	}

	@Override
	public String toString() {
		return "t" + getNumber() + " " + getLabel();
	}
}
