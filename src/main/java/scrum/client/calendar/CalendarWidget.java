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

	@Override
	protected Widget onInitialization() {

		dayList = new DayListWidget();

		PagePanel left = new PagePanel();
		left.addHeader("Project Calendar", new ButtonWidget(new CreateSimpleEventAction()));
		left.addSection(dayList);

		PagePanel right = new PagePanel();
		right.addHeader("Years");
		right.addSection(new YearSelectorListWidget());
		right.addHeader("Months");
		right.addSection(new MonthSelectorListWidget());
		right.addHeader("Days");
		right.addSection(new DaySelectorListWidget());

		TableBuilder tb = new TableBuilder();
		tb.setColumnWidths("67%", "1%", "32%");
		tb.addRow(left, Gwt.createSpacer(10, 1), right);
		return tb.createTable();
	}

	public Date getSelectedDate() {
		// TODO hogi
		return Date.today();
	}

	public void showDate(Date date) {
		dayList.showDate(date);
	}

	public void showEvent(SimpleEvent event) {
		dayList.showEvent(event);
	}
}
