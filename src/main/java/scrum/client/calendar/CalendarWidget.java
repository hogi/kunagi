package scrum.client.calendar;

import ilarkesto.gwt.client.TableBuilder;
import scrum.client.common.AScrumWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class CalendarWidget extends AScrumWidget {

	@Override
	protected Widget onInitialization() {

		PagePanel left = new PagePanel();
		left.addHeader("Project Calendar");

		PagePanel right = new PagePanel();
		right.addHeader("Years");
		right.addSection("[2008] [2009] [2010] ...");
		right.addHeader("Months");
		right.addSection("[Jan] [Feb] [Mar] [Apr] [Mai] [Jun] [Jul] [Aug] [Sep] [Oct] [Nov] [Dec]");
		right.addHeader("Days");
		right.addSection("[01] [02] [03] [04] ...");

		return TableBuilder.row(10, left, right);
	}
}
