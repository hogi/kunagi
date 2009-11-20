package scrum.client.dashboard;

import ilarkesto.gwt.client.TableBuilder;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class DashboardWidget extends AScrumWidget {

	@Override
	protected Widget onInitialization() {
		PagePanel sprintBurndown = PagePanel.createSimple("Sprint Burndown", new SprintBurndownWidget());
		PagePanel tasks = PagePanel.createSimple("Tasks", TableBuilder.row(10, new TeamTasksWidget(),
			new UpcomingTasksWidget()));
		PagePanel sprintComments = PagePanel.createSimple("Sprint Comments", new CommentsWidget(getCurrentSprint()));
		PagePanel impediments = PagePanel.createSimple("Open Impediments", new OpenImpedimentsWidget());
		PagePanel risks = PagePanel.createSimple("High Priority Risks", new HighestRisksWidget());
		PagePanel events = PagePanel.createSimple("Latest Events", new LatestEventsWidget());
		PagePanel projectComments = PagePanel.createSimple("Project Comments", new CommentsWidget(getCurrentProject()));

		Widget left = TableBuilder.column(5, sprintBurndown, tasks, sprintComments);
		Widget right = TableBuilder.column(5, impediments, risks, events, projectComments);

		return TableBuilder.row(5, left, right);
	}
}
