package scrum.client.calendar;

import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.Gwt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListSelectionManager;
import scrum.client.common.BlockListWidget;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
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
		table.setBorderWidth(1);
		int row = 0;
		Date date = dateToShow;
		Date end = dateToShow.addDays(14);
		int week = 0;
		while (date.compareTo(end) <= 0) {
			int w = date.getWeek();
			if (w != week) {
				week = w;
				table.setWidget(row, 0, createWeek(week));
				// table.getCellFormatter().getElement(row, 0).setAttribute("rowspan", "7");
			} else {
				table.setWidget(row, 0, new Label("."));
			}
			table.setWidget(row, 1, Gwt
					.createDiv("DayListWidget-date", Gwt.DTF_WEEKDAY_SHORT.format(date.toJavaDate())));
			table.setWidget(row, 2, Gwt.createDiv("DayListWidget-date", Gwt.DTF_DAY.format(date.toJavaDate())));
			table.setWidget(row, 3, createEventList(date));
			date = date.nextDay();
			row++;
		}

		wrapper.setWidget(table);
	}

	public void showEvent(SimpleEvent event) {
		showDate(event.getDate());
		update();
		selectionManager.select(event);
	}

	private Widget createWeek(int week) {
		Label l = new Label("" + week);
		l.setStyleName("DayListWidget-week");
		return l;
	}

	private Widget createEventList(Date date) {
		BlockListWidget<SimpleEvent> list = new BlockListWidget<SimpleEvent>(SimpleEventBlock.FACTORY);
		list.setSelectionManager(selectionManager);
		list.setAutoSorter(SimpleEvent.TIME_COMPARATOR);
		lists.put(date, list);
		return list;
	}

}
