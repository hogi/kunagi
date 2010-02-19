package scrum.client.communication;

import scrum.client.ComponentManager;
import ilarkesto.scope.ComponentIncubator;
import ilarkesto.scope.Scope;

public class PingerIncubator implements ComponentIncubator<Pinger> {

	public String getComponentName() {
		return "pinger";
	}

	public Pinger createComponent() {
		return new Pinger();
	}

	public void initializeComponent(Pinger component, Scope scope) {
		component.app = ComponentManager.get().getApp();
		ComponentManager.get().getEventBus().addListener(component); // TODO refactor: run events on scopes
	}

	public boolean isAutoCreate() {
		return false;
	}

}
