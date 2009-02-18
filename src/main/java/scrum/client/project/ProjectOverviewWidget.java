package scrum.client.project;

import scrum.client.ScrumGwtApplication;
import scrum.client.admin.User;
import scrum.client.common.GroupWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.ScrumUtil;
import scrum.client.common.editable.AEditableTextWidget;
import scrum.client.common.editable.AEditableTextareaWidget;
import scrum.client.sprint.Sprint;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ProjectOverviewWidget extends Composite {

	private AEditableTextWidget label;
	private AEditableTextareaWidget description;

	public ProjectOverviewWidget() {
		ItemFieldsWidget fields = new ItemFieldsWidget();

		label = fields.addField("Label", new AEditableTextWidget() {

			@Override
			protected void setText(String text) {
				ScrumGwtApplication.get().getProject().setLabel(text);
			}

			@Override
			protected String getText() {
				return ScrumGwtApplication.get().getProject().getLabel();
			}
		});

		description = fields.addField("Description", new AEditableTextareaWidget(true) {

			@Override
			protected void setText(String text) {
				ScrumGwtApplication.get().getProject().setDescription(text);
			}

			@Override
			protected String getText() {
				return ScrumGwtApplication.get().getProject().getDescription();
			}
		});

		User productOwner = ScrumGwtApplication.get().getProject().getProductOwner();
		if (productOwner != null) {
			fields.addField("Product Owner", new Label(productOwner.getName()));
		}

		User scrumMaster = ScrumGwtApplication.get().getProject().getScrumMaster();
		if (scrumMaster != null) {
			fields.addField("Scrum Master", new Label(scrumMaster.getName()));
		}

		String team = ScrumUtil.toCommataSeperatedString(ScrumGwtApplication.get().getProject().getTeamMembers());
		if (team.length() > 0) {
			fields.addField("Team", new Label(team));
		}

		String admins = ScrumUtil.toCommataSeperatedString(ScrumGwtApplication.get().getProject().getAdmins());
		if (admins.length() > 0) {
			fields.addField("Project Admins", new Label(admins));
		}

		int chartWidth = 500;
		int chartHeight = 300;
		String chartUrl = "../projectBurndownChart.png?projectId=" + ScrumGwtApplication.get().getProject().getId()
				+ "&width=" + chartWidth + "&height=" + chartHeight;
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

		initWidget(panel);
	}

	public void update() {
		label.rebuild();
		description.rebuild();
	}

	private Widget createCurrentSprintOverview(Sprint sprint) {
		int chartWidth = 500;
		int chartHeight = 300;
		String chartUrl = "../sprintBurndownChart.png?sprintId=" + sprint.getId() + "&width=" + chartWidth + "&height="
				+ chartHeight;
		return new GroupWidget("Current Sprint", new Image(chartUrl, 0, 0, chartWidth, chartHeight));
	}
}
