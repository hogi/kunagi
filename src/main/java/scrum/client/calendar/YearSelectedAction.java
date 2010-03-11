package scrum.client.calendar;

import ilarkesto.core.scope.Scope;
import scrum.client.common.AScrumAction;

public class YearSelectedAction extends AScrumAction {

	private int year;

	public YearSelectedAction(int year) {
		this.year = year;
	}

	@Override
	public String getLabel() {
		return "" + year;
	}

	@Override
	protected void onExecute() {
		Scope.get().getComponent(Calendar.class).showYear(year);
	}

}
