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
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class DayListWidget extends AScrumWidget {

	private int visibleDays = 21;
	private SimplePanel wrapper;
	private BlockListSelectionManager selectionManager;
	private Map<Date, BlockListWidget<SimpleEvent>> lists;

	private Date date;
	private Date begin;
	private Date end;

	public DayListWidget() {
		date = Date.today();
		updateBeginAndEnd();
	}

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

	private void updateBeginAndEnd() {
		begin = date;
		end = begin.addDays(visibleDays);
	}

	public void showDate(Date dateToShow) {
		this.date = dateToShow;
		updateBeginAndEnd();

		lists.clear();
		selectionManager.clear();

		FlexTable table = new FlexTable();
		table.setWidth("100%");
		table.setCellPadding(2);
		table.getColumnFormatter().setWidth(0, "25px");
		table.getColumnFormatter().setWidth(1, "40px");
		table.getColumnFormatter().setWidth(2, "30px");
		table.getColumnFormatter().setWidth(3, "40px");

		// table.setBorderWidth(1);
		int row = 0;
		Date d = begin;
		int month = 0;
		int week = 0;
		while (d.compareTo(end) <= 0) {
			int w = d.getWeek();
			if (w != week) {
				week = w;
				Widget weekWidget = Gwt.createDiv("DayListWidget-week", String.valueOf(week));
				table.setWidget(row, 0, weekWidget);
			}
			int m = d.getMonth();
			if (m != month) {
				month = m;
				Widget monthWidget = Gwt.createDiv("DayListWidget-month", Gwt.getMonthShort(month));
				table.setWidget(row, 1, monthWidget);
			}
			table.setWidget(row, 2, Gwt.createDiv("DayListWidget-date", Gwt.DTF_DAY.format(d.toJavaDate())));
			table.setWidget(row, 3, Gwt.createDiv("DayListWidget-date", Gwt.DTF_WEEKDAY_SHORT.format(d.toJavaDate())));
			table.setWidget(row, 4, createDayContent(d));

			formatRow(table, row);
			d = d.nextDay();
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

	private Widget createDayContent(Date date) {
		FlowPanel panel = new FlowPanel();
		for (String info : cm.getCalendar().getInfos(date)) {
			panel.add(Gwt.createDiv("DayListWidget-date-info", info));
		}
		panel.add(createEventList(date));
		return panel;
	}

	private Widget createEventList(Date date) {
		BlockListWidget<SimpleEvent> list = new BlockListWidget<SimpleEvent>(SimpleEventBlock.FACTORY,
				new ChangeSimpleEventDateDropAction(date));
		list.setSelectionManager(selectionManager);
		list.setAutoSorter(SimpleEvent.TIME_COMPARATOR);
		lists.put(date, list);
		return list;
	}

	public Date getDate() {
		return date;
	}

	public Date getBegin() {
		return begin;
	}

	public Date getEnd() {
		return end;
	}

}
