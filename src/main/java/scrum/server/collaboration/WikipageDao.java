package scrum.server.collaboration;

import ilarkesto.fp.Predicate;
import scrum.server.project.Project;

public class WikipageDao extends GWikipageDao {

	public Wikipage getWikipageByName(final String name, final Project project) {
		return getEntity(new Predicate<Wikipage>() {

			public boolean test(Wikipage e) {
				return e.isName(name) && e.isProject(project);
			}
		});
	}

}