package scrum;

import ilarkesto.base.Str;
import ilarkesto.base.time.Date;
import ilarkesto.base.time.DateAndTime;
import ilarkesto.base.time.Time;
import ilarkesto.core.logging.Log;
import ilarkesto.persistence.EntityStore;
import ilarkesto.persistence.FileEntityStore;
import ilarkesto.persistence.TransactionService;
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
import scrum.server.risks.Risk;
import scrum.server.risks.RiskDao;
import scrum.server.sprint.Sprint;
import scrum.server.sprint.SprintDao;
import scrum.server.sprint.SprintDaySnapshotDao;

public class TestUtil {

	private static boolean initialized;
	private static EntityStore entityStore;
	private static TransactionService transactionService;
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

	private static void initialize() {
		if (initialized) return;
		initialized = true;

		Log.setDebugEnabled(true);

		entityStore = new FileEntityStore();

		transactionService = new TransactionService();
		transactionService.setEntityStore(entityStore);

		wikipageDao = new WikipageDao();
		wikipageDao.setTransactionService(transactionService);

		simpleEventDao = new SimpleEventDao();
		simpleEventDao.setTransactionService(transactionService);

		riskDao = new RiskDao();
		riskDao.setTransactionService(transactionService);

		impedimentDao = new ImpedimentDao();
		impedimentDao.setTransactionService(transactionService);

		requirementDao = new RequirementDao();
		requirementDao.setTransactionService(transactionService);

		sprintDaySnapshotDao = new SprintDaySnapshotDao();
		sprintDaySnapshotDao.setTransactionService(transactionService);

		sprintDao = new SprintDao();
		sprintDao.setTransactionService(transactionService);
		Sprint.setSprintDaySnapshotDao(sprintDaySnapshotDao);
		Sprint.setRequirementDao(requirementDao);

		blogEntryDao = new BlogEntryDao();
		blogEntryDao.setTransactionService(transactionService);

		issueDao = new IssueDao();
		issueDao.setTransactionService(transactionService);

		projectDao = new ProjectDao();
		projectDao.setTransactionService(transactionService);
		Project.setWikipageDao(wikipageDao);
		Project.setSprintDaySnapshotDao(sprintDaySnapshotDao);
		Project.setRequirementDao(requirementDao);
		Project.setSprintDao(sprintDao);
		Project.setImpedimentDao(impedimentDao);
		Project.setRiskDao(riskDao);
		Project.setSimpleEventDao(simpleEventDao);
		Project.setBlogEntryDao(blogEntryDao);
		Project.setIssueDao(issueDao);
	}

	public static Issue createIssue(Project project, int number) {
		return createIssue(project, number, Str.generateRandomSentence(4, 8), Str.generateRandomParagraph(),
			Str.generateRandomParagraph());
	}

	public static Issue createIssue(Project project, int number, String label, String description, String statement) {
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
		return project;
	}

}
