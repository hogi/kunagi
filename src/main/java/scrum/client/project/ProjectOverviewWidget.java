package scrum.client.project;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.FieldsWidget;
import scrum.client.common.GroupWidget;
import scrum.client.sprint.Sprint;
import scrum.client.workspace.WorkareaWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class ProjectOverviewWidget extends AWidget {

	public static int CHART_WIDTH = 800;
	public static int CHART_HEIGHT = 300;

	private FieldsWidget fields;

	@Override
	protected Widget onInitialization() {
		fields = new FieldsWidget();

		fields.add("Label", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(ScrumGwtApplication.get().getProject().getLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(ScrumGwtApplication.get().getProject().getLabel());
			}

			@Override
			protected void onEditorSubmit() {
				ScrumGwtApplication.get().getProject().setLabel(getEditorText());
			}
		});

		fields.add("Description", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(ScrumGwtApplication.get().getProject().getDescription());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(ScrumGwtApplication.get().getProject().getDescription());
			}

			@Override
			protected void onEditorSubmit() {
				ScrumGwtApplication.get().getProject().setDescription(getEditorText());
			}
		});

		String chartUrl = GWT.getModuleBaseURL() + "/projectBurndownChart.png?projectId="
				+ ScrumGwtApplication.get().getProject().getId() + "&width=" + CHART_WIDTH + "&height=" + CHART_HEIGHT;
		Image chart = new Image(chartUrl, 0, 0, CHART_WIDTH, CHART_HEIGHT);

		FlowPanel projectPropertiesPanel = new FlowPanel();
		projectPropertiesPanel.add(fields);
		projectPropertiesPanel.add(chart);

		FlowPanel panel = new FlowPanel();
		panel.add(new GroupWidget("Project Properties", projectPropertiesPanel));
		Sprint sprint = ScrumGwtApplication.get().getProject().getCurrentSprint();
		if (sprint != null) {
			panel.add(createCurrentSprintOverview(sprint));
		}

		return panel;
	}

	@Override
	protected void onUpdate() {
		fields.update();
	}

	private Widget createCurrentSprintOverview(Sprint sprint) {
		String chartUrl = GWT.getModuleBaseURL() + "/sprintBurndownChart.png?sprintId=" + sprint.getId() + "&width="
				+ CHART_WIDTH + "&height=" + CHART_HEIGHT;
		return new GroupWidget("Current Sprint", new Image(chartUrl, 0, 0, CHART_WIDTH, CHART_HEIGHT));
	}

	public static ProjectOverviewWidget get() {
		return WorkareaWidget.get().getProjectOverview();
	}
}
