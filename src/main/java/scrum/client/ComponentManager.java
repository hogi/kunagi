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
	}

	public void onProjectOpened() {
		destroyHomeContext();
	}

	public void onLogin() {
		destroyPublicContext();
	}

	public ScrumGwtApplication getApp() {
		return (ScrumGwtApplication) super.getApp();
	}

	public static ComponentManager get() {
		return (ComponentManager) AComponentManager.get();
	}

}
