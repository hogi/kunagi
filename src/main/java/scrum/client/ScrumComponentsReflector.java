package scrum.client;

import scrum.client.communication.PingerReflector;

public class ScrumComponentsReflector extends GScrumComponentsReflector {

	@Override
	public PingerReflector createPingerReflector() {
		return new PingerReflector();
	}

}
