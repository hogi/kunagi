package scrum.client.calendar;

import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.common.AScrumWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class CalendarWidget extends AScrumWidget {

	private DayListWidget dayList;
	private YearSelectorListWidget yearSelector;
	private MonthSelectorListWidget monthSelector;
	private DaySelectorListWidget daySelector;

	@Override
	protected Widget onInitialization() {

		dayList = new DayListWidget();

		yearSelector = new YearSelectorListWidget();
		monthSelector = new MonthSelectorListWidget();
		daySelector = new DaySelectorListWidget();

		PagePanel left = new PagePanel();
		left.addHeader("Project Calendar", new ButtonWidget(new CreateSimpleEventAction()));
		left.addSection(dayList);

		PagePanel right = new PagePanel();
		right.addHeader("Years");
		right.addSection(yearSelector);
		right.addHeader("Months");
		right.addSection(monthSelector);
		right.addHeader("Days");
		right.addSection(daySelector);

		TableBuilder tb = new TableBuilder();
		tb.setColumnWidths("67%", "1%", "32%");
		tb.addRow(left, Gwt.createSpacer(10, 1), right);
		return tb.createTable();
	}

	public Date getSelectedDate() {
		return new Date(yearSelector.getSelectedYear(), 1, 1);
	}

	public void showDate(Date date) {
		dayList.showDate(date);
		yearSelector.setSelectedYear(date.getYear());
		monthSelector.setYear(date.getYear());
		monthSelector.setSelectedMonth(date.getMonth());
		// daySelector.sets
	}

	public void showEvent(SimpleEvent event) {
		dayList.showEvent(event);
	}

}
