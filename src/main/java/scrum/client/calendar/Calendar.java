package scrum.client.calendar;

import ilarkesto.gwt.client.Date;

import java.util.ArrayList;
import java.util.List;

import scrum.client.sprint.Sprint;

public class Calendar extends GCalendar {

	public List<SimpleEvent> getEventsByDate(Date date) {
		List<SimpleEvent> ret = new ArrayList<SimpleEvent>();
		for (SimpleEvent event : project.getSimpleEvents()) {
			if (event.isDate(date)) ret.add(event);
		}
		return ret;
	}

	public List<SimpleEvent> getEventsByYear(int year) {
		List<SimpleEvent> ret = new ArrayList<SimpleEvent>();
		for (SimpleEvent event : project.getSimpleEvents()) {
			if (event.getDate().getYear() == year) ret.add(event);
		}
		return ret;
	}

	public List<SimpleEvent> getEventsByMonth(int year, int month) {
		List<SimpleEvent> ret = new ArrayList<SimpleEvent>();
		for (SimpleEvent event : project.getSimpleEvents()) {
			Date date = event.getDate();
			if (date.getYear() == year && date.getMonth() == month) ret.add(event);
		}
		return ret;
	}

	public void showYear(int year) {
		Date prev = projectWorkspaceWidgets.getCalendar().getSelectedDate();
		Date date = new Date(year, prev.getMonth(), prev.getDay());
		projectWorkspaceWidgets.getCalendar().showDate(date);
	}

	public void showMonth(int month) {
		Date prev = projectWorkspaceWidgets.getCalendar().getSelectedDate();
		Date date = new Date(prev.getYear(), month, prev.getDay());
		projectWorkspaceWidgets.getCalendar().showDate(date);
	}

	public void showDay(int day) {
		Date prev = projectWorkspaceWidgets.getCalendar().getSelectedDate();
		Date date = new Date(prev.getYear(), prev.getMonth(), day);
		projectWorkspaceWidgets.getCalendar().showDate(date);
	}

	public void showDate(Date date) {
		projectWorkspaceWidgets.getCalendar().showDate(date);
	}

	public List<String> getInfos(Date date) {
		List<String> ret = new ArrayList<String>();
		for (Sprint sprint : project.getSprints()) {
			if (sprint.isEnd(date)) ret.add(0, "End of Sprint: " + sprint.getLabel());
			if (sprint.isBegin(date)) ret.add("Begin of Sprint: " + sprint.getLabel());
		}
		return ret;
	}
}
