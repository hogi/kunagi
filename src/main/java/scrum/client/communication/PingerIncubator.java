package scrum.client.communication;

import ilarkesto.core.scope.ComponentIncubator;
import ilarkesto.core.scope.Scope;
import scrum.client.ComponentManager;

public class PingerIncubator implements ComponentIncubator<Pinger> {

	public String getComponentName() {
		return "pinger";
	}

	public Pinger createComponent() {
		return new Pinger();
	}

	public void initializeComponent(Pinger component, Scope scope) {
		component.app = ComponentManager.get().getApp();
	}

	public boolean isAutoCreate() {
		return true;
	}

}
