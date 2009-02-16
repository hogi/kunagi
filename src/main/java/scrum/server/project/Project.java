package scrum.server.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import scrum.server.admin.User;
import scrum.server.impediments.Impediment;
import scrum.server.impediments.ImpedimentDao;
import scrum.server.sprint.Sprint;

public class Project extends GProject {

	// --- dependencies ---

	private static ImpedimentDao impedimentDao;
	private static RequirementDao requirementDao;
	private static ProjectSprintSnapshotDao projectSprintSnapshotDao;

	public Project(GProject template) {
		super(template);
	}

	public Project() {
		super(null);
	}

	public static void setImpedimentDao(ImpedimentDao impedimentDao) {
		Project.impedimentDao = impedimentDao;
	}

	public static void setRequirementDao(RequirementDao storyDao) {
		Project.requirementDao = storyDao;
	}

	public static void setProjectSprintSnapshotDao(ProjectSprintSnapshotDao projectSprintSnapshotDao) {
		Project.projectSprintSnapshotDao = projectSprintSnapshotDao;
	}

	// --- ---

	public List<ProjectSprintSnapshot> getSnapshots() {
		List<ProjectSprintSnapshot> ret = new ArrayList<ProjectSprintSnapshot>(projectSprintSnapshotDao
				.getProjectSprintSnapshotsByProject(this));
		Collections.sort(ret);
		return ret;
	}

	public void switchToNextSprint() {
		Sprint oldSprint = getCurrentSprint();

		Sprint newSprint = getNextSprint();
		if (newSprint == null) newSprint = createNextSprint();
		setCurrentSprint(newSprint);
		createNextSprint();
	}

	public Sprint createNextSprint() {
		Sprint sprint = sprintDao.newEntityInstance();
		sprint.setProject(this);
		sprint.setLabel("Next Sprint");
		if (isCurrentSprintSet()) sprint.setBegin(getCurrentSprint().getEnd());
		setNextSprint(sprint);
		return sprint;
	}

	public Set<User> getMembers() {
		Set<User> ret = new HashSet<User>();
		ret.add(getProductOwner());
		ret.add(getScrumMaster());
		ret.addAll(getTeamMembers());
		ret.addAll(getAdmins());
		return ret;
	}

	public boolean isMember(User user) {
		return containsTeamMember(user) || containsAdmin(user) || isProductOwner(user) || isScrumMaster(user);
	}

	public Set<Sprint> getSprints() {
		return sprintDao.getSprintsByProject(this);
	}

	public Set<Impediment> getImpediments() {
		return impedimentDao.getImpedimentsByProject(this);
	}

	public Set<Requirement> getRequirements() {
		return requirementDao.getRequirementsByProject(this);
	}

	@Override
	public String toString() {
		return getLabel();
	}

}
