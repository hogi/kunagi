package scrum.server.sprint;

import ilarkesto.fp.Predicate;

import java.util.Set;

import scrum.server.project.Requirement;

public class TaskDao extends GTaskDao {

	@Override
	public Task newEntityInstance() {
		Task task = super.newEntityInstance();
		task.setRemainingWork(scrum.client.sprint.Task.INIT_EFFORT);
		return task;
	}

	public Set<Task> getTasksBySprint(final Sprint sprint) {
		return getEntities(new Predicate<Task>() {

			public boolean test(Task task) {
				return task.isSprint(sprint);
			}
		});
	}

	// --- test data ---

	public void createTestTask(Requirement requirement, int variant) {
		int remainingWork = 5;
		int burnedWork = 5;

		if (variant == 0) {
			remainingWork = 0;
		} else {
			remainingWork += variant;
			burnedWork += variant;
		}

		Task task = newEntityInstance();
		task.setRequirement(requirement);
		task.setLabel("Task " + variant);
		task.setRemainingWork(remainingWork);
		task.setBurnedWork(burnedWork);
		saveEntity(task);
	}

}
