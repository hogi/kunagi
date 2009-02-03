package scrum.client.sprint;

import java.util.Map;

import scrum.client.admin.User;
import scrum.client.project.Requirement;

public class Task extends GTask {

	public static final int INIT_EFFORT = 1;

	private User owner;

	public Task(Requirement requirement) {
		setRequirement(requirement);
		setLabel("New Task");
		setRemainingWork(INIT_EFFORT);
	}

	public Task(Map data) {
		super(data);
	}

	public void setDone() {
		setRemainingWork(0);
	}

	public boolean isDone() {
		return getRemainingWork() == null || getRemainingWork() == 0;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getSummary() {
		if (isDone()) return "Done.";
		return getRemainingWork() + " hours to do.";
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

	public void incrementRemainingWork() {
		Integer work = getRemainingWork();
		if (work == null) work = 0;
		setRemainingWork(work + 1);
	}

	public void decrementRemainingWork() {
		Integer work = getRemainingWork();
		if (work == null || work == 0) return;
		setRemainingWork(work - 1);
	}

}
