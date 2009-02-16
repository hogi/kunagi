package scrum.server;

import ilarkesto.base.Url;
import ilarkesto.base.Utl;
import ilarkesto.base.time.Date;
import ilarkesto.concurrent.TaskManager;
import ilarkesto.io.IO;
import ilarkesto.logging.Logger;
import ilarkesto.webapp.AWebApplication;
import ilarkesto.webapp.AWebSession;
import scrum.server.admin.User;
import scrum.server.admin.UserDao;
import scrum.server.impediments.Impediment;
import scrum.server.project.Project;
import scrum.server.project.Requirement;
import scrum.server.sprint.BurndownChart;
import scrum.server.sprint.Sprint;
import scrum.server.sprint.SprintDaySnapshot;
import scrum.server.sprint.Task;

public class ScrumWebApplication extends GScrumWebApplication {

	private static final Logger LOG = Logger.get(ScrumWebApplication.class);

	private BurndownChart burndownChart;

	// --- components ---

	public BurndownChart getBurndownChart() {
		if (burndownChart == null) {
			burndownChart = new BurndownChart();
			autowire(burndownChart);
		}
		return burndownChart;
	}

	// --- ---

	@Override
	public void ensureIntegrity() {
		// delete entities
		IO.delete(getApplicationDataDir() + "/entities");

		super.ensureIntegrity();
	}

	@Override
	protected void onStartWebApplication() {
		if (getUserDao().getEntities().isEmpty()) {
			LOG.warn("No users. Creating initial user: admin:geheim");
			getUserDao().postUser("admin", "geheim");
		}

		// test data
		if (getProjectDao().getEntities().isEmpty()) createTestData();
	}

	private void createTestData() {
		LOG.warn("Creating test data");

		User user1 = getUserDao().postUser("homer", "geheim");
		User user2 = getUserDao().postUser("cartman", "geheim");
		User user3 = getUserDao().postUser("duke", "geheim");

		Project project1 = getProjectDao().postProject("Prepared Project", getUserDao().getUserByName("admin"));
		project1.setBegin(Date.today().addMonths(-2));
		project1.setEnd(Date.today().addMonths(5));
		project1.addAdmins(getUserDao().getEntities());
		project1.addTeamMembers(getUserDao().getEntities());
		project1.setProductOwner(user1);
		project1.setScrumMaster(user2);
		project1
				.setDescription("Test project with homer, cartman, duke and admin. Some impediments, some backlog items. Also a current sprint.");

		Impediment impediment1 = getImpedimentDao().newEntityInstance();
		impediment1.setProject(project1);
		impediment1.setLabel("Impediment 1");
		getImpedimentDao().saveEntity(impediment1);

		Impediment impediment2 = getImpedimentDao().newEntityInstance();
		impediment2.setProject(project1);
		impediment2.setLabel("Impediment 2");
		getImpedimentDao().saveEntity(impediment2);

		Impediment impediment3 = getImpedimentDao().newEntityInstance();
		impediment3.setProject(project1);
		impediment3.setLabel("Impediment 3");
		getImpedimentDao().saveEntity(impediment3);

		Requirement requirement1 = getRequirementDao().newEntityInstance();
		requirement1.setProject(project1);
		requirement1.setLabel("Requirement 1");
		requirement1.setEstimatedWork(10);
		getRequirementDao().saveEntity(requirement1);

		Requirement requirement2 = getRequirementDao().newEntityInstance();
		requirement2.setProject(project1);
		requirement2.setLabel("Requirement 2");
		requirement2.setEstimatedWork(5);
		getRequirementDao().saveEntity(requirement2);

		Task task1 = getTaskDao().newEntityInstance();
		task1.setRequirement(requirement2);
		task1.setLabel("Task 1");
		task1.setRemainingWork(3);
		task1.setBurnedWork(5);
		getTaskDao().saveEntity(task1);

		Task task2 = getTaskDao().newEntityInstance();
		task2.setRequirement(requirement2);
		task2.setLabel("Task 2");
		task2.setRemainingWork(1);
		getTaskDao().saveEntity(task2);

		Requirement requirement3 = getRequirementDao().newEntityInstance();
		requirement3.setProject(project1);
		requirement3.setLabel("Requirement 3");
		getRequirementDao().saveEntity(requirement3);

		Sprint sprint1 = getSprintDao().newEntityInstance();
		sprint1.setProject(project1);
		sprint1.setLabel("Sprint 1");
		sprint1.setBegin(Date.today().addDays(-15));
		sprint1.setEnd(Date.today().addDays(5));
		getSprintDao().saveEntity(sprint1);
		requirement2.setSprint(sprint1);
		int remainingWork = 150;
		int burnedWork = 0;
		for (int i = 15; i > 0; i--) {
			SprintDaySnapshot snapshot = sprint1.getDaySnapshot(Date.today().addDays(-i));
			int burned = Utl.randomInt(1, 3) * 5;
			burnedWork += burned;
			remainingWork -= burned;
			if (Utl.randomInt(1, 5) == 1) remainingWork += burned + 5;
			snapshot.setBurnedWork(burnedWork);
			snapshot.setRemainingWork(remainingWork);
		}

		project1.setCurrentSprint(sprint1);
		project1.createNextSprint();

		// ---

		Project project2 = getProjectDao().postProject("Empty Project", getUserDao().getUserByName("admin"));
		project2.setBegin(Date.today().addDays(-5));
		project2.setEnd(Date.today().addMonths(2));
		project2.addAdmins(getUserDao().getEntities());
		project2.addTeamMembers(getUserDao().getEntities());
		project2.setProductOwner(user2);
		project2.setScrumMaster(user3);
		project2.setDescription("Minimal test project.");

		Sprint sprint2 = getSprintDao().newEntityInstance();
		sprint2.setProject(project2);
		sprint2.setLabel("Sprint 1");
		sprint2.setBegin(Date.today().addDays(-1));
		sprint2.setEnd(Date.today().addDays(30));
		getSprintDao().saveEntity(sprint2);

		project2.setCurrentSprint(sprint2);
		project2.createNextSprint();

		getTransactionService().commit();
	}

	@Override
	protected void scheduleTasks(TaskManager tm) {}

	@Override
	protected void onShutdownWebApplication() {}

	@Override
	public Url getHomeUrl() {
		return new Url("index.html");
	}

	private UserDao userDao;

	public UserDao getUserDao() {
		if (userDao == null) {
			userDao = new UserDao();
			autowire(userDao);
		}
		return userDao;
	}

	@Override
	protected AWebSession createWebSession() {
		return autowire(new WebSession(context));
	}

	public static ScrumWebApplication get() {
		return (ScrumWebApplication) AWebApplication.get();
	}

}
