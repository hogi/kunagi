package scrum.client.dashboard;

import ilarkesto.gwt.client.TableBuilder;
import scrum.client.common.AScrumWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class DashboardWidget extends AScrumWidget {

	@Override
	protected Widget onInitialization() {

		PagePanel left = new PagePanel();
		left.addHeader("Sprint Burndown");
		left.addSection(new SprintBurndownWidget());
		left.addHeader("Team Member Tasks");
		left.addSection(new UsersWorkWidget());

		PagePanel right = new PagePanel();
		right.addHeader("Open Impediments");
		right.addSection(new OpenImpedimentsWidget());
		right.addHeader("Hight Priority Risks");
		right.addSection(new HighestRisksWidget());
		right.addHeader("Latest Events");
		right.addSection(new LatestEventsWidget());

		return TableBuilder.row(7, left, right);
	}
}
