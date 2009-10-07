package scrum.client;

import ilarkesto.gwt.client.AComponents;

public class Components extends AComponents<EventBus, Dao> {

	Components() {
		super(new EventBus(), new Dao());
	}

	public static Components get() {
		return (Components) AComponents.get();
	}

}
