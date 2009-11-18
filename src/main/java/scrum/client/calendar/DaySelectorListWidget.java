package scrum.client.calendar;

import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.TableBuilder;

import java.util.ArrayList;
import java.util.List;

import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Widget;

public class DaySelectorListWidget extends AScrumWidget {

	@Override
	protected Widget onInitialization() {

		TableBuilder tb = new TableBuilder();
		tb.setCellPadding(2);

		List<Date> days = Date.getDaysInMonth(2009, 11);

		List<DaySelectorWidget> widgets = new ArrayList<DaySelectorWidget>();

		for (Date d : days) {
			widgets.add(new DaySelectorWidget(d));
		}

		return TableBuilder.row(10, widgets.toArray(new Widget[0]));

	}
}
