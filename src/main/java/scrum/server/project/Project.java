package scrum.server.project;

import java.util.Set;

import scrum.server.impediments.Impediment;
import scrum.server.impediments.ImpedimentDao;

public class Project extends GProject {

	// --- dependencies ---

	private static ImpedimentDao impedimentDao;

	public Project(GProject template) {
		super(template);
	}

	public Project() {
		super(null);
	}

	public static void setImpedimentDao(ImpedimentDao impedimentDao) {
		Project.impedimentDao = impedimentDao;
	}

	// --- ---

	public Set<Impediment> getImpediments() {
		return impedimentDao.getImpedimentsByProject(this);
	}

	@Override
	public String toString() {
		return getLabel();
	}

}
