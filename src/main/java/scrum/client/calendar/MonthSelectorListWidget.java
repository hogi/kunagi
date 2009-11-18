package scrum.client.calendar;

import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Widget;

public class MonthSelectorListWidget extends AScrumWidget {

	private int selectedMonth = Date.today().getMonth();
	private int year = Date.today().getYear();

	@Override
	protected Widget onInitialization() {
		return null;
	}

	@Override
	protected void onUpdate() {
		TableBuilder tb = new TableBuilder();
		for (int month = 1; month <= 12; month++) {
			tb.add(new MonthSelectorWidget(year, month, month == selectedMonth));
			if (month == 6) tb.nextRow();
		}
		replaceContent(tb.createTable());
		super.onUpdate();
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setSelectedMonth(int selectedMonth) {
		this.selectedMonth = selectedMonth;
	}

	public int getSelectedMonth() {
		return selectedMonth;
	}

}
