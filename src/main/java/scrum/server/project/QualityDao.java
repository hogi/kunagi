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

	public boolean createTestQuality(Project project, int variant) {
		Quality quality = newEntityInstance();
		quality.setProject(project);

		if (variant == 1) {
			quality.setLabel("Undeniable Success");
		} else if (variant == 2) {
			quality.setLabel("Orgasmic Joy-of-Use");
		} else if (variant == 3) {
			quality.setLabel("Effervescent Happiness");
		} else if (variant == 4) {
			quality.setLabel("Supersonic Communication");
		} else if (variant == 5) {
			quality.setLabel("Endless Freedom");
		} else if (variant == 6) {
			quality.setLabel("Flawless Integration");
		} else {
			return false;
		}

		saveEntity(quality);
		return true;
	}
}