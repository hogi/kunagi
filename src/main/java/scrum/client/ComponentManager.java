package scrum.client;

import ilarkesto.gwt.client.AComponentManager;

public class ComponentManager extends GComponentManager {

	ComponentManager() {
		super(new EventBus(), new Dao());
	}

	@Override
	public ScrumGwtApplication getApp() {
		return (ScrumGwtApplication) super.getApp();
	}

	public static ComponentManager get() {
		return (ComponentManager) AComponentManager.get();
	}

}
