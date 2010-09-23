package scrum.server.project;

import ilarkesto.concurrent.ACollectionTask;

import java.util.Collection;

public class HomepageUpdaterTask extends ACollectionTask<Project> {

	// --- dependencies ---

	private ProjectDao projectDao;

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	// --- ---

	@Override
	protected Collection<Project> prepare() throws InterruptedException {
		return projectDao.getProjectsByAutoUpdateHomepage(true);
	}

	@Override
	protected void perform(Project project) throws InterruptedException {
		project.updateHomepage();
	}

}
