package scrum.client.communication;

import ilarkesto.core.scope.Scope;
import scrum.client.ComponentManager;

public class PingerReflector extends GPingerReflector {

	public void injectComponents(Pinger component, Scope scope) {
		super.injectComponents(component, scope);
		component.app = ComponentManager.get().getApp();
	}

	public void callInitializationMethods(Pinger component) {
		super.callInitializationMethods(component);
	}

	public void outjectComponents(Pinger component, Scope scope) {
		super.outjectComponents(component, scope);
	}

}
