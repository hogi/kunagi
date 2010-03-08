package scrum.client.communication;

import ilarkesto.core.scope.ComponentReflector;
import ilarkesto.core.scope.Scope;
import scrum.client.ComponentManager;

public class PingerReflector implements ComponentReflector<Pinger> {

	public void injectComponents(Pinger component, Scope scope) {
		component.app = ComponentManager.get().getApp();
	}

	public void callInitalizationMethods(Pinger component) {}

	public void outjectComponents(Pinger component, Scope scope) {}

}
