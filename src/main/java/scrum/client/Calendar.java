package scrum.client;

import ilarkesto.gwt.client.Date;

import java.util.ArrayList;
import java.util.List;

import scrum.client.calendar.SimpleEvent;
import scrum.client.common.AScrumComponent;

public class Calendar extends AScrumComponent {

	public List<SimpleEvent> getEventsByDate(Date date) {
		List<SimpleEvent> ret = new ArrayList<SimpleEvent>();
		for (SimpleEvent event : getCurrentProject().getSimpleEvents()) {
			if (event.isDate(date)) ret.add(event);
		}
		return ret;
	}

	public List<SimpleEvent> getEventsByYear(int year) {
		List<SimpleEvent> ret = new ArrayList<SimpleEvent>();
		for (SimpleEvent event : getCurrentProject().getSimpleEvents()) {
			if (event.getDate().getYear() == year) ret.add(event);
		}
		return ret;
	}

	public List<SimpleEvent> getEventsByMonth(int year, int month) {
		List<SimpleEvent> ret = new ArrayList<SimpleEvent>();
		for (SimpleEvent event : getCurrentProject().getSimpleEvents()) {
			Date date = event.getDate();
			if (date.getYear() == year && date.getMonth() == month) ret.add(event);
		}
		return ret;
	}

	public void showSelectedDate() {
	// TODO
	}

}