package scrum.server.project;

import ilarkesto.base.time.Date;
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

		if (variant == 0) {
			po = userDao.getTestUser("cartman");
			sm = userDao.getTestUser("duke");
		} else {
			po = userDao.getTestUser("homer");
			sm = userDao.getTestUser("cartman");
		}

		Project project = postProject(userDao.getUserByName("admin"));
		project.setLabel("Project " + variant);
		project.setBegin(Date.today().addMonths(-2));
		project.setEnd(Date.today().addMonths(5));
		project.addAdmins(userDao.getEntities());
		project.addTeamMembers(userDao.getEntities());
		project.setProductOwner(po);
		project.setScrumMaster(sm);
		project.addTestImpediments(variant);
		project.addTestSprints(variant);
		project.addTestRequirements(variant);
	}

}
