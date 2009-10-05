package scrum.client.sprint;

import java.util.Comparator;
import java.util.Map;

import scrum.client.ScrumGwtApplication;
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

	public void claim() {
		User user = ScrumGwtApplication.get().getUser();
		if (isDone()) {
			setUnDone(user);
		} else {
			setOwner(user);
		}
	}

	public String getLongLabel(boolean showOwner, boolean showRequirement) {
		StringBuilder sb = new StringBuilder();
		sb.append(getReference()).append(" ").append(getLabel());
		if (showOwner && isOwnerSet()) {
			sb.append(" (").append(getOwner().getName()).append(')');
		}
		if (showRequirement) {
			Requirement requirement = getRequirement();
			sb.append(" (").append(requirement.getReference()).append(" ").append(requirement.getLabel()).append(')');
		}
		return sb.toString();
	}

	public String getReference() {
		return "t" + getNumber();
	}

	public void setDone(User user) {
		if (user == null)
			throw new IllegalArgumentException("a Task cannot be set done without claiming Task ownership");
		setOwner(user);
		setRemainingWork(0);
	}

	public void setUnDone(User user) {
		setOwner(user);
		setRemainingWork(1);
	}

	public void setUnOwned() {
		setUnDone(null);
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
		// return getLabel();
		return getReference();
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

	public static int sumBurnedWork(Iterable<Task> tasks) {
		int sum = 0;
		for (Task task : tasks) {
			sum += task.getBurnedWork();
		}
		return sum;
	}

	public static int sumRemainingWork(Iterable<Task> tasks) {
		int sum = 0;
		for (Task task : tasks) {
			sum += task.getRemainingWork();
		}
		return sum;
	}

	public static final Comparator<Task> NUMBER_COMPARATOR = new Comparator<Task>() {

		public int compare(Task a, Task b) {
			return a.getNumber() - b.getNumber();
		}
	};

}
