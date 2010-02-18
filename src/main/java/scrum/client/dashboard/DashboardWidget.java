package scrum.client.dashboard;

import ilarkesto.gwt.client.HyperlinkWidget;
import ilarkesto.gwt.client.SwitchingNavigatorWidget;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.ProjectContext;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class DashboardWidget extends AScrumWidget {

	@Override
	protected Widget onInitialization() {
		ProjectContext pc = cm.getProjectContext();
		SwitchingNavigatorWidget nav = pc.getSidebar().getNavigator();

		PagePanel sprintBurndown = new PagePanel();
		sprintBurndown.addHeader("Sprint Burndown", new HyperlinkWidget(nav.createSwitchAction(pc.getSprintBacklog())));
		sprintBurndown.addSection(new SprintBurndownWidget());

		PagePanel tasks = new PagePanel();
		tasks.addHeader("Tasks", new HyperlinkWidget(nav.createSwitchAction(pc.getWhiteboard())));
		tasks.addSection(TableBuilder.row(10, new TeamTasksWidget(), new UpcomingTasksWidget()));

		PagePanel issues = new PagePanel();
		issues.addHeader("Current Bugs", new HyperlinkWidget(nav.createSwitchAction(pc.getIssueList())));
		issues.addSection(new AcceptedIssuesWidget());

		PagePanel sprintComments = PagePanel.createSimple("Sprint Comments", new CommentsWidget(getCurrentSprint()));

		PagePanel impediments = new PagePanel();
		impediments.addHeader("Open Impediments", new HyperlinkWidget(nav.createSwitchAction(pc.getImpedimentList())));
		impediments.addSection(new OpenImpedimentsWidget());

		PagePanel risks = new PagePanel();
		risks.addHeader("High Priority Risks", new HyperlinkWidget(nav.createSwitchAction(pc.getRiskList())));
		risks.addSection(new HighestRisksWidget());

		PagePanel events = new PagePanel();
		events.addHeader("Latest Events", new HyperlinkWidget(nav.createSwitchAction(pc.getProjectEventList())));
		events.addSection(new LatestEventsWidget());

		PagePanel projectComments = PagePanel.createSimple("Project Comments", new CommentsWidget(getCurrentProject()));

		Widget left = TableBuilder.column(5, sprintBurndown, tasks, issues, sprintComments);
		Widget right = TableBuilder.column(5, impediments, risks, events, projectComments);

		return TableBuilder.row(5, left, right);
	}

}
