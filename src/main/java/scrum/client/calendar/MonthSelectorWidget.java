package scrum.client.calendar;

import java.util.List;

import scrum.client.common.AScrumAction;

public class MonthSelectorWidget extends ADateSelectorWidget {

	private int month;
	private int year;
	private boolean selected;

	public MonthSelectorWidget(int year, int month, boolean selected) {
		this.month = month;
		this.year = year;
		this.selected = selected;
	}

	public MonthSelectorWidget(int year, int month) {
		this(year, month, false);
	}

	@Override
	protected AScrumAction getAction() {
		return new MonthSelectedAction(month);
	}

	@Override
	protected List<SimpleEvent> getEvents() {
		return cm.getCalendar().getEventsByMonth(year, month);
	}

	@Override
	protected boolean isSelected() {
		return selected;
	}
}
