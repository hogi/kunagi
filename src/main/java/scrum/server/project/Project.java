package scrum.server.project;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import scrum.server.admin.User;
import scrum.server.impediments.Impediment;
import scrum.server.impediments.ImpedimentDao;
import scrum.server.risks.Risk;
import scrum.server.risks.RiskDao;
import scrum.server.sprint.Sprint;

public class Project extends GProject {

	// --- dependencies ---

	private static ImpedimentDao impedimentDao;
	private static RequirementDao requirementDao;
	private static AttributeDao attributeDao;
	private static ProjectSprintSnapshotDao projectSprintSnapshotDao;
	private static RiskDao riskDao;

	public static void setRiskDao(RiskDao riskDao) {
		Project.riskDao = riskDao;
	}

	public static void setImpedimentDao(ImpedimentDao impedimentDao) {
		Project.impedimentDao = impedimentDao;
	}

	public static void setRequirementDao(RequirementDao storyDao) {
		Project.requirementDao = storyDao;
	}

	public static void setAttributeDao(AttributeDao attributeDao) {
		Project.attributeDao = attributeDao;
	}

	public static void setProjectSprintSnapshotDao(ProjectSprintSnapshotDao projectSprintSnapshotDao) {
		Project.projectSprintSnapshotDao = projectSprintSnapshotDao;
	}

	// --- ---

	public ProjectSprintSnapshot getCurrentSprintSnapshot() {
		ProjectSprintSnapshot snapshot = projectSprintSnapshotDao.getProjectSprintSnapshotBySprint(getCurrentSprint());
		if (snapshot == null) snapshot = createSprintSnapshot();
		return snapshot;
	}

	public int getRemainingWork() {
		int sum = 0;
		for (Requirement requirement : getRequirements()) {
			if (requirement.isClosed()) continue;
			Integer work = requirement.getEstimatedWork();
			if (work != null) sum += work;
		}
		return sum;
	}

	public int getBurnedWork() {
		int sum = 0;
		for (Requirement requirement : getRequirements()) {
			if (!requirement.isClosed()) continue;
			Integer work = requirement.getEstimatedWork();
			if (work != null) sum += work;
		}
		return sum;
	}

	public List<ProjectSprintSnapshot> getSprintSnapshots() {
		return projectSprintSnapshotDao.getProjectSprintSnapshotsByProject(this);
	}

	public void switchToNextSprint() {
		Sprint oldSprint = getCurrentSprint();

		getCurrentSprintSnapshot().update();

		Sprint newSprint = getNextSprint();
		if (newSprint == null) newSprint = createNextSprint();
		setCurrentSprint(newSprint);
		createNextSprint();

		createSprintSnapshot();
	}

	private ProjectSprintSnapshot createSprintSnapshot() {
		ProjectSprintSnapshot snapshot = projectSprintSnapshotDao.newEntityInstance();
		snapshot.setSprint(getCurrentSprint());
		snapshot.update();
		projectSprintSnapshotDao.saveEntity(snapshot);
		return snapshot;
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

	public Set<Risk> getRisks() {
		return riskDao.getRisksByProject(this);
	}

	public Set<Requirement> getRequirements() {
		return requirementDao.getRequirementsByProject(this);
	}

	public Set<Attribute> getAttributes() {
		return attributeDao.getAttributesByProject(this);
	}

	@Override
	public String toString() {
		return getLabel();
	}

	// --- test data ---

	public void addTestImpediments(int variant) {
		if (variant == 0) return;

		impedimentDao.createTestImpediment(this, 1);
		impedimentDao.createTestImpediment(this, 2);
		impedimentDao.createTestImpediment(this, 3);
	}

	public void addTestRisks(int variant) {
		if (variant == 0) return;

		riskDao.createTestRisk(this, 1);
		riskDao.createTestRisk(this, 2);
		riskDao.createTestRisk(this, 3);
		riskDao.createTestRisk(this, 4);
		riskDao.createTestRisk(this, 5);
		riskDao.createTestRisk(this, 6);
		riskDao.createTestRisk(this, 7);
	}

	public void addTestRequirements(int variant) {
		if (variant == 0) return;

		requirementDao.createTestRequirement(this, 1);
		requirementDao.createTestRequirement(this, 2);
		requirementDao.createTestRequirement(this, 3);
		requirementDao.createTestRequirement(this, 4);
		requirementDao.createTestRequirement(this, 5);
	}

	public void addTestAttributes(int variant) {
		if (variant == 0) return;

		attributeDao.createTestAttribute(this, 1);
		attributeDao.createTestAttribute(this, 2);
	}

	public void addTestSprints(int variant) {
		sprintDao.createTestSprint(this, 0);
		if (variant == 0) return;
		sprintDao.createTestSprint(this, 1);
		sprintDao.createTestSprint(this, 2);
		sprintDao.createTestSprint(this, 3);
	}
}
