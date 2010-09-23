package scrum;

import ilarkesto.base.Str;
import ilarkesto.base.Sys;
import ilarkesto.base.Utl;
import ilarkesto.base.time.Date;
import ilarkesto.base.time.DateAndTime;
import ilarkesto.base.time.Time;
import ilarkesto.core.logging.Log;
import ilarkesto.persistence.AEntity;
import scrum.server.ScrumWebApplication;
import scrum.server.admin.User;
import scrum.server.calendar.SimpleEvent;
import scrum.server.collaboration.Comment;
import scrum.server.collaboration.Wikipage;
import scrum.server.impediments.Impediment;
import scrum.server.issues.Issue;
import scrum.server.pr.BlogEntry;
import scrum.server.project.Project;
import scrum.server.project.Requirement;
import scrum.server.risks.Risk;
import scrum.server.sprint.Sprint;
import scrum.server.sprint.Task;

public class TestUtil {

	private static boolean initialized;
	private static ScrumWebApplication app;

	public static void initialize() {
		if (initialized) return;
		initialized = true;

		Log.setDebugEnabled(false);
		Sys.setHeadless(true);

		app = new ScrumWebApplication();
		app.setTestMode(true);
		app.start();

	}

	public static User getDuke() {
		initialize();
		User duke = app.getUserDao().getUserByName("duke");
		if (duke == null) duke = app.getUserDao().postUser("duke");
		duke.setEmail("duke@kunagi.org");
		duke.setEmailVerified(true);
		return duke;
	}

	public static User getAdmin() {
		initialize();
		User admin = app.getUserDao().getUserByName("admin");
		if (admin == null) {
			admin = app.getUserDao().postUser("admin");
			admin.setAdmin(true);
		}
		return admin;
	}

	public static void createComments(AEntity parent, int count) {
		for (int i = 0; i < count; i++) {
			createComment(parent);
		}
	}

	public static Comment createComment(AEntity parent) {
		return createComment(parent, DateAndTime.now().addHours(Utl.randomInt(-100000, -1)),
			Str.generateRandomSentence(1, 2), Str.generateRandomParagraph(), true);
	}

	public static Comment createComment(AEntity parent, DateAndTime dateAndTime, String author, String text,
			boolean published) {
		Comment comment = app.getCommentDao().newEntityInstance();
		comment.setParent(parent);
		comment.setDateAndTime(dateAndTime);
		comment.setAuthorName(author);
		comment.setText(text);
		comment.setPublished(published);
		return comment;
	}

	public static Task createTask(Requirement requirement, int number, int work) {
		return createTask(requirement, number, Str.generateRandomSentence(2, 6), work);
	}

	public static Task createTask(Requirement requirement, int number, String label, int work) {
		Task task = app.getTaskDao().newEntityInstance();
		task.setRequirement(requirement);
		task.setNumber(number);
		task.setLabel(label);
		task.setRemainingWork(work);
		return task;
	}

	public static User createUser(String name) {
		User user = app.getUserDao().newEntityInstance();
		user.setName(name);
		return user;
	}

	public static Issue createIssue(Project project, int number) {
		return createIssue(project, number, Str.generateRandomSentence(4, 8), Str.generateRandomParagraph(),
			Str.generateRandomParagraph(), true);
	}

	public static Issue createIssue(Project project, int number, String label, String description, String statement,
			boolean published) {
		Issue issue = app.getIssueDao().newEntityInstance();
		issue.setProject(project);
		issue.setNumber(number);
		issue.setLabel(label);
		issue.setDescription(description);
		issue.setStatement(statement);
		issue.setPublished(published);
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
		Wikipage wikipage = app.getWikipageDao().newEntityInstance();
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
		SimpleEvent event = app.getSimpleEventDao().newEntityInstance();
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
		Risk risk = app.getRiskDao().newEntityInstance();
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
		Impediment impediment = app.getImpedimentDao().newEntityInstance();
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
		BlogEntry entry = app.getBlogEntryDao().newEntityInstance();
		entry.setProject(project);
		entry.setNumber(number);
		entry.setTitle(title);
		entry.setText(text);
		entry.setDateAndTime(dateAndTime);
		return entry;
	}

	public static Requirement createRequirement(Project project, int number) {
		return createRequirement(project, number, Str.generateRandomSentence(4, 5) + " (#" + number + ")",
			Str.generateRandomParagraph(), Str.generateRandomParagraph());
	}

	public static Requirement createRequirement(Project project, int number, String label, String description,
			String testDescription) {
		Requirement requirement = app.getRequirementDao().newEntityInstance();
		requirement.setProject(project);
		requirement.setNumber(number);
		requirement.setLabel(label);
		requirement.setDescription(description);
		requirement.setTestDescription(testDescription);
		return requirement;
	}

	public static Sprint createSprint(Project project, Date end) {
		return createSprint(project, end.beforeDays(30), end);
	}

	public static Sprint createSprint(Project project, Date begin, Date end) {
		Sprint sprint = app.getSprintDao().newEntityInstance();
		sprint.setProject(project);
		sprint.setBegin(begin);
		sprint.setEnd(end);
		sprint.setLabel("Sprint from " + begin + " to " + end);
		sprint.setGoal("Sprint from " + begin + " to " + end);
		return sprint;
	}

	public static Project createProject() {
		return createProject(getDuke());
	}

	public static Project createProject(User admin) {
		return createProject(admin, Str.generateRandomWord(5, 10, true));
	}

	public static Project createProject(User admin, String label) {
		Project project = app.getProjectDao().postProject(admin);
		project.setLabel(label);
		project.setShortDescription(Str.generateRandomSentence(4, 4));
		project.setDescription(Str.generateRandomParagraph());
		project.setLongDescription(Str.generateRandomParagraphs(5, null, null, "\n\n"));
		return project;
	}

	public static ScrumWebApplication getApp() {
		return app;
	}

}
