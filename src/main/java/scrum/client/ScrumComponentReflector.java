package scrum.client;

import ilarkesto.core.scope.ComponentReflector;
import ilarkesto.core.scope.Scope;
import scrum.client.communication.Pinger;
import scrum.client.communication.PingerReflector;

public class ScrumComponentReflector implements ComponentReflector<Object> {

	private PingerReflector pingerReflector = new PingerReflector();

	public void injectComponents(Object component, Scope scope) {
		if (component instanceof Pinger) pingerReflector.injectComponents((Pinger) component, scope);
	}

	public void callInitalizationMethods(Object component) {
		if (component instanceof Pinger) pingerReflector.callInitalizationMethods((Pinger) component);
	}

	public void outjectComponents(Object component, Scope scope) {
		if (component instanceof Pinger) pingerReflector.outjectComponents((Pinger) component, scope);

	}

}
