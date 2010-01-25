package scrum.client.calendar;

import ilarkesto.gwt.client.Date;

import java.util.List;

import scrum.client.common.AScrumAction;

public class YearSelectorWidget extends ADateSelectorWidget {

	private int year;
	private boolean selected;
	private boolean visible;

	public YearSelectorWidget(int year, boolean selected, boolean visible) {
		this.year = year;
		this.selected = selected;
		this.visible = visible;
	}

	public YearSelectorWidget(int year) {
		this(year, false, false);
	}

	@Override
	protected AScrumAction getAction() {
		return new YearSelectedAction(year);
	}

	@Override
	protected List<SimpleEvent> getEvents() {
		return cm.getCalendar().getEventsByYear(year);
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
		return today.getYear() == year;
	}

}
