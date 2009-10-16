package scrum.client;

import ilarkesto.gwt.client.AComponentManager;

public class ComponentManager extends GComponentManager implements LogoutListener, ProjectClosedListener,
		ProjectOpenedListener, LoginListener {

	ComponentManager() {
		super(new EventBus(), new Dao());
	}

	public void onLogout() {
		destroyAuth();
		destroyHomeContext();
		getPublicContext().activate();
	}

	public void onProjectClosed() {
		destroyProjectContext();
		destroyChat();
		destroyUsersStatus();
		destroyDndManager();
	}

	public void onProjectOpened() {
		destroyHomeContext();
	}

	public void onLogin() {
		destroyPublicContext();
	}

	@Override
	protected void initializeDao(Dao dao) {
		super.initializeDao(dao);
		dao.setUi(getUi());
	}

	public ScrumGwtApplication getApp() {
		return (ScrumGwtApplication) super.getApp();
	}

	public static ComponentManager get() {
		return (ComponentManager) AComponentManager.get();
	}

}
