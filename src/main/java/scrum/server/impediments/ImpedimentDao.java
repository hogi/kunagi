package scrum.server.impediments;

import ilarkesto.base.time.Date;
import ilarkesto.fp.Predicate;
import scrum.server.project.Project;

public class ImpedimentDao extends GImpedimentDao {

	public Impediment getImpedimentByNumber(final int number, final Project project) {
		return getEntity(new Predicate<Impediment>() {

			public boolean test(Impediment t) {
				return t.isNumber(number) && t.isProject(project);
			}
		});
	}

	@Override
	public Impediment newEntityInstance() {
		Impediment impediment = super.newEntityInstance();
		impediment.setLabel("New Impediment");
		return impediment;
	}

	// --- test data ---

	public boolean createTestImpediment(Project project, int variant) {
		Impediment im = newEntityInstance();
		im.setProject(project);

		if (variant == 1) {
			im.setLabel("There is no documentation. Seriously.");
			im.setDescription("Someone promised that, I remember. Where is it?");
		} else if (variant == 2) {
			im.setLabel("Daily Scrums are not daily");
			im.setDescription("\"Daily Scrums\" are supposed to be daily. That's why they are called DAILY Scrums.");
			im.setClosed(true);
			im
					.setSolution("Fixed time and place to 09.00 p. m. at Starbucks every morning (except weekdays, weekends and holydays).");
		} else if (variant == 3) {
			im.setLabel("There is no coffee machine");
		} else {
			return false;
		}

		im.setDate(Date.beforeDays(variant * 5));
		saveEntity(im);
		return true;
	}
}
