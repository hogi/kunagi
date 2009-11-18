package scrum.client.calendar;

import java.util.List;

import scrum.client.common.AScrumAction;

public class YearSelectorWidget extends ADateSelectorWidget {

	private int year;
	private boolean selected;

	public YearSelectorWidget(int year, boolean selected) {
		this.year = year;
		this.selected = selected;
	}

	public YearSelectorWidget(int year) {
		this(year, false);
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
}
