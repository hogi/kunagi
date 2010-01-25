package scrum.client;

import ilarkesto.gwt.client.Date;

import java.util.ArrayList;
import java.util.List;

import scrum.client.calendar.SimpleEvent;
import scrum.client.common.AScrumComponent;
import scrum.client.sprint.Sprint;

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

	public void showYear(int year) {
		Date prev = cm.getProjectContext().getCalendar().getSelectedDate();
		Date date = new Date(year, prev.getMonth(), prev.getDay());
		cm.getProjectContext().getCalendar().showDate(date);
	}

	public void showMonth(int month) {
		Date prev = cm.getProjectContext().getCalendar().getSelectedDate();
		Date date = new Date(prev.getYear(), month, prev.getDay());
		cm.getProjectContext().getCalendar().showDate(date);
	}

	public void showDay(int day) {
		Date prev = cm.getProjectContext().getCalendar().getSelectedDate();
		Date date = new Date(prev.getYear(), prev.getMonth(), day);
		cm.getProjectContext().getCalendar().showDate(date);
	}

	public void showDate(Date date) {
		cm.getProjectContext().getCalendar().showDate(date);
	}

	public List<String> getInfos(Date date) {
		List<String> ret = new ArrayList<String>();
		for (Sprint sprint : getCurrentProject().getSprints()) {
			if (sprint.isEnd(date)) ret.add(0, "End of Sprint: " + sprint.getLabel());
			if (sprint.isBegin(date)) ret.add("Begin of Sprint: " + sprint.getLabel());
		}
		return ret;
	}

}