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

	public Impediment postImpediment(Project project, Date dateCreated, String label, boolean closed) {
		Impediment im = newEntityInstance();
		im.setProject(project);
		im.setDate(dateCreated);
		im.setLabel(label);
		im.setClosed(closed);
		saveEntity(im);
		return im;
	}
}
