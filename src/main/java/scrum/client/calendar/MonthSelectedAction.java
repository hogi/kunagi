package scrum.client.calendar;

import ilarkesto.gwt.client.Gwt;
import scrum.client.common.AScrumAction;

public class MonthSelectedAction extends AScrumAction {

	private int month;

	public MonthSelectedAction(int month) {
		this.month = month;
	}

	@Override
	public String getLabel() {
		return Gwt.getMonthShort(month);
	}

	@Override
	protected void onExecute() {
		cm.getCalendar().showMonth(month);
	}

}
