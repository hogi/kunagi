package scrum.client.project;

import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import ilarkesto.gwt.client.editor.TextEditorWidget;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.FieldsWidget;
import scrum.client.sprint.Sprint;
import scrum.client.workspace.PagePanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class ProjectOverviewWidget extends AScrumWidget {

	public static final int CHART_WIDTH = 800;
	public static final int CHART_HEIGHT = 270;

	private Image sprintChart;

	@Override
	protected Widget onInitialization() {
		final Project project = getCurrentProject();

		PagePanel page = new PagePanel();
		page.addHeader("Project Properties");
		page.addSection(createProjectOverview(project));

		Sprint sprint = project.getCurrentSprint();
		if (sprint != null) {
			page.addHeader("Current Sprint");
			page.addSection(createCurrentSprintOverview(sprint));
		}

		return page;
	}

	private Widget createProjectOverview(Project project) {

		FieldsWidget fields = new FieldsWidget();
		fields.add("Label", new TextEditorWidget(project.getLabelModel()));
		fields.add("Description", new RichtextEditorWidget(project.getDescriptionModel()));

		return TableBuilder.row(20, fields, new CommentsWidget(project));
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		if (sprintChart != null) {
			Sprint sprint = getCurrentProject().getCurrentSprint();
			if (sprint != null) sprintChart.setUrl(getChartUrl(sprint));
		}
	}

	private Widget createCurrentSprintOverview(Sprint sprint) {
		String chartUrl = getChartUrl(sprint);
		sprintChart = new Image(chartUrl, 0, 0, CHART_WIDTH, CHART_HEIGHT);
		return TableBuilder.row(20, sprintChart, new CommentsWidget(sprint));
	}

	private String getChartUrl(Sprint sprint) {
		int width = Window.getClientWidth() - 280;
		width = width / 2;
		return GWT.getModuleBaseURL() + "sprintBurndownChart.png?sprintId=" + sprint.getId() + "&width=" + width
				+ "&height=" + CHART_HEIGHT;
	}

}
