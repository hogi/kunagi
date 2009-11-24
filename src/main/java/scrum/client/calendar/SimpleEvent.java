package scrum.client.calendar;

import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.Time;
import ilarkesto.gwt.client.TimePeriod;

import java.util.Comparator;
import java.util.Map;

public class SimpleEvent extends GSimpleEvent {

	public SimpleEvent() {
		super();
		setDate(Date.today());
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getDate().toString());
		Time time = getTime();
		if (time != null) {
			sb.append(" ").append(time.toString(false));
		}
		String location = getLocation();
		if (location != null) {
			sb.append(" @ ").append(location);
		}
		sb.append(getLabel());
		return sb.toString();
	}

	public static Comparator<SimpleEvent> TIME_COMPARATOR = new Comparator<SimpleEvent>() {

		public int compare(SimpleEvent a, SimpleEvent b) {
			Time at = a.getTime();
			Time bt = b.getTime();
			if (at == null && bt == null) return 0;
			if (at == null) return -1;
			if (bt == null) return 1;
			return at.compareTo(bt);
		}
	};
}