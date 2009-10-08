package scrum.client.project;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.Gwt;
import scrum.client.ScrumGwtApplication;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.FieldsWidget;
import scrum.client.common.GroupWidget;
import scrum.client.sprint.Sprint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class ProjectOverviewWidget extends AWidget {

	public static int CHART_WIDTH = 800;
	public static int CHART_HEIGHT = 270;

	private Image sprintChart;

	@Override
	protected Widget onInitialization() {
		final Project project = ScrumGwtApplication.get().getProject();

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

		// String chartUrl = GWT.getModuleBaseURL() + "/projectBurndownChart.png?projectId=" + project.getId()
		// + "&width="
		// + CHART_WIDTH + "&height=" + CHART_HEIGHT;
		// Image chart = new Image(chartUrl, 0, 0, CHART_WIDTH, CHART_HEIGHT);

		FlowPanel projectPropertiesPanel = new FlowPanel();
		projectPropertiesPanel.add(fields);
		// projectPropertiesPanel.add(chart);

		FlowPanel panel = new FlowPanel();
		panel.add(new GroupWidget("Project Properties", projectPropertiesPanel));
		Sprint sprint = project.getCurrentSprint();
		if (sprint != null) {
			panel.add(createCurrentSprintOverview(sprint));
		}

		return Gwt.createFlowPanel(panel, new CommentsWidget(project));
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		if (sprintChart != null) {
			Sprint sprint = ScrumGwtApplication.get().getProject().getCurrentSprint();
			if (sprint != null) sprintChart.setUrl(getChartUrl(sprint));
		}
	}

	private Widget createCurrentSprintOverview(Sprint sprint) {
		String chartUrl = getChartUrl(sprint);
		sprintChart = new Image(chartUrl, 0, 0, CHART_WIDTH, CHART_HEIGHT);
		return new GroupWidget("Current Sprint", sprintChart);
	}

	private String getChartUrl(Sprint sprint) {
		return GWT.getModuleBaseURL() + "/sprintBurndownChart.png?sprintId=" + sprint.getId() + "&width=" + CHART_WIDTH
				+ "&height=" + CHART_HEIGHT;
	}

}
