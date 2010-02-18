package scrum.client;

import ilarkesto.gwt.client.AComponentManager;
import ilarkesto.scope.ComponentFactory;
import ilarkesto.scope.DependencyInjector;
import ilarkesto.scope.Scope;

public class ComponentManager extends GComponentManager implements ComponentFactory, DependencyInjector,
		LogoutListener, ProjectClosedListener, ProjectOpenedListener, LoginListener {

	ComponentManager() {
		super(new EventBus(), new Dao());
		getSystemMessageManager();
	}

	public Object createComponent(String name) {
		return null;
	}

	public void injectDependencies(Object component, Scope scope) {}

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
		destroyUndo();
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
