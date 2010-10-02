package scrum.server.project;

import ilarkesto.base.Money;
import ilarkesto.base.Str;
import ilarkesto.base.Utl;
import ilarkesto.base.time.Date;
import ilarkesto.persistence.AEntity;
import ilarkesto.persistence.Persist;
import ilarkesto.rss.Rss20Builder;
import ilarkesto.search.Searchable;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import scrum.client.UsersStatusData;
import scrum.server.ScrumConfig;
import scrum.server.admin.ProjectUserConfig;
import scrum.server.admin.ProjectUserConfigDao;
import scrum.server.admin.User;
import scrum.server.calendar.SimpleEvent;
import scrum.server.calendar.SimpleEventDao;
import scrum.server.collaboration.Comment;
import scrum.server.collaboration.CommentDao;
import scrum.server.collaboration.Subject;
import scrum.server.collaboration.SubjectDao;
import scrum.server.collaboration.Wikipage;
import scrum.server.collaboration.WikipageDao;
import scrum.server.estimation.RequirementEstimationVote;
import scrum.server.estimation.RequirementEstimationVoteDao;
import scrum.server.files.File;
import scrum.server.files.FileDao;
import scrum.server.impediments.Impediment;
import scrum.server.impediments.ImpedimentDao;
import scrum.server.issues.Issue;
import scrum.server.issues.IssueDao;
import scrum.server.journal.ProjectEvent;
import scrum.server.journal.ProjectEventDao;
import scrum.server.pr.BlogEntry;
import scrum.server.pr.BlogEntryDao;
import scrum.server.release.Release;
import scrum.server.release.ReleaseDao;
import scrum.server.risks.Risk;
import scrum.server.risks.RiskDao;
import scrum.server.sprint.Sprint;
import scrum.server.sprint.SprintDaySnapshot;
import scrum.server.sprint.SprintDaySnapshotDao;
import scrum.server.sprint.Task;
import scrum.server.sprint.TaskDao;

public class Project extends GProject {

	private transient UsersStatusData usersStatus;
	private transient Comparator<Requirement> requirementsOrderComparator;

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
	private static ScrumConfig config;
	private static CommentDao commentDao;
	private static ReleaseDao releaseDao;
	private static RequirementEstimationVoteDao requirementEstimationVoteDao;
	private static SprintDaySnapshotDao sprintDaySnapshotDao;
	private static SubjectDao subjectDao;
	private static BlogEntryDao blogEntryDao;

	public static void setBlogEntryDao(BlogEntryDao blogEntryDao) {
		Project.blogEntryDao = blogEntryDao;
	}

	public static void setSubjectDao(SubjectDao subjectDao) {
		Project.subjectDao = subjectDao;
	}

	public static void setCommentDao(CommentDao commentDao) {
		Project.commentDao = commentDao;
	}

	public static void setSprintDaySnapshotDao(SprintDaySnapshotDao sprintDaySnapshotDao) {
		Project.sprintDaySnapshotDao = sprintDaySnapshotDao;
	}

	public static void setRequirementEstimationVoteDao(RequirementEstimationVoteDao requirementEstimationVoteDao) {
		Project.requirementEstimationVoteDao = requirementEstimationVoteDao;
	}

	public static void setReleaseDao(ReleaseDao releaseDao) {
		Project.releaseDao = releaseDao;
	}

	public static void setConfig(ScrumConfig config) {
		Project.config = config;
	}

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

	public boolean containsParticipantWithVerifiedEmail() {
		for (User user : getParticipants()) {
			if (user.isEmailVerified()) return true;
		}
		return false;
	}

	public void updateRequirementsOrder(List<Requirement> requirements) {
		setRequirementsOrderIds(Persist.getIdsAsList(requirements));
	}

	public synchronized void updateHomepage() {
		if (!isHomepageDirSet()) return;
		getHomepageUpdater().processAll();
	}

	public synchronized void updateHomepage(AEntity entity, boolean forceUpdate) {
		if (!isHomepageDirSet()) return;
		if (!forceUpdate && !isAutoUpdateHomepage()) return;
		getHomepageUpdater().processEntityTemplate(entity);
	}

	public java.io.File getHomepageDirFile() {
		if (!isHomepageDirSet()) return null;
		return new java.io.File(getHomepageDir());
	}

	public String getHomepageVelocityDir() {
		if (!isHomepageDirSet()) return null;
		return getHomepageDir() + "/velocity";
	}

	public HomepageUpdater getHomepageUpdater() {
		return new HomepageUpdater(this);
	}

	public void scanFiles() {
		java.io.File dir = new java.io.File(getFileRepositoryPath());
		java.io.File[] files = dir.listFiles();
		if (files != null) {

			for (java.io.File f : files) {
				File file = fileDao.getFilesByName(f.getName(), this);
				if (file == null) {
					file = fileDao.postFile(f, this);
				}
			}
		}
	}

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
		ret.addAll(getMatching(getReleases(), keys));
		ret.addAll(getMatching(getBlogEntrys(), keys));
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
		rss.setLink(baseUrl + "#project=" + getId());
		for (ProjectEvent event : getLatestProjectEvents()) {
			Rss20Builder.Item item = rss.addItem();
			item.setTitle(event.getLabel());
			item.setDescription(event.getLabel());
			String link = baseUrl + "#project=" + getId();
			if (event.isSubjectSet()) link += "|entity=" + event.getSubjectId();
			item.setLink(link);
			item.setGuid(event.getId());
			item.setPubDate(event.getDateAndTime());
		}
		rss.sortItems();
		rss.write(out, encoding);
	}

	public String getFileRepositoryPath() {
		return config.getFileRepositoryPath() + "/" + getId();
	}

	public Set<SimpleEvent> getCalendarEvents() {
		return simpleEventDao.getSimpleEventsByProject(this);
	}

	public List<ProjectEvent> getLatestProjectEvents() {
		List<ProjectEvent> events = new LinkedList<ProjectEvent>(getProjectEvents());
		int max = 30;
		if (events.size() <= max) return events;
		Collections.sort(events);
		while (events.size() > max) {
			events.remove(0);
		}
		return events;
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

	public AEntity getEntityByReference(String reference) {
		if (reference.length() > 4 && reference.startsWith("[[")) {
			String pageName = reference.substring(2, reference.length() - 2);
			return getWikipageByName(pageName);
		}

		int number = Integer.parseInt(reference.substring(scrum.client.project.Requirement.REFERENCE_PREFIX.length()));

		if (reference.startsWith(scrum.client.project.Requirement.REFERENCE_PREFIX)) {
			return getRequirementByNumber(number);
		} else if (reference.startsWith(scrum.client.project.Quality.REFERENCE_PREFIX)) {
			return getQualityByNumber(number);
		} else if (reference.startsWith(scrum.client.sprint.Task.REFERENCE_PREFIX)) {
			return getTaskByNumber(number);
		} else if (reference.startsWith(scrum.client.impediments.Impediment.REFERENCE_PREFIX)) {
			return getImpedimentByNumber(number);
		} else if (reference.startsWith(scrum.client.issues.Issue.REFERENCE_PREFIX)) {
			return getIssueByNumber(number);
		} else if (reference.startsWith(scrum.client.sprint.Sprint.REFERENCE_PREFIX)) {
			return getSprintByNumber(number);
		} else if (reference.startsWith(scrum.client.collaboration.Subject.REFERENCE_PREFIX)) {
			return getSubjectByNumber(number);
		} else if (reference.startsWith(scrum.client.files.File.REFERENCE_PREFIX)) {
			return getFileByNumber(number);
		} else if (reference.startsWith(scrum.client.calendar.SimpleEvent.REFERENCE_PREFIX)) {
			return getSimpleEventByNumber(number);
		} else if (reference.startsWith(scrum.client.release.Release.REFERENCE_PREFIX)) {
			return getReleaseByNumber(number);
		} else if (reference.startsWith(scrum.client.pr.BlogEntry.REFERENCE_PREFIX)) { return getBlogEntryByNumber(number); }

		return null;
	}

	public Requirement getRequirementByNumber(int number) {
		return requirementDao.getRequirementByNumber(number, this);
	}

	public Task getTaskByNumber(int number) {
		return taskDao.getTaskByNumber(number, this);
	}

	public Quality getQualityByNumber(int number) {
		return qualityDao.getQualityByNumber(number, this);
	}

	public Issue getIssueByNumber(int number) {
		return issueDao.getIssueByNumber(number, this);
	}

	public Sprint getSprintByNumber(int number) {
		return sprintDao.getSprintByNumber(number, this);
	}

	public Impediment getImpedimentByNumber(int number) {
		return impedimentDao.getImpedimentByNumber(number, this);
	}

	public Subject getSubjectByNumber(int number) {
		return subjectDao.getSubjectsByNumber(number, this);
	}

	public File getFileByNumber(int number) {
		return fileDao.getFileByNumber(number, this);
	}

	public File getFileByReference(String reference) {
		return getFileByNumber(Integer.parseInt(reference.substring(3)));
	}

	public SimpleEvent getSimpleEventByNumber(int number) {
		return simpleEventDao.getSimpleEventByNumber(number, this);
	}

	public Release getReleaseByNumber(int number) {
		return releaseDao.getReleaseByNumber(number, this);
	}

	public BlogEntry getBlogEntryByNumber(int number) {
		return blogEntryDao.getBlogEntryByNumber(number, this);
	}

	public Wikipage getWikipageByName(String name) {
		return wikipageDao.getWikipageByName(name, this);
	}

	public synchronized int generateTaskNumber() {
		int number = getLastTaskNumber() + 1;
		setLastTaskNumber(number);
		return number;
	}

	public synchronized int generateEventNumber() {
		int number = getLastEventNumber() + 1;
		setLastEventNumber(number);
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

	public synchronized int generateSubjectNumber() {
		int number = getLastSubjectNumber() + 1;
		setLastSubjectNumber(number);
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

	public synchronized int generateSprintNumber() {
		int number = getLastSprintNumber() + 1;
		setLastSprintNumber(number);
		return number;
	}

	public synchronized int generateReleaseNumber() {
		int number = getLastReleaseNumber() + 1;
		setLastReleaseNumber(number);
		return number;
	}

	public synchronized int generateQualityNumber() {
		int number = getLastQualityNumber() + 1;
		setLastQualityNumber(number);
		return number;
	}

	public synchronized int generateBlogEntryNumber() {
		int number = getLastBlogEntryNumber() + 1;
		setLastBlogEntryNumber(number);
		return number;
	}

	public Release getCurrentRelease() {
		return releaseDao.getCurrentRelease(this);
	}

	public Release getNextRelease() {
		return releaseDao.getNextRelease(this);
	}

	public ProjectSprintSnapshot getCurrentSprintSnapshot() {
		ProjectSprintSnapshot snapshot = projectSprintSnapshotDao.getProjectSprintSnapshotBySprint(getCurrentSprint());
		if (snapshot == null) snapshot = createSprintSnapshot();
		return snapshot;
	}

	// public int getRemainingWork() {
	// int sum = 0;
	// for (Requirement requirement : getRequirements()) {
	// if (requirement.isClosed()) continue;
	// Integer work = requirement.getEstimatedWork();
	// if (work != null) sum += work;
	// }
	// return sum;
	// }
	//
	// public int getBurnedWork() {
	// int sum = 0;
	// for (Requirement requirement : getRequirements()) {
	// if (!requirement.isClosed()) continue;
	// Integer work = requirement.getEstimatedWork();
	// if (work != null) sum += work;
	// }
	// return sum;
	// }

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

	public Set<Issue> getAcceptedIssues() {
		return issueDao.getAcceptedIssues(this);
	}

	public Set<Issue> getClosedIssues() {
		return issueDao.getClosedIssues(this);
	}

	public Set<Issue> getOpenIssues() {
		return issueDao.getOpenIssues(this);
	}

	public Set<Issue> getPublishedIssues() {
		return issueDao.getPublishedIssues(this);
	}

	public Set<Issue> getBugsInCurrentRelease() {
		Release release = getCurrentRelease();
		Release nextRelease = getNextRelease();
		if (release == null) return getOpenBugs();
		Set<Issue> ret = new HashSet<Issue>();
		ret.addAll(getOpenBugs());
		for (Issue issue : getClosedIssues()) {
			if (issue.containsFixRelease(nextRelease) && !issue.containsFixRelease(release)) ret.add(issue);
		}
		return ret;
	}

	public Set<Issue> getOpenBugs() {
		return issueDao.getOpenBugs(this);
	}

	public Set<Issue> getOpenIdeas() {
		return issueDao.getOpenIdeas(this);
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

	public Set<Subject> getSubjects() {
		return subjectDao.getSubjectsByProject(this);
	}

	public Set<Release> getReleases() {
		return releaseDao.getReleasesByProject(this);
	}

	public Set<BlogEntry> getBlogEntrys() {
		return blogEntryDao.getBlogEntrysByProject(this);
	}

	public Set<RequirementEstimationVote> getRequirementEstimationVotes() {
		Set<RequirementEstimationVote> ret = new HashSet<RequirementEstimationVote>();
		for (Requirement requirement : getRequirements()) {
			ret.addAll(requirement.getEstimationVotes());
		}
		return ret;
	}

	public Set<SprintDaySnapshot> getSprintDaySnapshots() {
		Set<SprintDaySnapshot> ret = new HashSet<SprintDaySnapshot>();
		for (Sprint sprint : getSprints()) {
			ret.addAll(sprint.getDaySnapshots());
		}
		return ret;
	}

	public Set<SprintDaySnapshot> getExistingSprintDaySnapshots() {
		Set<SprintDaySnapshot> ret = new HashSet<SprintDaySnapshot>();
		for (Sprint sprint : getSprints()) {
			ret.addAll(sprint.getExistingDaySnapshots());
		}
		return ret;
	}

	public Set<SimpleEvent> getSimpleEvents() {
		return simpleEventDao.getSimpleEventsByProject(this);
	}

	public Set<Comment> getAllComments() {
		return getComments(false);
	}

	public Set<Comment> getLatestComments() {
		return getComments(true);
	}

	private Set<Comment> getComments(boolean latestOnly) {
		Set<Comment> ret = new HashSet<Comment>();
		ret.addAll(getComments(Arrays.asList(this), latestOnly));
		ret.addAll(getComments(getSprints(), latestOnly));
		ret.addAll(getComments(getParticipants(), latestOnly));
		ret.addAll(getComments(getRequirements(), latestOnly));
		ret.addAll(getComments(getQualitys(), latestOnly));
		ret.addAll(getComments(getTasks(), latestOnly));
		ret.addAll(getComments(getImpediments(), latestOnly));
		ret.addAll(getComments(getIssues(), latestOnly));
		ret.addAll(getComments(getRisks(), latestOnly));
		ret.addAll(getComments(getWikipages(), latestOnly));
		ret.addAll(getComments(getSimpleEvents(), latestOnly));
		ret.addAll(getComments(getFiles(), latestOnly));
		ret.addAll(getComments(getReleases(), latestOnly));
		ret.addAll(getComments(getSprintSnapshots(), latestOnly));
		ret.addAll(getComments(getRequirementEstimationVotes(), latestOnly));
		ret.addAll(getComments(getUserConfigs(), latestOnly));
		ret.addAll(getComments(getProjectEvents(), latestOnly));
		ret.addAll(getComments(getSubjects(), latestOnly));
		ret.addAll(getComments(getReleases(), latestOnly));
		ret.addAll(getComments(getBlogEntrys(), latestOnly));
		return ret;
	}

	private Set<Comment> getComments(Collection<? extends AEntity> entities, boolean latestOnly) {
		Set<Comment> ret = new HashSet<Comment>();
		for (AEntity entity : entities) {
			Set<Comment> comments = commentDao.getCommentsByParent(entity);
			ret.addAll(latestOnly ? getLatest(comments) : comments);
		}
		return ret;
	}

	private Set<Comment> getLatest(Set<Comment> comments) {
		if (comments.size() < 2) return comments;
		Comment latest = null;
		for (Comment comment : comments) {
			if (latest == null || comment.getDateAndTime().isAfter(latest.getDateAndTime())) latest = comment;
		}
		assert latest != null;
		return Utl.toSet(latest);
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
		if (!isHomepageDirSet()) setAutoUpdateHomepage(false);
	}

	@Override
	public boolean isVisibleFor(User user) {
		return (user != null && user.isAdmin()) || containsParticipant(user) || containsAdmin(user);
	}

	public boolean isEditableBy(User user) {
		return isVisibleFor(user);
	}

	@Override
	public boolean isDeletableBy(User user) {
		if (user != null && user.isAdmin()) return true;
		return containsAdmin(user);
	}

	// --- test data ---

	public void addTestImpediments() {
		Impediment imp = null;

		// no documentation
		imp = impedimentDao.postImpediment(this, Date.randomPast(5), "There is no documentation. Seriously.", false);
		imp.setDescription("Someone promised that, I remember. Where is it?");

		// no daily scrums
		imp = impedimentDao.postImpediment(this, Date.randomPast(5), "Daily Scrums are not daily", true);
		imp.setDescription("\"Daily Scrums\" are supposed to be daily. That's why they are called DAILY Scrums.");
		imp.setSolution("Fixed time and place to 09.00 p. m. at Starbucks every morning (except weekdays, weekends and holydays).");

		// no coffee
		imp = impedimentDao.postImpediment(this, Date.randomPast(5), "There is no coffee machine", false);
	}

	public void addTestRisks() {
		Risk rsk = null;

		// god
		rsk = riskDao.postRisk(this, "God comes to judge the living and the dead", 0, 100);
		rsk.setImpactMitigation("Nothing we can do here.");

		// duke
		rsk = riskDao.postRisk(this, "Duke Nukem Forever is released", 20, 100);
		rsk.setProbabilityMitigation("Nothing to mitigate here. The probability is close to nothing.");
		rsk.setImpactMitigation("Nothing to mitigate here. Everyone will be playing. Everything will be lost.");

		// sudden death
		rsk = riskDao.postRisk(this, "Sudden Death", 50, 40);
		rsk.setImpactMitigation("Go to the roof if it's by rising sea level.");
	}

	public void addTestSimpleEvents() {
		// let people generate their own events
	}

	public void addTestEvents() {
		// no test events
	}

	public void addTestIssues() {
		Issue iss = null;

		// noobs
		iss = issueDao.postIssue(this, "thiz cr4p don't work, n00bz!!1");
		iss.setDescription("go home, u noobz ..#");

		// eclipse integration
		iss = issueDao.postIssue(this, "I want eclipse integration");
		iss.setDescription("I would be really nice if eclipse commits would be represented in Kunagi! Thank you!");

		// link bug
		iss = issueDao.postIssue(this, "Bug: Links don't work");
		iss.setDescription("When I try to post links to other pages, I get links to the Wiki. WTF?");

		// date crash
		iss = issueDao.postIssue(this, "Crash when using Dates after 2012");
		iss.setDescription("The program crashes whenever I enter dates after 2012. Can't figure out what the problem is though.");
		iss.setAcceptDate(Date.beforeDays(2));
		iss.setUrgent(true);
		iss.setSeverity(scrum.client.issues.Issue.SEVERE);

		// gui bug
		iss = issueDao.postIssue(this, "GUI inconsistency between PB and SB");
		iss.setDescription("The order of Qualities and Tests is different between widgets in the PB and SB. It should be the same.");
		iss.setAcceptDate(Date.beforeDays(35));
		iss.setUrgent(true);
		iss.setSeverity(scrum.client.issues.Issue.MINOR);

		// navi display bug
		iss = issueDao.postIssue(this, "navigation displays wrong current view");
		iss.setDescription("When I open the Whiteboard, \"Sprint Backlog\" is selected in the navigation. Same for other jumps.");
		iss.setAcceptDate(Date.today());
		iss.setUrgent(true);
		iss.setSeverity(scrum.client.issues.Issue.MINOR);
		iss.setIssuerName("Witek");
		iss.setIssuerEmail("wi@koczewski.de");

		// terrific pb suggestion
		iss = issueDao.postIssue(this, "Product Backlog should be terrific, not amazing");
		iss.setDescription("56% of users want a terrific PB, not an amazing one. We should change that in one of the upcoming releases.");
		iss.setAcceptDate(Date.today());

		// flattr
		iss = issueDao.postIssue(this, "Add a flattr-button");
		iss.setDescription("See [http://flattr.com].");
		iss.setCloseDate(Date.beforeDays(1));

		// thank you
		iss = issueDao.postIssue(this, "I like this software, thank you!");
		iss.setDescription("I'm using Kunagi for my own project now. Thanks for the great work.");
		iss.setCloseDate(Date.today());
	}

	public void addTestRequirements() {
		Requirement req = null;
		List<Requirement> reqOrder = new LinkedList<Requirement>();

		// Unsurpassed Concept
		req = requirementDao.postRequirement(this, "Unsurpassed Concept", 3f);
		req.setDescription("As a Product Owner I want the concept to be unsurpassable so I don't have to worry about ROI anymore.");
		reqOrder.add(req);
		req.setSprint(getCurrentSprint());
		taskDao.postTask(req, "Create Concept", 20);
		taskDao.postTask(req, "Make Sure All Others Are Inferior", 50);

		// Amazing Product Backlog
		req = requirementDao.postRequirement(this, "Amazing Product Backlog", 2f);
		req.setDescription("As a Product Owner I want my Backlog to be amazing so that people stand in awe.");
		reqOrder.add(req);
		req.setSprint(getCurrentSprint());
		taskDao.postTask(req, "Creation of Storys", 10);
		taskDao.postTask(req, "Intelligent Design of Storys", 10);
		taskDao.postTask(req, "Deletion of Storys", 10);

		// Functional Quality Backlog
		req = requirementDao.postRequirement(this, "Functional Quality Backlog", 1f);
		req.setDescription("As a Product Owner I want my non-functional Requirements to be functional, so I can use them.");
		reqOrder.add(req);
		req.setSprint(getCurrentSprint());
		taskDao.postTask(req, "Copy And Paste Product Backlog", 10);
		taskDao.postTask(req, "Marry Storys and Qualitys", 10);

		// Groundbraking Scrum Backlog
		req = requirementDao.postRequirement(this, "Groundbraking Scrum Backlog", 1f);
		req.setDescription("As a Team member I want the Scrum Backlog to be groundbreaking, so that everybody can see that I am the most important here.");
		reqOrder.add(req);
		req.setSprint(getCurrentSprint());
		taskDao.postTask(req, "Create Tasks", 10);
		taskDao.postTask(req, "Create More Tasks", 10);

		// Breathtaking Whiteboard
		req = requirementDao.postRequirement(this, "Breathtaking Whiteboard", 8f);
		req.setDescription("As a Team member I want the current state of things to be displayed on a Whiteboard, so I can play around when I am bored.");
		reqOrder.add(req);

		// Empowering Impediment List
		req = requirementDao.postRequirement(this, "Empowering Impediment List", 2f);
		req.setDescription("As a Scrum Master I want the Impedimen List to be empowering. Best thing would be self-resolving Impediments.");
		reqOrder.add(req);

		// Divine Risk Management
		req = requirementDao.postRequirement(this, "Divine Risk Management", 5f);
		req.setDescription("As a Team member, I want Risk Management to be devine. Wait, that makes Risk Management superfluous, I guess.");
		reqOrder.add(req);

		// Miraculous Issue Management
		req = requirementDao.postRequirement(this, "Miraculous Issue Management", 3f);
		req.setDescription("As a Product Owner I want my Issue Management to be miraculous, so that Issues close themselves AND sometimes even each other.");
		reqOrder.add(req);

		// Immaculate Bug Tracking
		req = requirementDao.postRequirement(this, "Immaculate Bug Tracking", 2f);
		req.setDescription("As a Team member I want immaculate Bug Tracking. A Bug Tracking containing no bugs would be suitable.");
		reqOrder.add(req);

		// Unbeatable Planning Poker
		req = requirementDao.postRequirement(this, "Unbeatable Planning Poker", null);
		req.setDescription("As I User I want Planning Poker to be so good that nobody can beat it.");
		reqOrder.add(req);

		// Enlightening Wiki
		req = requirementDao.postRequirement(this, "Enlightening Wiki", 5f);
		req.setDescription("As a User I want the Wiki to enlighten me so that I am enlightened after reading (makes sense, doesn't it?).");
		reqOrder.add(req);

		// Absorbing Discussion Board
		req = requirementDao.postRequirement(this, "Absorbing Discussion Board", 5f);
		req.setDescription("As a User I want the Discussion Board to be absorbing, so that I never have time to do my work.");
		reqOrder.add(req);

		// Irresistable User Interface
		req = requirementDao.postRequirement(this, "Irresistable User Interface", 20f);
		req.setDescription("As a User I want the User Interface to be irresistable so that I can experience Orgasmic Joy-of-Use.");
		reqOrder.add(req);

		// Succulent Documentation
		req = requirementDao.postRequirement(this, "Succulent Documentation", null);
		req.setDescription("As a Noob I want Succulent Documentation. Yammy!");
		reqOrder.add(req);

		// Outlasting Collaboration
		req = requirementDao.postRequirement(this, "Outlasting Collaboration", null);
		req.setDescription("This is still an epic. Nothing to see, really.");
		reqOrder.add(req);

		updateRequirementsOrder(reqOrder);
	}

	public void addTestQualitys() {
		Quality qly = null;

		qly = qualityDao.postQuality(this, "Undeniable Success");
		qly = qualityDao.postQuality(this, "Orgasmic Joy-of-Use");
		qly = qualityDao.postQuality(this, "Effervescent Happiness");
		qly = qualityDao.postQuality(this, "Supersonic Communication");
		qly = qualityDao.postQuality(this, "Endless Freedom");
		qly = qualityDao.postQuality(this, "Flawless Integration");
	}

	public void addTestSprints() {
		sprintDao.createTestSprint(this);
	}

	public Comparator<Requirement> getRequirementsOrderComparator() {
		if (requirementsOrderComparator == null) requirementsOrderComparator = new Comparator<Requirement>() {

			@Override
			public int compare(Requirement a, Requirement b) {
				if (a.isInCurrentSprint() && !b.isInCurrentSprint()) return -1;
				if (b.isInCurrentSprint() && !a.isInCurrentSprint()) return 1;
				List<String> order = getRequirementsOrderIds();
				int additional = order.size();
				int ia = order.indexOf(a.getId());
				if (ia < 0) {
					ia = additional;
					additional++;
				}
				int ib = order.indexOf(b.getId());
				if (ib < 0) {
					ib = additional;
					additional++;
				}
				return ia - ib;
			}
		};
		return requirementsOrderComparator;
	}

}
