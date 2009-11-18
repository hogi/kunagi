package scrum.client.calendar;

import ilarkesto.gwt.client.TableBuilder;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Widget;

public class YearSelectorListWidget extends AScrumWidget {

	@Override
	protected Widget onInitialization() {

		return TableBuilder.row(10, new YearSelectorWidget(2008), new YearSelectorWidget(2009, true),
			new YearSelectorWidget(2010), new YearSelectorWidget(2011), new YearSelectorWidget(2012));

	}

}
