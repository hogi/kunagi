package scrum.client.dashboard;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.HyperlinkWidget;
import ilarkesto.gwt.client.SwitchingNavigatorWidget;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.common.AScrumWidget;
import scrum.client.workspace.PagePanel;
import scrum.client.workspace.ProjectWorkspaceWidgets;

import com.google.gwt.user.client.ui.Widget;

public class DashboardWidget extends AScrumWidget {

	@Override
	protected Widget onInitialization() {
		ProjectWorkspaceWidgets widgets = Scope.get().getComponent(ProjectWorkspaceWidgets.class);

		SwitchingNavigatorWidget nav = widgets.getSidebar().getNavigator();

		PagePanel sprintBurndown = new PagePanel();
		sprintBurndown.addHeader("Sprint Burndown",
			new HyperlinkWidget(nav.createSwitchAction(widgets.getSprintBacklog())));
		sprintBurndown.addSection(new SprintBurndownWidget());

		PagePanel teamsTasks = new PagePanel();
		teamsTasks.addHeader("Teams work", new HyperlinkWidget(nav.createSwitchAction(widgets.getWhiteboard())),
			new HyperlinkWidget(nav.createSwitchAction(widgets.getIssueList())));
		teamsTasks.addSection(TableBuilder.row(10, new TeamTasksWidget(), new UpcomingTeamTasksWidget()));

		PagePanel posTasks = new PagePanel();
		posTasks.addHeader("Product Owners work",
			new HyperlinkWidget(nav.createSwitchAction(widgets.getProductBacklog())),
			new HyperlinkWidget(nav.createSwitchAction(widgets.getSprintBacklog())),
			new HyperlinkWidget(nav.createSwitchAction(widgets.getIssueList())));
		posTasks.addSection(new UpcomingPoTasksWidget());

		PagePanel impediments = new PagePanel();
		impediments.addHeader("Open Impediments",
			new HyperlinkWidget(nav.createSwitchAction(widgets.getImpedimentList())));
		impediments.addSection(new OpenImpedimentsWidget());

		PagePanel risks = new PagePanel();
		risks.addHeader("High Priority Risks", new HyperlinkWidget(nav.createSwitchAction(widgets.getRiskList())));
		risks.addSection(new HighestRisksWidget());

		PagePanel events = new PagePanel();
		events.addHeader("Latest Events", new HyperlinkWidget(nav.createSwitchAction(widgets.getProjectEventList())));
		events.addSection(new LatestEventsWidget());

		Widget left = TableBuilder.column(5, sprintBurndown, teamsTasks, posTasks);
		Widget right = TableBuilder.column(5, impediments, risks, events);

		return TableBuilder.row(5, left, right);
	}

}
