package scrum.server.release;

import ilarkesto.fp.Predicate;
import scrum.server.project.Project;

public class ReleaseDao extends GReleaseDao {

	public Release getReleaseByNumber(final int number, final Project project) {
		return getEntity(new Predicate<Release>() {

			public boolean test(Release t) {
				return t.isNumber(number) && t.isProject(project);
			}
		});
	}

}