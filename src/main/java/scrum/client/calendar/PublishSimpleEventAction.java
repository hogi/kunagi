package scrum.client.calendar;

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
		String sTime = Gwt.DTF_WEEKDAY_MONTH_DAY.format(simpleEvent.getDate().toJavaDate());
		Time time = simpleEvent.getTime();
		if (time != null) sTime += ", " + Gwt.DTF_HOUR_MINUTE;
		cm.getDao().createProjectEvent(new ProjectEvent(simpleEvent.getLabel() + " scheduled to " + sTime));
	}

}