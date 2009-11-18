package scrum.client.calendar;

import scrum.client.common.AScrumAction;

public class DaySelectedAction extends AScrumAction {

	private int day;

	public DaySelectedAction(int day) {
		this.day = day;
	}

	@Override
	public String getLabel() {
		return "" + day;
	}

	@Override
	protected void onExecute() {
		cm.getCalendar().showSelectedDate();
	}

}
