package scrum.client.calendar;

import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.TableBuilder;

import java.util.ArrayList;
import java.util.List;

import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Widget;

public class MonthSelectorListWidget extends AScrumWidget {

	private int selectedMonth = Date.today().getMonth();
	private int year = Date.today().getYear();

	private static List<MonthSelectorWidget> getMonthWidgets(int year, int selectedMonth) {
		List<MonthSelectorWidget> widgets = new ArrayList<MonthSelectorWidget>();
		for (int i = 1; i < 13; i++) {
			if (i == selectedMonth) {
				widgets.add(new MonthSelectorWidget(year, i, true));
			} else {
				widgets.add(new MonthSelectorWidget(year, i));
			}
		}
		return widgets;
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

	@Override
	protected void onUpdate() {
		replaceContent(TableBuilder.row(10, getMonthWidgets(year, selectedMonth).toArray(new Widget[0])));
		super.onUpdate();
	}

	@Override
	protected Widget onInitialization() {
		return null;
	}
}
