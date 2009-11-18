package scrum.client.calendar;

import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.TableBuilder;

import java.util.ArrayList;
import java.util.List;

import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Widget;

public class DaySelectorListWidget extends AScrumWidget {

	private int selectedDay;
	private int year;
	private int month;

	protected List<DaySelectorWidget> getDayWidgets(int year, int month, int selectedDay) {

		List<Date> days = Date.getDaysInMonth(2009, 11);

		List<DaySelectorWidget> widgets = new ArrayList<DaySelectorWidget>();

		for (Date d : days) {
			if (d.getDay() == selectedDay) {
				widgets.add(new DaySelectorWidget(d, true));
			} else {
				widgets.add(new DaySelectorWidget(d));
			}
		}

		return widgets;

	}

	public void setMonth(int year, int month) {
		this.year = year;
		this.month = month;
	}

	public void setSelectedDay(int selectedDay) {
		this.selectedDay = selectedDay;
	}

	public int getSelectedDay() {
		return this.selectedDay;
	}

	@Override
	protected void onUpdate() {
		replaceContent(TableBuilder.row(10, getDayWidgets(year, month, selectedDay).toArray(new Widget[0])));
		super.onUpdate();
	}

	@Override
	protected Widget onInitialization() {
		return null;
	}

}
