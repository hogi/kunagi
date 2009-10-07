package scrum.client;

import ilarkesto.gwt.client.AEventBus;

public class EventBus extends GScrumEventBus {

	public static EventBus get() {
		return (EventBus) AEventBus.get();
	}

}
