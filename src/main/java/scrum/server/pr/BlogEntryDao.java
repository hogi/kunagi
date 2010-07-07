package scrum.server.pr;

import ilarkesto.base.time.DateAndTime;
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

	@Override
	public BlogEntry newEntityInstance() {
		BlogEntry entry = super.newEntityInstance();
		entry.setDateAndTime(DateAndTime.now());
		return entry;
	}

}