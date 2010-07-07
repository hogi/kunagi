package scrum.server.pr;

import ilarkesto.fp.Predicate;
import scrum.server.project.Project;

public class BlogEntryDao extends GBlogEntryDao {

	public BlogEntry getBlogEntryByNumber(final int number, final Project project) {
		return getEntity(new Predicate<BlogEntry>() {

			public boolean test(BlogEntry t) {
				return t.isNumber(number) && t.isProject(project);
			}
		});
	}

}