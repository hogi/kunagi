package scrum.server.project;

import java.util.Set;

import scrum.server.common.Numbered;
import scrum.server.sprint.Task;
import scrum.server.sprint.TaskDao;

public class Requirement extends GRequirement implements Numbered {

	// --- dependencies ---

	private static TaskDao taskDao;

	public static void setTaskDao(TaskDao taskDao) {
		Requirement.taskDao = taskDao;
	}

	// --- ---

	public boolean isTasksClosed() {
		for (Task task : getTasks()) {
			if (!task.isClosed()) return false;
		}
		return true;
	}

	public String getReferenceAndLabel() {
		return getReference() + " (" + getLabel() + ")";
	}

	public String getReference() {
		return scrum.client.project.Requirement.REFERENCE_PREFIX + getNumber();
	}

	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateRequirementNumber());
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		updateNumber();

		// delete when closed and older than 4 weeks
		if (isClosed() && getLastModified().getPeriodToNow().toWeeks() > 4) getDao().deleteEntity(this);
	}

	public Set<Task> getTasks() {
		return taskDao.getTasksByRequirement(this);
	}

	@Override
	public String toString() {
		return getReferenceAndLabel();
	}

	// --- test data ---

	public void addTestTasks(int variant) {
		if (variant == 0) return;
		taskDao.createTestTask(this, 0);
		taskDao.createTestTask(this, 1);
		taskDao.createTestTask(this, 2);
		taskDao.createTestTask(this, 3);
		taskDao.createTestTask(this, 4);
	}
}
