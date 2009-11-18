package scrum.client.calendar;

import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.Gwt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListSelectionManager;
import scrum.client.common.BlockListWidget;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class DayListWidget extends AScrumWidget {

	private SimplePanel wrapper;
	private BlockListSelectionManager selectionManager;
	private Map<Date, BlockListWidget<SimpleEvent>> lists;

	@Override
	protected Widget onInitialization() {
		selectionManager = new BlockListSelectionManager();
		lists = new HashMap<Date, BlockListWidget<SimpleEvent>>();
		wrapper = new SimplePanel();
		showDate(Date.today());
		return wrapper;
	}

	@Override
	protected void onUpdate() {
		for (Map.Entry<Date, BlockListWidget<SimpleEvent>> entry : lists.entrySet()) {
			List<SimpleEvent> events = cm.getCalendar().getEventsByDate(entry.getKey());
			entry.getValue().setObjects(events);
		}
	}

	public void showDate(Date dateToShow) {
		lists.clear();
		selectionManager.clear();

		FlexTable table = new FlexTable();
		table.setWidth("100%");
		table.setCellPadding(2);

		// table.setBorderWidth(1);
		int row = 0;
		Date date = dateToShow;
		Date end = dateToShow.addDays(14);
		int month = 0;
		int week = 0;
		while (date.compareTo(end) <= 0) {
			int w = date.getWeek();
			if (w != week) {
				week = w;
				table.setWidget(row, 0, Gwt.createDiv("DayListWidget-week", String.valueOf(week)));
			}
			int m = date.getMonth();
			if (m != month) {
				month = m;
				table.setWidget(row, 1, Gwt.createDiv("DayListWidget-month", Gwt.getMonthShort(month)));
			}
			table.setWidget(row, 2, Gwt.createDiv("DayListWidget-date", Gwt.DTF_DAY.format(date.toJavaDate())));
			table.setWidget(row, 3, Gwt
					.createDiv("DayListWidget-date", Gwt.DTF_WEEKDAY_SHORT.format(date.toJavaDate())));
			table.setWidget(row, 4, createEventList(date));

			formatRow(table, row);
			date = date.nextDay();
			row++;
		}

		wrapper.setWidget(table);
	}

	private void formatRow(FlexTable table, int row) {
		String border = "1px solid #EEE";
		for (int i = 0; i <= 4; i++) {
			Element element = table.getCellFormatter().getElement(row, i);
			Style style = element.getStyle();
			style.setProperty("borderBottom", border);
			if (row == 0) style.setProperty("borderTop", border);
			if (i < 3 || i == 4) style.setProperty("borderLeft", border);
			if (i == 4) style.setProperty("borderRight", border);
		}
	}

	public void showEvent(SimpleEvent event) {
		showDate(event.getDate());
		update();
		selectionManager.select(event);
	}

	private Widget createEventList(Date date) {
		BlockListWidget<SimpleEvent> list = new BlockListWidget<SimpleEvent>(SimpleEventBlock.FACTORY);
		list.setSelectionManager(selectionManager);
		list.setAutoSorter(SimpleEvent.TIME_COMPARATOR);
		lists.put(date, list);
		return list;
	}

}
