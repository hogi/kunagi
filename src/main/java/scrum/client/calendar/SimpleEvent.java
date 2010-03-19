package scrum.client.calendar;

import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.HyperlinkWidget;
import ilarkesto.gwt.client.Time;
import ilarkesto.gwt.client.TimePeriod;

import java.util.Comparator;
import java.util.Map;

import scrum.client.collaboration.ForumSupport;
import scrum.client.common.ReferenceSupport;
import scrum.client.common.ShowEntityAction;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.Widget;

public class SimpleEvent extends GSimpleEvent implements ForumSupport, ReferenceSupport {

	public static final String REFERENCE_PREFIX = "evt";

	public SimpleEvent(Project project, Date date) {
		super();
		setDate(date);
		setProject(project);
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
		sb.append(getReference()).append(" ");
		sb.append(getDate().toString());
		Time time = getTime();
		if (time != null) {
			sb.append(" ").append(time.toString(false));
		}
		String location = getLocation();
		if (location != null) {
			sb.append(" @ ").append(location);
		}
		sb.append(" ").append(getLabel());
		return sb.toString();
	}

	public Widget createForumItemWidget() {
		return new HyperlinkWidget(new ShowEntityAction(this, getLabel()));
	}

	public String getReference() {
		return REFERENCE_PREFIX + getNumber();
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