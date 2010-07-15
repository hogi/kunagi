package scrum.server.sprint;

import ilarkesto.fp.Predicate;

import java.util.Set;

import scrum.server.project.Project;
import scrum.server.project.Requirement;

public class TaskDao extends GTaskDao {

	public Set<Task> getTasksByProject(final Project project) {
		return getEntities(new Predicate<Task>() {

			public boolean test(Task t) {
				return t.isProject(project);
			}
		});
	}

	public Task getTaskByNumber(final int number, final Project project) {
		return getEntity(new Predicate<Task>() {

			public boolean test(Task t) {
				return t.isNumber(number) && t.isProject(project);
			}
		});
	}

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

	public Task postTask(Requirement requirement, String label, int work) {
		Task task = newEntityInstance();
		task.setRequirement(requirement);
		task.setLabel(label);
		task.setRemainingWork(work);
		saveEntity(task);
		return task;
	}
}
