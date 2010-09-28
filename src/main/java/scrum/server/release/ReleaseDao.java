package scrum.server.release;

import ilarkesto.fp.Predicate;

import java.util.Set;

import scrum.server.project.Project;

public class ReleaseDao extends GReleaseDao {

	public Release getReleaseByNumber(final int number, final Project project) {
		return getEntity(new Predicate<Release>() {

			@Override
			public boolean test(Release t) {
				return t.isNumber(number) && t.isProject(project);
			}
		});
	}

	public Release getNextRelease(Project project) {
		Release next = null;
		Set<Release> releases = getReleasesByProject(project);
		for (Release release : releases) {
			if (release.isReleased()) continue;
			if (next == null || release.getReleaseDate().isBefore(next.getReleaseDate())) {
				next = release;
			}
		}
		return next;
	}

	public Release getCurrentRelease(Project project) {
		Release latest = null;
		Set<Release> releases = getReleasesByProject(project);
		for (Release release : releases) {
			if (!release.isReleased()) continue;
			if (latest == null || release.getReleaseDate().isAfter(latest.getReleaseDate())) {
				latest = release;
			}
		}
		return latest;
	}

}