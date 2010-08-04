package scrum.client.calendar;

import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.common.AScrumWidget;
import scrum.client.common.UserGuideWidget;
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
		left.addSection(new UserGuideWidget(getCurrentProject().getSimpleEvents().size() < 5, getLocalizer()
				.views().calendar()));

		PagePanel right = new PagePanel();
		right.addHeader("Years");
		right.addSection(yearSelector);
		right.addHeader("Months");
		right.addSection(monthSelector);
		right.addHeader("Days");
		right.addSection(daySelector);

		yearSelector.setSelectedYear(dayList.getDate().getYear());
		yearSelector.setVisibleRange(dayList.getBegin(), dayList.getEnd());
		monthSelector.setYear(dayList.getDate().getYear());
		monthSelector.setSelectedMonth(dayList.getDate().getMonth());
		monthSelector.setVisibleRange(dayList.getBegin(), dayList.getEnd());
		daySelector.setSelectedDate(dayList.getDate());
		daySelector.setVisibleRange(dayList.getBegin(), dayList.getEnd());

		TableBuilder tb = new TableBuilder();
		tb.setColumnWidths("", "10px", "270px");
		tb.addRow(left, Gwt.createSpacer(10, 1), right);

		return tb.createTable();
	}

	public Date getSelectedDate() {
		return new Date(yearSelector.getSelectedYear(), monthSelector.getSelectedMonth(), daySelector.getSelectedDay());
	}

	public void showDate(Date date) {
		dayList.showDate(date);
		yearSelector.setSelectedYear(date.getYear());
		yearSelector.setVisibleRange(dayList.getBegin(), dayList.getEnd());
		monthSelector.setYear(date.getYear());
		monthSelector.setSelectedMonth(date.getMonth());
		monthSelector.setVisibleRange(dayList.getBegin(), dayList.getEnd());
		daySelector.setSelectedDate(date);
		daySelector.setVisibleRange(dayList.getBegin(), dayList.getEnd());
	}

	public void showEvent(SimpleEvent event) {
		dayList.showEvent(event);
	}

}
