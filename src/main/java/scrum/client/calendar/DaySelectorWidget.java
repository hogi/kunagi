package scrum.client.calendar;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.Date;

import java.util.List;

import scrum.client.common.AScrumAction;

public class DaySelectorWidget extends ADateSelectorWidget {

	private Date date;
	private boolean selected;
	private boolean visible;

	public DaySelectorWidget(Date date, boolean selected, boolean visible) {
		this.date = date;
		this.selected = selected;
		this.visible = visible;
	}

	public DaySelectorWidget(Date date) {
		this(date, false, false);
	}

	@Override
	protected AScrumAction getAction() {
		return new DaySelectedAction(date);
	}

	@Override
	protected List<SimpleEvent> getEvents() {
		return Scope.get().getComponent(Calendar.class).getEventsByDate(date);
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
		return Date.today().equals(date);
	}
}
