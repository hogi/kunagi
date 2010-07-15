package scrum.server.sprint;

import ilarkesto.base.Str;
import ilarkesto.base.Utl;
import ilarkesto.base.time.Date;
import ilarkesto.core.logging.Log;

import java.util.List;
import java.util.Set;

import scrum.server.admin.User;
import scrum.server.common.Numbered;
import scrum.server.project.Project;
import scrum.server.project.Requirement;
import scrum.server.project.RequirementDao;

public class Sprint extends GSprint implements Numbered {

	private static final Log LOG = Log.get(Sprint.class);

	// --- dependencies ---

	private static RequirementDao requirementDao;
	private static TaskDao taskDao;
	private static SprintDaySnapshotDao sprintDaySnapshotDao;

	public static void setRequirementDao(RequirementDao storyDao) {
		Sprint.requirementDao = storyDao;
	}

	public static void setTaskDao(TaskDao taskDao) {
		Sprint.taskDao = taskDao;
	}

	public static void setSprintDaySnapshotDao(SprintDaySnapshotDao sprintDaySnapshotDao) {
		Sprint.sprintDaySnapshotDao = sprintDaySnapshotDao;
	}

	// --- ---

	public void close() {
		float velocity = 0;
		StringBuilder sb = new StringBuilder();
		for (Requirement requirement : getRequirements()) {
			if (requirement.isClosed()) {
				Float work = requirement.getEstimatedWork();
				if (work != null) velocity += work;
				sb.append("* ");
				sb.append(requirement.getLabel());
				sb.append("\n");
				for (Task task : requirement.getTasks()) {
					taskDao.deleteEntity(task);
				}
			} else {
				for (Task task : requirement.getTasks()) {
					if (task.isClosed()) {
						taskDao.deleteEntity(task);
					} else {
						task.reset();
					}
				}
			}
			requirement.setSprint(null);
		}
		setCompletedRequirementLabels(sb.toString());
		setVelocity(velocity);
		Project project = getProject();
		project.setVelocity(Math.round(velocity));
		setProductOwners(project.getProductOwners());
		setScrumMasters(project.getScrumMasters());
		setTeamMembers(project.getTeamMembers());
	}

	public String getProductOwnersAsString() {
		return Str.concat(User.getNames(getProductOwners()), ", ");
	}

	public String getScrumMastersAsString() {
		return Str.concat(User.getNames(getScrumMasters()), ", ");
	}

	public String getTeamMembersAsString() {
		return Str.concat(User.getNames(getTeamMembers()), ", ");
	}

	public List<SprintDaySnapshot> getDaySnapshots() {
		return sprintDaySnapshotDao.getSprintDaySnapshots(this);
	}

	public Set<SprintDaySnapshot> getExistingDaySnapshots() {
		return sprintDaySnapshotDao.getSprintDaySnapshotsBySprint(this);
	}

	public Integer getLengthInDays() {
		if (!isBeginSet() || !isEndSet()) return null;
		return getBegin().getPeriodTo(getEnd()).toDays();
	}

	public SprintDaySnapshot getDaySnapshot(Date date) {
		return sprintDaySnapshotDao.getSprintDaySnapshot(this, date, true);
	}

	public int getRemainingWork() {
		int sum = 0;
		for (Task task : getTasks()) {
			Integer effort = task.getRemainingWork();
			if (effort != null) sum += effort;
		}
		return sum;
	}

	public int getBurnedWork() {
		int sum = 0;
		for (Task task : getTasks()) {
			sum += task.getBurnedWork();
		}
		return sum;
	}

	public Set<Requirement> getRequirements() {
		return requirementDao.getRequirementsBySprint(this);
	}

	public Set<Task> getTasks() {
		return taskDao.getTasksBySprint(this);
	}

	public String getReference() {
		return scrum.client.sprint.Sprint.REFERENCE_PREFIX + getNumber();
	}

	@Override
	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateSprintNumber());
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		updateNumber();

		if (getProject().isCurrentSprint(this)) {
			if (!isBeginSet()) setBegin(Date.today());
			if (!isEndSet()) setEnd(getBegin().addDays(14));
		}

		// delete when not current and end date older than 4 weeks
		// if (isEndSet() && !getProject().isCurrentSprint(this) && getEnd().isPast()
		// && getEnd().getPeriodToNow().toWeeks() > 4) {
		// LOG.info("Deleting sprint, which ended on", getEnd(), "->", toString());
		// getDao().deleteEntity(this);
		// }

	}

	@Override
	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	public String getReferenceAndLabel() {
		return getReference() + " " + getLabel();
	}

	@Override
	public String toString() {
		return getReferenceAndLabel();
	}

	public void burndownTasksRandomly(Date begin, Date end) {
		int days = getBegin().getPeriodTo(getEnd()).toDays();
		days -= (days / 7) * 2;
		int defaultWorkPerDay = getRemainingWork() / days;

		getDaySnapshot(begin).updateWithCurrentSprint();
		begin = begin.nextDay();
		while (begin.isBefore(end)) {
			if (!begin.isWeekend()) {
				int toBurn = Utl.randomInt(0, defaultWorkPerDay + (defaultWorkPerDay * 2));
				int totalRemaining = getRemainingWork();
				for (Task task : getTasks()) {
					if (toBurn == 0) break;
					int remaining = task.getRemainingWork();
					int burn = Math.min(toBurn, remaining);
					remaining -= burn;
					toBurn -= burn;
					task.setBurnedWork(task.getBurnedWork() + burn);
					if (Utl.randomInt(1, 10) == 1) {
						remaining += Utl.randomInt(-defaultWorkPerDay * 2, defaultWorkPerDay * 3);
					}
					if (totalRemaining == 0) {
						remaining += Utl.randomInt(defaultWorkPerDay * 3, defaultWorkPerDay * 5);
						totalRemaining = remaining;
					}
					task.setRemainingWork(remaining);
				}
			}
			getDaySnapshot(begin).updateWithCurrentSprint();
			begin = begin.nextDay();
		}
	}

}
