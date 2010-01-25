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

	public void createTestQuality(Project project, int variant) {
		Quality quality = newEntityInstance();
		quality.setProject(project);

		if (variant == 1) {
			quality.setLabel("Corporate Design");
			quality.setDescription("All GUI elements must reflect the corporate design.");
		} else if (variant == 2) {
			quality.setLabel("Coffee shock");
			quality.setDescription("Developers must consume coffee during work at all costs.");
			quality.setTestDescription("At least 10 cups per day per developer.");
		}

		saveEntity(quality);
	}
}