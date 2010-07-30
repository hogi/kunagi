package scrum;

import ilarkesto.base.Str;
import ilarkesto.base.time.Date;
import ilarkesto.base.time.DateAndTime;
import ilarkesto.base.time.Time;
import ilarkesto.core.logging.Log;
import ilarkesto.persistence.DaoService;
import ilarkesto.persistence.EntityStore;
import ilarkesto.persistence.FileEntityStore;
import ilarkesto.persistence.TransactionService;
import scrum.server.admin.User;
import scrum.server.admin.UserDao;
import scrum.server.calendar.SimpleEvent;
import scrum.server.calendar.SimpleEventDao;
import scrum.server.collaboration.Wikipage;
import scrum.server.collaboration.WikipageDao;
import scrum.server.impediments.Impediment;
import scrum.server.impediments.ImpedimentDao;
import scrum.server.issues.Issue;
import scrum.server.issues.IssueDao;
import scrum.server.pr.BlogEntry;
import scrum.server.pr.BlogEntryDao;
import scrum.server.project.Project;
import scrum.server.project.ProjectDao;
import scrum.server.project.Requirement;
import scrum.server.project.RequirementDao;
import scrum.server.release.ReleaseDao;
import scrum.server.risks.Risk;
import scrum.server.risks.RiskDao;
import scrum.server.sprint.Sprint;
import scrum.server.sprint.SprintDao;
import scrum.server.sprint.SprintDaySnapshot;
import scrum.server.sprint.SprintDaySnapshotDao;
import scrum.server.sprint.Task;
import scrum.server.sprint.TaskDao;

public class TestUtil {

	private static boolean initialized;
	private static EntityStore entityStore;
	private static TransactionService transactionService;
	private static DaoService daoService;
	private static SprintDaySnapshotDao sprintDaySnapshotDao;
	private static RequirementDao requirementDao;
	private static ProjectDao projectDao;
	private static SprintDao sprintDao;
	private static ImpedimentDao impedimentDao;
	private static RiskDao riskDao;
	private static SimpleEventDao simpleEventDao;
	private static WikipageDao wikipageDao;
	private static BlogEntryDao blogEntryDao;
	private static IssueDao issueDao;
	private static UserDao userDao;
	private static TaskDao taskDao;
	private static ReleaseDao releaseDao;

	private static void initialize() {
		if (initialized) return;
		initialized = true;

		Log.setDebugEnabled(true);

		entityStore = new FileEntityStore();

		transactionService = new TransactionService();
		transactionService.setEntityStore(entityStore);

		daoService = new DaoService();

		userDao = new UserDao();
		userDao.setTransactionService(transactionService);
		Sprint.setUserDao(userDao);

		releaseDao = new ReleaseDao();
		releaseDao.setTransactionService(transactionService);
		Project.setReleaseDao(releaseDao);

		taskDao = new TaskDao();
		taskDao.setTransactionService(transactionService);
		Sprint.setTaskDao(taskDao);
		Project.setTaskDao(taskDao);

		wikipageDao = new WikipageDao();
		wikipageDao.setTransactionService(transactionService);
		Project.setWikipageDao(wikipageDao);

		simpleEventDao = new SimpleEventDao();
		simpleEventDao.setTransactionService(transactionService);
		Project.setSimpleEventDao(simpleEventDao);

		riskDao = new RiskDao();
		riskDao.setTransactionService(transactionService);
		Project.setRiskDao(riskDao);

		impedimentDao = new ImpedimentDao();
		impedimentDao.setTransactionService(transactionService);
		Project.setImpedimentDao(impedimentDao);

		requirementDao = new RequirementDao();
		requirementDao.setTransactionService(transactionService);
		Sprint.setRequirementDao(requirementDao);
		Project.setRequirementDao(requirementDao);

		sprintDaySnapshotDao = new SprintDaySnapshotDao();
		sprintDaySnapshotDao.setTransactionService(transactionService);
		sprintDaySnapshotDao.setDaoService(daoService);
		Sprint.setSprintDaySnapshotDao(sprintDaySnapshotDao);
		Project.setSprintDaySnapshotDao(sprintDaySnapshotDao);

		sprintDao = new SprintDao();
		sprintDao.setTransactionService(transactionService);
		SprintDaySnapshot.setSprintDao(sprintDao);
		Project.setSprintDao(sprintDao);

		blogEntryDao = new BlogEntryDao();
		blogEntryDao.setTransactionService(transactionService);
		Project.setBlogEntryDao(blogEntryDao);

		issueDao = new IssueDao();
		issueDao.setTransactionService(transactionService);
		Project.setIssueDao(issueDao);

		projectDao = new ProjectDao();
		projectDao.setTransactionService(transactionService);
	}

	public static Task createTask(Requirement requirement, int number, int work) {
		return createTask(requirement, number, Str.generateRandomSentence(2, 6), work);
	}

	public static Task createTask(Requirement requirement, int number, String label, int work) {
		initialize();
		Task task = taskDao.newEntityInstance();
		task.setRequirement(requirement);
		task.setNumber(number);
		task.setLabel(label);
		task.setRemainingWork(work);
		return task;
	}

	public static User createUser(String name) {
		initialize();
		User user = userDao.newEntityInstance();
		user.setName(name);
		return user;
	}

	public static Issue createIssue(Project project, int number) {
		return createIssue(project, number, Str.generateRandomSentence(4, 8), Str.generateRandomParagraph(),
			Str.generateRandomParagraph());
	}

	public static Issue createIssue(Project project, int number, String label, String description, String statement) {
		initialize();
		Issue issue = issueDao.newEntityInstance();
		issue.setProject(project);
		issue.setNumber(number);
		issue.setLabel(label);
		issue.setDescription(description);
		issue.setStatement(statement);
		return issue;
	}

	public static Wikipage createWikipage(Project project, String name) {
		String text = "== " + name + " ==";
		text += "\n\n" + Str.generateRandomParagraph();
		text += "\n\n" + "* " + name + "\n* " + name;
		text += "\n\n" + Str.generateRandomParagraph();
		return createWikipage(project, name, text);
	}

	public static Wikipage createWikipage(Project project, String name, String text) {
		initialize();
		Wikipage wikipage = wikipageDao.newEntityInstance();
		wikipage.setProject(project);
		wikipage.setName(name);
		wikipage.setText(text);
		return wikipage;
	}

	public static SimpleEvent createSimpleEvent(Project project, int number) {
		return createSimpleEvent(project, Date.inDays(number), "Event #" + number, "Location #" + number,
			"Note for Event #" + number);
	}

	public static SimpleEvent createSimpleEvent(Project project, Date date, String label, String location, String note) {
		initialize();
		SimpleEvent event = simpleEventDao.newEntityInstance();
		event.setProject(project);
		event.setDate(date);
		event.setLabel(label);
		event.setLocation(location);
		event.setNote(note);
		return event;
	}

	public static Risk createRisk(Project project, int number) {
		return createRisk(project, number, "Risk #" + number, "Risk #" + number + " description");
	}

	public static Risk createRisk(Project project, int number, String label, String description) {
		initialize();
		Risk risk = riskDao.newEntityInstance();
		risk.setProject(project);
		risk.setNumber(number);
		risk.setLabel(label);
		risk.setDescription(description);
		risk.setImpact(number);
		risk.setProbability(number);
		return risk;
	}

	public static Impediment createImpediment(Project project, int number) {
		return createImpediment(project, Date.beforeDays(number), number, "Impediment #" + number, "Impediment #"
				+ number + " description.");
	}

	public static Impediment createImpediment(Project project, Date date, int number, String label, String description) {
		initialize();
		Impediment impediment = impedimentDao.newEntityInstance();
		impediment.setProject(project);
		impediment.setDate(date);
		impediment.setNumber(number);
		impediment.setLabel(label);
		impediment.setDescription(description);
		return impediment;
	}

	public static BlogEntry createBlogEntry(Project project, int number) {
		DateAndTime date = new DateAndTime(Date.beforeDays(number * 5), Time.now());
		return createBlogEntry(project, number, Str.generateRandomSentence(4, 6), Str.generateRandomParagraph(), date);
	}

	public static BlogEntry createBlogEntry(Project project, int number, String title, String text,
			DateAndTime dateAndTime) {
		initialize();
		BlogEntry entry = blogEntryDao.newEntityInstance();
		entry.setProject(project);
		entry.setNumber(number);
		entry.setTitle(title);
		entry.setText(text);
		entry.setDateAndTime(dateAndTime);
		return entry;
	}

	public static Requirement createRequirement(Project project, int number) {
		return createRequirement(project, number, Str.generateRandomSentence(4, 5) + " (#" + number + ")",
			Str.generateRandomParagraph());
	}

	public static Requirement createRequirement(Project project, int number, String label, String description) {
		initialize();
		Requirement requirement = requirementDao.newEntityInstance();
		requirement.setProject(project);
		requirement.setNumber(number);
		requirement.setLabel(label);
		requirement.setDescription(description);
		return requirement;
	}

	public static Sprint createSprint(Project project, Date end) {
		return createSprint(project, end.beforeDays(30), end);
	}

	public static Sprint createSprint(Project project, Date begin, Date end) {
		initialize();
		Sprint sprint = sprintDao.newEntityInstance();
		sprint.setProject(project);
		sprint.setBegin(begin);
		sprint.setEnd(end);
		sprint.setLabel("Sprint from " + begin + " to " + end);
		sprint.setGoal("Sprint from " + begin + " to " + end);
		return sprint;
	}

	public static Project createProject() {
		return createProject("Some Project");
	}

	public static Project createProject(String label) {
		initialize();
		Project project = projectDao.newEntityInstance();
		project.setLabel(label);
		project.setShortDescription(Str.generateRandomSentence(4, 4));
		project.setDescription(Str.generateRandomParagraph());
		project.setLongDescription(Str.generateRandomParagraphs(5, null, null, "\n\n"));
		return project;
	}

}
