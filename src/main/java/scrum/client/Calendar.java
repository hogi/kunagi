package scrum.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import scrum.client.calendar.SimpleEvent;
import scrum.client.common.AScrumComponent;

public class Calendar extends AScrumComponent {

	public List<SimpleEvent> getEvents(Date date) {
		List<SimpleEvent> ret = new ArrayList<SimpleEvent>();
		return ret;
	}

}