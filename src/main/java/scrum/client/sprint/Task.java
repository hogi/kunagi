package scrum.client.sprint;

import java.util.Map;

import scrum.client.admin.User;
import scrum.client.project.Requirement;

public class Task extends GTask {

	public static final int INIT_EFFORT = 1;

	public Task(Requirement requirement) {
		setRequirement(requirement);
		setLabel("New Task");
		setRemainingWork(INIT_EFFORT);
	}

	public Task(Map data) {
		super(data);
	}

	public String getReference() {
		return "t" + getNumber();
	}

	public void setDone() {
		setRemainingWork(0);
	}

	public boolean isDone() {
		return getRemainingWork() == 0;
	}

	public String getSummary() {
		if (isDone()) return "Done.";
		String s = getRemainingWork() + " hours to do.";
		User owner = getOwner();
		if (owner != null) {
			s += " Owed by " + owner + ".";
		} else {
			s += " No owner.";
		}
		return s;
	}

	@Override
	public String toString() {
		return getLabel();
	}

	public void incrementBurnedWork() {
		setBurnedWork(getBurnedWork() + 1);
	}

	public void decrementBurnedWork() {
		if (getBurnedWork() == 0) return;
		setBurnedWork(getBurnedWork() - 1);
	}

	public void adjustRemainingWork(int burned) {
		int remaining = getRemainingWork();
		if (remaining == 0) return;
		remaining -= burned;
		if (remaining < 1) remaining = 1;
		setRemainingWork(remaining);
	}

	public void incrementRemainingWork() {
		setRemainingWork(getRemainingWork() + 1);
	}

	public void decrementRemainingWork() {
		int work = getRemainingWork();
		if (work <= 1) return;
		setRemainingWork(work - 1);
	}

}
