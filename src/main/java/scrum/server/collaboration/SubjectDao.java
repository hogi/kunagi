package scrum.server.collaboration;

import ilarkesto.fp.Predicate;
import scrum.server.project.Project;

public class SubjectDao extends GSubjectDao {

	public Subject getSubjectsByNumber(final int number, final Project project) {
		return getEntity(new Predicate<Subject>() {

			public boolean test(Subject t) {
				return t.isNumber(number) && t.isProject(project);
			}
		});
	}
}