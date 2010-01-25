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

	public void createTestImpediment(Project project, int variant) {
		Impediment im = newEntityInstance();
		im.setProject(project);
		im.setLabel("Impediment " + variant);
		im.setDate(Date.beforeDays(variant * 5));
		saveEntity(im);
	}

}
