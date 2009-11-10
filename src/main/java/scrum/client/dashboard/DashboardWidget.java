package scrum.client.dashboard;

import ilarkesto.gwt.client.TableBuilder;
import scrum.client.common.AScrumWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class DashboardWidget extends AScrumWidget {

	@Override
	protected Widget onInitialization() {

		FlowPanel left = new FlowPanel();
		left.add(new SprintBurndownWidget());
		left.add(new UsersWorkWidget());

		FlowPanel right = new FlowPanel();
		right.add(new ProjectInfoWidget());

		PagePanel page = new PagePanel();
		page.addHeader("Dashboard");
		page.addSection(TableBuilder.row(20, left, right));
		return page;
	}

}
