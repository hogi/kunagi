package scrum.client.calendar;

import ilarkesto.gwt.client.DateAndTime;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.Time;
import scrum.client.journal.ProjectEvent;

public class PublishSimpleEventAction extends GPublishSimpleEventAction {

	public PublishSimpleEventAction(scrum.client.calendar.SimpleEvent simpleEvent) {
		super(simpleEvent);
	}

	@Override
	public String getLabel() {
		return "Publish Notification";
	}

	@Override
	public String getTooltip() {
		return "Add a notification for this event to the project journal.";
	}

	@Override
	protected void onExecute() {
		String suffix = Gwt.DTF_WEEKDAY_MONTH_DAY.format(simpleEvent.getDate().toJavaDate());
		Time time = simpleEvent.getTime();
		if (time != null)
			suffix += ", " + Gwt.DTF_HOUR_MINUTE.format(new DateAndTime(simpleEvent.getDate(), time).toJavaDate());
		String location = simpleEvent.getLocation();
		if (!Gwt.isEmpty(location)) suffix += " @ " + location;
		cm.getDao().createProjectEvent(
			new ProjectEvent(getCurrentProject(), simpleEvent.getLabel() + " scheduled to " + suffix));
	}

}