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

	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateRequirementNumber());
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		updateNumber();
	}

	public Set<Task> getTasks() {
		return taskDao.getTasksByRequirement(this);
	}

	@Override
	public String toString() {
		return "r" + getNumber() + " " + getLabel();
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
