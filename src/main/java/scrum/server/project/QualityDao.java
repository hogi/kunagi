package scrum.server.project;

import ilarkesto.fp.Predicate;

public class QualityDao extends GQualityDao {

	public Quality getQualityByNumber(final int number, final Project project) {
		return getEntity(new Predicate<Quality>() {

			public boolean test(Quality t) {
				return t.isNumber(number) && t.isProject(project);
			}
		});
	}

	@Override
	public Quality newEntityInstance() {
		Quality quality = super.newEntityInstance();
		quality.setLabel("New Quality");
		return quality;
	}

	// --- test data ---

	public Quality postQuality(Project project, String label) {
		Quality quality = newEntityInstance();
		quality.setProject(project);
		quality.setLabel(label);
		saveEntity(quality);
		return quality;
	}
}