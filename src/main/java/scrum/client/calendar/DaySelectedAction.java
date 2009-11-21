package scrum.client.calendar;

import ilarkesto.gwt.client.Date;
import scrum.client.common.AScrumAction;

public class DaySelectedAction extends AScrumAction {

	private Date date;

	public DaySelectedAction(Date date) {
		this.date = date;
	}

	@Override
	public String getLabel() {
		return "" + date.getDay();
	}

	@Override
	protected void onExecute() {
		cm.getCalendar().showDate(date);
	}

}
