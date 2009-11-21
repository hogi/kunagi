package scrum.client.calendar;

import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DaySelectorListWidget extends AScrumWidget {

	private Date selectedDate = Date.today();

	@Override
	protected Widget onInitialization() {
		return null;
	}

	@Override
	protected void onUpdate() {
		TableBuilder tb = new TableBuilder();
		int count = 0;

		// Header with weekdays
		for (int i = 0; i < 7; i++) {
			// 2009-11-23 is a Monday
			Date weekday = new Date(2009, 11, 23 + i);
			tb.add(new Label(weekday.getWeekdayLabel()));
		}
		tb.nextRow();

		for (Date date : Date.getDaysOverMonth(selectedDate.getYear(), selectedDate.getMonth())) {
			tb.add(new DaySelectorWidget(date, date.equals(selectedDate)));
			count++;
			if (count == 7) {
				tb.nextRow();
				count = 0;
			}
		}
		replaceContent(tb.createTable());
		super.onUpdate();
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public int getSelectedDay() {
		return this.selectedDate.getDay();
	}

}
