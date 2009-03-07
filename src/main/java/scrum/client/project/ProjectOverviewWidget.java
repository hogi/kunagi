package scrum.client.project;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.admin.User;
import scrum.client.common.FieldsWidget;
import scrum.client.common.GroupWidget;
import scrum.client.common.ScrumUtil;
import scrum.client.sprint.Sprint;
import scrum.client.workspace.WorkareaWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ProjectOverviewWidget extends AWidget {

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

		User productOwner = ScrumGwtApplication.get().getProject().getProductOwner();
		if (productOwner != null) {
			fields.add("Product Owner", new Label(productOwner.getName()));
		}

		User scrumMaster = ScrumGwtApplication.get().getProject().getScrumMaster();
		if (scrumMaster != null) {
			fields.add("Scrum Master", new Label(scrumMaster.getName()));
		}

		String team = ScrumUtil.toCommataSeperatedString(ScrumGwtApplication.get().getProject().getTeamMembers());
		if (team.length() > 0) {
			fields.add("Team", new Label(team));
		}

		String admins = ScrumUtil.toCommataSeperatedString(ScrumGwtApplication.get().getProject().getAdmins());
		if (admins.length() > 0) {
			fields.add("Project Admins", new Label(admins));
		}

		int chartWidth = 500;
		int chartHeight = 300;
		String chartUrl = GWT.getModuleBaseURL() + "/projectBurndownChart.png?projectId="
				+ ScrumGwtApplication.get().getProject().getId() + "&width=" + chartWidth + "&height=" + chartHeight;
		Image chart = new Image(chartUrl, 0, 0, chartWidth, chartHeight);

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
		int chartWidth = 500;
		int chartHeight = 300;
		String chartUrl = GWT.getModuleBaseURL() + "/sprintBurndownChart.png?sprintId=" + sprint.getId() + "&width="
				+ chartWidth + "&height=" + chartHeight;
		return new GroupWidget("Current Sprint", new Image(chartUrl, 0, 0, chartWidth, chartHeight));
	}

	public static ProjectOverviewWidget get() {
		return WorkareaWidget.get().getProjectOverview();
	}
}
