package scrum.client.sprint;

import java.util.Comparator;
import java.util.Map;

import scrum.client.admin.User;
import scrum.client.project.Requirement;

public class Task extends GTask {

	public static final int INIT_EFFORT = 1;
	public static final String REFERENCE_PREFIX = "tsk";

	public Task(Requirement requirement) {
		setRequirement(requirement);
		setLabel("New Task");
		setRemainingWork(INIT_EFFORT);
	}

	public Task(Map data) {
		super(data);
	}

	public void claim() {
		User user = cm.getAuth().getUser();
		boolean ownerchange = !isOwner(user);
		if (isDone()) {
			setUnDone(user);
		} else {
			setOwner(user);
		}
		if (ownerchange)
			cm.getChat().postSystemMessage(user.getName() + " claimed task " + getReference() + ".", true);
	}

	public String getLongLabel(boolean showOwner, boolean showRequirement) {
		StringBuilder sb = new StringBuilder();
		sb.append(getLabel());
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
		return REFERENCE_PREFIX + getNumber();
	}

	public void setDone(User user) {
		if (user == null)
			throw new IllegalArgumentException("a Task cannot be set done without claiming Task ownership");
		setOwner(user);
		setRemainingWork(0);
		cm.getChat().postSystemMessage(cm.getAuth().getUser().getName() + " closed task " + getReference() + ".", true);
	}

	public void setUnDone(User user) {
		setOwner(user);
		setRemainingWork(1);
	}

	public void setUnOwned() {
		setUnDone(null);
		cm.getChat().postSystemMessage(cm.getAuth().getUser().getName() + " rejected task " + getReference() + ".",
			true);
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
