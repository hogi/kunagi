package scrum;

import ilarkesto.logging.Logger;
import ilarkesto.persistence.EntityStore;
import ilarkesto.persistence.FileEntityStore;
import ilarkesto.persistence.TransactionService;
import scrum.server.project.Project;
import scrum.server.project.ProjectDao;
import scrum.server.project.Requirement;
import scrum.server.project.RequirementDao;
import scrum.server.sprint.Sprint;
import scrum.server.sprint.SprintDaySnapshotDao;

public class TestUtil {

	private static boolean initialized;
	private static EntityStore entityStore;
	private static TransactionService transactionService;
	private static SprintDaySnapshotDao sprintDaySnapshotDao;
	private static RequirementDao requirementDao;
	private static ProjectDao projectDao;

	private static void initialize() {
		if (initialized) return;
		initialized = true;

		Logger.setDebugEnabled(true);

		entityStore = new FileEntityStore();

		transactionService = new TransactionService();
		transactionService.setEntityStore(entityStore);

		requirementDao = new RequirementDao();
		requirementDao.setTransactionService(transactionService);

		sprintDaySnapshotDao = new SprintDaySnapshotDao();
		sprintDaySnapshotDao.setTransactionService(transactionService);

		projectDao = new ProjectDao();
		projectDao.setTransactionService(transactionService);
		Project.setSprintDaySnapshotDao(sprintDaySnapshotDao);
		Project.setRequirementDao(requirementDao);

		Sprint.setSprintDaySnapshotDao(sprintDaySnapshotDao);
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
