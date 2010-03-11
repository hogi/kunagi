package scrum.client;

import ilarkesto.gwt.client.AComponentManager;

public class ComponentManager extends GComponentManager implements ProjectClosedListener {

	ComponentManager() {
		super(new EventBus(), new Dao());
	}

	public void onProjectClosed() {
		destroyProjectContext();
	}

	public ScrumGwtApplication getApp() {
		return (ScrumGwtApplication) super.getApp();
	}

	public static ComponentManager get() {
		return (ComponentManager) AComponentManager.get();
	}

}
