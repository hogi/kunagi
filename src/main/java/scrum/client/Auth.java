package scrum.client;

import ilarkesto.gwt.client.AComponent;
import scrum.client.admin.User;
import scrum.client.context.HomeContext;

public class Auth extends AComponent implements ServerDataReceivedListener {

	// --- dependencies ---

	private Ui ui;
	private Dao dao;
	private EventBus eventBus;
	private ProjectContext projectContext;

	public void setUi(Ui ui) {
		this.ui = ui;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	public void setProjectContext(ProjectContext projectContext) {
		this.projectContext = projectContext;
	}

	// --- ---

	private User user;

	@Override
	protected void onInitialization() {
		super.onInitialization();
		dao = Components.get().getDao();
		eventBus = Components.get().getEventBus();
	}

	public void onServerDataReceived(DataTransferObject data) {
		if (data.isUserSet()) {
			user = dao.getUser(data.getUserId());
			log.info("User logged in:", user);
			eventBus.fireLogin();
		}
	}

	public void logout() {
		if (projectContext.isProjectOpen()) projectContext.closeProject(false);
		HomeContext.destroy();
		log.info("Logging out");
		ui.getWorkspace().lock("Logging out...");
		user = null;
		eventBus.fireLogout();
		ScrumGwtApplication.get().callLogout();
		ui.getWorkspace().activatePublicView();
	}

	public boolean isUserLoggedIn() {
		return user != null;
	}

	public User getUser() {
		return user;
	}

}
