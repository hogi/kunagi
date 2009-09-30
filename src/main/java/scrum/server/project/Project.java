package scrum.server.project;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import scrum.server.admin.ProjectUserConfig;
import scrum.server.admin.ProjectUserConfigDao;
import scrum.server.admin.User;
import scrum.server.collaboration.Wikipage;
import scrum.server.collaboration.WikipageDao;
import scrum.server.impediments.Impediment;
import scrum.server.impediments.ImpedimentDao;
import scrum.server.issues.Issue;
import scrum.server.issues.IssueDao;
import scrum.server.risks.Risk;
import scrum.server.risks.RiskDao;
import scrum.server.sprint.Sprint;
import scrum.server.sprint.Task;
import scrum.server.sprint.TaskDao;

public class Project extends GProject {

	// --- dependencies ---

	private static ProjectUserConfigDao projectUserConfigDao;
	private static ImpedimentDao impedimentDao;
	private static IssueDao issueDao;
	private static RequirementDao requirementDao;
	private static QualityDao qualityDao;
	private static ProjectSprintSnapshotDao projectSprintSnapshotDao;
	private static RiskDao riskDao;
	private static TaskDao taskDao;
	private static WikipageDao wikipageDao;

	public static void setWikipageDao(WikipageDao wikipageDao) {
		Project.wikipageDao = wikipageDao;
	}

	public static void setProjectUserConfigDao(ProjectUserConfigDao projectUserConfigDao) {
		Project.projectUserConfigDao = projectUserConfigDao;
	}

	public static void setIssueDao(IssueDao issueDao) {
		Project.issueDao = issueDao;
	}

	public static void setTaskDao(TaskDao taskDao) {
		Project.taskDao = taskDao;
	}

	public static void setRiskDao(RiskDao riskDao) {
		Project.riskDao = riskDao;
	}

	public static void setImpedimentDao(ImpedimentDao impedimentDao) {
		Project.impedimentDao = impedimentDao;
	}

	public static void setRequirementDao(RequirementDao storyDao) {
		Project.requirementDao = storyDao;
	}

	public static void setQualityDao(QualityDao qualityDao) {
		Project.qualityDao = qualityDao;
	}

	public static void setProjectSprintSnapshotDao(ProjectSprintSnapshotDao projectSprintSnapshotDao) {
		Project.projectSprintSnapshotDao = projectSprintSnapshotDao;
	}

	// --- ---

	public Set<ProjectUserConfig> getUserConfigs() {
		Set<ProjectUserConfig> configs = new HashSet<ProjectUserConfig>();
		for (User user : getParticipants()) {
			configs.add(getUserConfig(user));
		}
		return configs;
	}

	public ProjectUserConfig getUserConfig(User user) {
		return projectUserConfigDao.getProjectUserConfig(this, user);
	}

	public Set<Wikipage> getWikipages() {
		return wikipageDao.getWikipagesByProject(this);
	}

	public Set<Task> getTasks() {
		return taskDao.getTasksByProject(this);
	}

	public Requirement getRequirementByNumber(int number) {
		return requirementDao.getRequirementByNumber(number, this);
	}

	public Task getTaskByNumber(int number) {
		return taskDao.getTaskByNumber(number, this);
	}

	public synchronized int generateTaskNumber() {
		int number = getLastTaskNumber() + 1;
		setLastTaskNumber(number);
		return number;
	}

	public synchronized int generateRequirementNumber() {
		int number = getLastRequirementNumber() + 1;
		setLastRequirementNumber(number);
		return number;
	}

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
		sprintDao.saveEntity(sprint);
		setNextSprint(sprint);
		return sprint;
	}

	public Set<Sprint> getSprints() {
		return sprintDao.getSprintsByProject(this);
	}

	public Set<Impediment> getImpediments() {
		return impedimentDao.getImpedimentsByProject(this);
	}

	public Set<Issue> getIssues() {
		return issueDao.getIssuesByProject(this);
	}

	public Set<Risk> getRisks() {
		return riskDao.getRisksByProject(this);
	}

	public Set<Requirement> getRequirements() {
		return requirementDao.getRequirementsByProject(this);
	}

	public Set<Quality> getQualitys() {
		return qualityDao.getQualitysByProject(this);
	}

	@Override
	public String toString() {
		return getLabel();
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		addParticipants(getAdmins());
		addParticipants(getProductOwners());
		addParticipants(getScrumMasters());
		addParticipants(getTeamMembers());
		if (!isCurrentSprintSet()) {
			Sprint sprint = sprintDao.newEntityInstance();
			sprint.setProject(this);
			sprintDao.saveEntity(sprint);
			setCurrentSprint(sprint);
		}
		if (!isNextSprintSet()) {
			createNextSprint();
		}
	}

	public boolean isVisibleFor(User user) {
		return containsParticipant(user);
	}

	public boolean isDeletableBy(User user) {
		return containsAdmin(user);
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

	public void addTestIssues(int variant) {
		if (variant == 0) return;

		issueDao.createTestIssue(this, 1);
		issueDao.createTestIssue(this, 2);
		issueDao.createTestIssue(this, 3);
		issueDao.createTestIssue(this, 4);
		issueDao.createTestIssue(this, 5);
	}

	public void addTestRequirements(int variant) {
		if (variant == 0) return;

		requirementDao.createTestRequirement(this, 1);
		requirementDao.createTestRequirement(this, 2);
		requirementDao.createTestRequirement(this, 3);
		requirementDao.createTestRequirement(this, 4);
		requirementDao.createTestRequirement(this, 5);
	}

	public void addTestQualitys(int variant) {
		if (variant == 0) return;

		qualityDao.createTestQuality(this, 1);
		qualityDao.createTestQuality(this, 2);
	}

	public void addTestSprints(int variant) {
		sprintDao.createTestSprint(this, 0);
		if (variant == 0) return;
		sprintDao.createTestSprint(this, 1);
		sprintDao.createTestSprint(this, 2);
		sprintDao.createTestSprint(this, 3);
	}

}
