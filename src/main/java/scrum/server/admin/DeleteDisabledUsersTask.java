package scrum.server.admin;

import ilarkesto.concurrent.ACollectionTask;
import ilarkesto.core.logging.Log;
import ilarkesto.persistence.TransactionService;

import java.util.Collection;

public class DeleteDisabledUsersTask extends ACollectionTask<User> {

	private static Log log = Log.get(DeleteDisabledUsersTask.class);

	// --- dependencies ---

	private UserDao userDao;
	private TransactionService transactionService;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	// --- ---

	@Override
	protected Collection<User> prepare() throws InterruptedException {
		return userDao.getUsersByDisabled(true);
	}

	@Override
	protected void perform(User user) throws InterruptedException {
		if (!user.getProjects().isEmpty()) return;
		log.info("Deleting disabled user with no projects:", user);
		userDao.deleteEntity(user);
	}

	@Override
	protected void cleanup() throws InterruptedException {
		transactionService.commit();
		super.cleanup();
	}

}
