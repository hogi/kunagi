package scrum;

import ilarkesto.base.time.Date;
import ilarkesto.logging.Logger;
import ilarkesto.persistence.EntityStore;
import ilarkesto.persistence.FileEntityStore;
import ilarkesto.persistence.TransactionService;
import scrum.server.calendar.SimpleEvent;
import scrum.server.calendar.SimpleEventDao;
import scrum.server.impediments.Impediment;
import scrum.server.impediments.ImpedimentDao;
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

	private static void initialize() {
		if (initialized) return;
		initialized = true;

		Logger.setDebugEnabled(true);

		entityStore = new FileEntityStore();

		transactionService = new TransactionService();
		transactionService.setEntityStore(entityStore);

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

		projectDao = new ProjectDao();
		projectDao.setTransactionService(transactionService);
		Project.setSprintDaySnapshotDao(sprintDaySnapshotDao);
		Project.setRequirementDao(requirementDao);
		Project.setSprintDao(sprintDao);
		Project.setImpedimentDao(impedimentDao);
		Project.setRiskDao(riskDao);
		Project.setSimpleEventDao(simpleEventDao);

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

	public static Requirement createRequirement(Project project, int number) {
		return createRequirement(project, number, "Requirement #" + number, "Some description for requirement number "
				+ number);
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
