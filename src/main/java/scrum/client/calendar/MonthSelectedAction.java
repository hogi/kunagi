package scrum.client.calendar;

import ilarkesto.core.scope.Scope;
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
		Scope.get().getComponent(Calendar.class).showMonth(month);
	}

}
