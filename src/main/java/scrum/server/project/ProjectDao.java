package scrum.server.project;

import ilarkesto.base.time.Date;

import java.util.Collection;

import scrum.server.admin.User;
import scrum.server.admin.UserDao;

public class ProjectDao extends GProjectDao {

	// --- dependencies ---

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// --- ---

	public Project postProject(User admin) {
		Project project = newEntityInstance();
		project.setLabel("New Project");
		project.addAdmin(admin);
		saveEntity(project);
		return project;
	}

	// --- test data ---

	public void createTestProject(int variant) {
		User po;
		User sm;

		po = userDao.getTestUser("duke");
		sm = userDao.getTestUser("spinne");

		Collection<User> team = userDao.getEntities();
		team.remove(po);
		team.remove(sm);

		Project project = postProject(userDao.getUserByName("admin"));
		project.setLabel("Project " + variant);
		project.setBegin(Date.today().addMonths(-2));
		project.setEnd(Date.today().addMonths(5));
		project.addParticipants(userDao.getEntities());
		project.addAdmins(userDao.getEntities());
		project.addTeamMembers(team);
		project.addProductOwner(po);
		project.addScrumMaster(sm);
		project.addTestImpediments(variant);
		project.addTestSprints(variant);
		project.addTestRequirements(variant);
		project.addTestRisks(variant);
		project.addTestQualitys(variant);
		project.addTestIssues(variant);

		if (variant == 1) {
			userDao.getTestUser("duke").setCurrentProject(project);
		}
	}

}
