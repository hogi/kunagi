package scrum.client.calendar;

import ilarkesto.gwt.client.TableBuilder;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Widget;

public class MonthSelectorListWidget extends AScrumWidget {

	@Override
	protected Widget onInitialization() {

		return TableBuilder.row(10, new MonthSelectorWidget(1, 2009), new MonthSelectorWidget(2, 2009),
			new MonthSelectorWidget(3, 2009), new MonthSelectorWidget(4, 2009), new MonthSelectorWidget(5, 2009),
			new MonthSelectorWidget(6, 2009), new MonthSelectorWidget(7, 2009), new MonthSelectorWidget(8, 2009),
			new MonthSelectorWidget(9, 2009), new MonthSelectorWidget(10, 2009), new MonthSelectorWidget(11, 2009),
			new MonthSelectorWidget(12, 2009, true));

	}
}
