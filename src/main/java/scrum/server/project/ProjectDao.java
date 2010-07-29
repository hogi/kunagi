package scrum.server.project;

import ilarkesto.base.time.Date;
import ilarkesto.base.time.DateAndTime;
import ilarkesto.core.logging.Log;

import java.util.Collection;

import scrum.server.admin.User;
import scrum.server.admin.UserDao;

public class ProjectDao extends GProjectDao {

	private static final Log LOG = Log.get(ProjectDao.class);

	// --- dependencies ---

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// --- ---

	@Override
	public Project newEntityInstance() {
		Project project = super.newEntityInstance();
		project.setLastOpenedDateAndTime(DateAndTime.now());
		return project;
	}

	public Project postProject(User admin) {
		Project project = newEntityInstance();
		project.setLabel("New Project");
		project.addAdmin(admin);
		saveEntity(project);
		return project;
	}

	public void scanFiles() {
		LOG.info("Scanning file repositories of all projects");
		for (Project project : getEntities()) {
			project.scanFiles();
		}
	}

	// --- test data ---

	public Project postExampleProject(User owner, User po, User sm) {
		Collection<User> team = userDao.getEntities();
		// team.remove(po);
		// team.remove(sm);

		Project project = postProject(owner);
		project.setLabel("Example Project # " + DateAndTime.now());
		project.setBegin(Date.today().addMonths(-2));
		project.setEnd(Date.today().addMonths(5));

		project.addAdmin(owner);
		project.addProductOwner(po);
		project.addProductOwner(owner);
		project.addScrumMaster(sm);
		project.addScrumMaster(owner);
		project.addTeamMember(owner);
		project.addParticipants(project.getAdmins());
		project.addParticipants(project.getTeamMembers());
		project.addParticipants(project.getProductOwners());
		project.addParticipants(project.getScrumMasters());

		project.addTestSprints();
		project.addTestRequirements();
		project.addTestImpediments();
		project.addTestRisks();
		project.addTestQualitys();
		project.addTestIssues();
		project.addTestEvents();
		project.addTestSimpleEvents();

		project.getCurrentSprint().burndownTasksRandomly(Date.beforeDays(15), Date.today().addDays(-1));

		po.setCurrentProject(project);

		return project;
	}

}
