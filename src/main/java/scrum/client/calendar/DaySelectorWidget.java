package scrum.client.calendar;

import ilarkesto.gwt.client.Date;

import java.util.List;

import scrum.client.common.AScrumAction;

public class DaySelectorWidget extends ADateSelectorWidget {

	private Date date;
	private boolean selected;

	public DaySelectorWidget(Date date, boolean selected) {
		this.date = date;
		this.selected = selected;
	}

	public DaySelectorWidget(Date date) {
		this(date, false);
	}

	@Override
	protected AScrumAction getAction() {
		return new DaySelectedAction(date.getDay());
	}

	@Override
	protected List<SimpleEvent> getEvents() {
		return cm.getCalendar().getEventsByDate(date);
	}

	@Override
	protected boolean isSelected() {
		return selected;
	}
}
