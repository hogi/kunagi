package scrum.server.project;

import ilarkesto.base.time.Date;
import ilarkesto.base.time.DateAndTime;
import ilarkesto.concurrent.ACollectionTask;
import ilarkesto.core.logging.Log;
import ilarkesto.persistence.TransactionService;

import java.util.Collection;

public class DeleteOldProjectsTask extends ACollectionTask<Project> {

	private static Log log = Log.get(DeleteOldProjectsTask.class);

	// --- dependencies ---

	private TransactionService transactionService;
	private ProjectDao projectDao;

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	// --- ---

	@Override
	protected Collection<Project> prepare() throws InterruptedException {
		return projectDao.getEntities();
	}

	@Override
	protected void perform(Project project) throws InterruptedException {
		DateAndTime opened = project.getLastOpenedDateAndTime();
		int timeToLiveInDays = project.containsParticipantWithVerifiedEmail() ? 14 : 3;
		Date deadline = Date.beforeDays(timeToLiveInDays);
		if (opened == null || opened.getDate().isAfter(deadline)) return;
		log.info("Deleting old project:", project);
		projectDao.deleteEntity(project);
	}

	@Override
	protected void cleanup() throws InterruptedException {
		transactionService.commit();
		super.cleanup();
	}

}
