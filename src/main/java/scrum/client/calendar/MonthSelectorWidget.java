package scrum.client.calendar;

import ilarkesto.gwt.client.Date;

import java.util.List;

import scrum.client.common.AScrumAction;

public class MonthSelectorWidget extends ADateSelectorWidget {

	private int month;
	private int year;
	private boolean selected;
	private boolean visible;

	public MonthSelectorWidget(int year, int month, boolean selected, boolean visible) {
		this.month = month;
		this.year = year;
		this.selected = selected;
		this.visible = visible;
	}

	public MonthSelectorWidget(int year, int month) {
		this(year, month, false, false);
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

	@Override
	protected boolean isVisibleInList() {
		return visible;
	}

	@Override
	protected boolean isToday() {
		Date today = Date.today();
		return today.getMonth() == month && today.getYear() == year;
	}
}
