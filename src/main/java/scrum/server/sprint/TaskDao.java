package scrum.server.sprint;

import ilarkesto.fp.Predicate;

import java.util.Set;

public class TaskDao extends GTaskDao {

	public Set<Task> getTasksBySprint(final Sprint sprint) {
		return getEntities(new Predicate<Task>() {

			public boolean test(Task task) {
				return task.isSprint(sprint);
			}
		});
	}

}
