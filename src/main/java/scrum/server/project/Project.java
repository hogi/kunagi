package scrum.server.project;

import ilarkesto.base.Money;
import ilarkesto.base.Str;
import ilarkesto.base.time.Date;
import ilarkesto.persistence.AEntity;
import ilarkesto.rss.Rss20Builder;
import ilarkesto.search.Searchable;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import scrum.client.UsersStatusData;
import scrum.server.admin.ProjectUserConfig;
import scrum.server.admin.ProjectUserConfigDao;
import scrum.server.admin.User;
import scrum.server.calendar.SimpleEvent;
import scrum.server.calendar.SimpleEventDao;
import scrum.server.collaboration.Wikipage;
import scrum.server.collaboration.WikipageDao;
import scrum.server.files.File;
import scrum.server.files.FileDao;
import scrum.server.impediments.Impediment;
import scrum.server.impediments.ImpedimentDao;
import scrum.server.issues.Issue;
import scrum.server.issues.IssueDao;
import scrum.server.journal.ProjectEvent;
import scrum.server.journal.ProjectEventDao;
import scrum.server.risks.Risk;
import scrum.server.risks.RiskDao;
import scrum.server.sprint.Sprint;
import scrum.server.sprint.Task;
import scrum.server.sprint.TaskDao;

public class Project extends GProject {

	private transient UsersStatusData usersStatus;

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
	private static ProjectEventDao projectEventDao;
	private static SimpleEventDao simpleEventDao;
	private static FileDao fileDao;

	public static void setFileDao(FileDao fileDao) {
		Project.fileDao = fileDao;
	}

	public static void setSimpleEventDao(SimpleEventDao simpleEventDao) {
		Project.simpleEventDao = simpleEventDao;
	}

	public static void setProjectEventDao(ProjectEventDao projectEventDao) {
		Project.projectEventDao = projectEventDao;
	}

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

	@SuppressWarnings("unchecked")
	public ArrayList<AEntity> search(String text) {
		String[] keys = Str.tokenize(text, " ");
		ArrayList ret = new ArrayList();
		ret.addAll(getMatching(getRequirements(), keys));
		ret.addAll(getMatching(getQualitys(), keys));
		ret.addAll(getMatching(getTasks(), keys));
		ret.addAll(getMatching(getWikipages(), keys));
		ret.addAll(getMatching(getIssues(), keys));
		ret.addAll(getMatching(getImpediments(), keys));
		ret.addAll(getMatching(getRisks(), keys));
		ret.addAll(getMatching(getFiles(), keys));
		return ret;
	}

	private <T extends Searchable> List<T> getMatching(Collection<T> entities, String[] keys) {
		List<T> ret = new ArrayList<T>();
		for (T entity : entities) {
			if (matchesKeys(entity, keys)) ret.add(entity);
		}
		return ret;
	}

	private boolean matchesKeys(Searchable entity, String[] keys) {
		for (String key : keys) {
			if (!entity.matchesKey(key)) return false;
		}
		return true;
	}

	public void writeJournalAsRss(OutputStream out, String encoding, String baseUrl) {
		Rss20Builder rss = new Rss20Builder();
		rss.setTitle(getLabel() + " Event Journal");
		rss.setLanguage("en");
		rss.setLink(baseUrl);
		for (ProjectEvent event : getProjectEvents()) {
			Rss20Builder.Item item = rss.addItem();
			item.setTitle(event.getLabel());
			item.setDescription(event.getLabel());
			item.setLink(baseUrl);
			item.setGuid(event.getId());
			item.setPubDate(event.getDateAndTime());
		}
		rss.sortItems();
		rss.write(out, encoding);
	}

	public Set<SimpleEvent> getCalendarEvents() {
		return simpleEventDao.getSimpleEventsByProject(this);
	}

	public Set<ProjectEvent> getProjectEvents() {
		return projectEventDao.getProjectEventsByProject(this);
	}

	public UsersStatusData getUsersStatus() {
		if (usersStatus == null) usersStatus = new UsersStatusData();
		return usersStatus;
	}

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

	public synchronized int generateFileNumber() {
		int number = getLastFileNumber() + 1;
		setLastFileNumber(number);
		return number;
	}

	public synchronized int generateRequirementNumber() {
		int number = getLastRequirementNumber() + 1;
		setLastRequirementNumber(number);
		return number;
	}

	public synchronized int generateImpedimentNumber() {
		int number = getLastImpedimentNumber() + 1;
		setLastImpedimentNumber(number);
		return number;
	}

	public synchronized int generateRiskNumber() {
		int number = getLastRiskNumber() + 1;
		setLastRiskNumber(number);
		return number;
	}

	public synchronized int generateIssueNumber() {
		int number = getLastIssueNumber() + 1;
		setLastIssueNumber(number);
		return number;
	}

	public synchronized int generateQualityNumber() {
		int number = getLastQualityNumber() + 1;
		setLastQualityNumber(number);
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
		oldSprint.close();
		oldSprint.setEnd(Date.today());

		getCurrentSprintSnapshot().update();

		Sprint newSprint = getNextSprint();
		if (newSprint == null) newSprint = createNextSprint();
		if (!newSprint.isBeginSet() || newSprint.getBegin().isPast()) newSprint.setBegin(Date.today());
		if (!newSprint.isEndSet() || newSprint.getEnd().isBeforeOrSame(newSprint.getBegin()))
			newSprint.setEnd(newSprint.getBegin().addDays(oldSprint.getLengthInDays()));

		setCurrentSprint(newSprint);
		createNextSprint();

		createSprintSnapshot();

		for (Task task : oldSprint.getTasks()) {
			if (task.isClosed()) {
				taskDao.deleteEntity(task);
			}
		}
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
		if (isCurrentSprintSet()) {
			sprint.setBegin(getCurrentSprint().getEnd());
			Integer length = getCurrentSprint().getLengthInDays();
			if (length != null) sprint.setEnd(sprint.getBegin().addDays(length));
		}
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

	public Set<File> getFiles() {
		return fileDao.getFilesByProject(this);
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
		if (!isPunishmentUnitSet()) {
			setPunishmentUnit(Money.EUR);
		}
		if (getPunishmentFactor() == 0) {
			setPunishmentFactor(1);
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

	public void addTestSimpleEvents(int variant) {
		if (variant == 0) return;

		simpleEventDao.createTestEvent(this, 1);
		simpleEventDao.createTestEvent(this, 2);
		simpleEventDao.createTestEvent(this, 3);
	}

	public void addTestEvents(int variant) {
		if (variant == 0) return;

		projectEventDao.createTestEvent(this, 1);
		projectEventDao.createTestEvent(this, 2);
		projectEventDao.createTestEvent(this, 3);
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
