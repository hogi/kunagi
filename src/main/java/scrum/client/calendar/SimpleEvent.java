package scrum.client.calendar;

import java.util.Map;

public class SimpleEvent extends GSimpleEvent {

	public SimpleEvent() {
		super();
		setProject(cm.getProjectContext().getProject());
	}

	public SimpleEvent(Map data) {
		super(data);
	}

}