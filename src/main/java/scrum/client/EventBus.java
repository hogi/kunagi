package scrum.client;

import ilarkesto.gwt.client.AEventBus;

public class EventBus extends GEventBus {

	EventBus() {}

	public static EventBus get() {
		return (EventBus) AEventBus.get();
	}

}
