package scrum.client.project;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
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

	public static int CHART_WIDTH = 800;
	public static int CHART_HEIGHT = 270;

	private Image sprintChart;

	@Override
	protected Widget onInitialization() {
		final Project project = getCurrentProject();

		FieldsWidget fields = new FieldsWidget();

		fields.add("Label", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(project.getLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(project.getLabel());
			}

			@Override
			protected void onEditorSubmit() {
				project.setLabel(getEditorText());
			}
		});

		fields.add("Description", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(project.getDescription());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(project.getDescription());
			}

			@Override
			protected void onEditorSubmit() {
				project.setDescription(getEditorText());
			}
		});

		PagePanel page = new PagePanel();
		page.addHeader("Project Properties");
		page.addSection(fields);

		// String chartUrl = GWT.getModuleBaseURL() + "/projectBurndownChart.png?projectId=" + project.getId()
		// + "&width="
		// + CHART_WIDTH + "&height=" + CHART_HEIGHT;
		// Image chart = new Image(chartUrl, 0, 0, CHART_WIDTH, CHART_HEIGHT);

		Sprint sprint = project.getCurrentSprint();
		if (sprint != null) {
			page.addHeader("Current Sprint");
			page.addSection(createCurrentSprintOverview(sprint));
		}

		page.addSection(new CommentsWidget(project));

		return page;
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
		return sprintChart;
	}

	private String getChartUrl(Sprint sprint) {
		int width = Window.getClientWidth() - 260;
		return GWT.getModuleBaseURL() + "/sprintBurndownChart.png?sprintId=" + sprint.getId() + "&width=" + width
				+ "&height=" + CHART_HEIGHT;
	}

}
