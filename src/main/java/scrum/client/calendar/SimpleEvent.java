package scrum.client.calendar;

import ilarkesto.gwt.client.Time;
import ilarkesto.gwt.client.TimePeriod;

import java.util.Map;

public class SimpleEvent extends GSimpleEvent {

	public SimpleEvent() {
		super();
		setProject(cm.getProjectContext().getProject());
	}

	public SimpleEvent(Map data) {
		super(data);
	}

	public String getTimeAsString() {
		Time time = getTime();
		return time == null ? null : time.toString(false);
	}

	public String getDurationAsString() {
		Integer duration = getDuration();
		return duration == null ? null : TimePeriod.minutes(duration).toHoursAndMinutes();
	}
}