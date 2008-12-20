package scrum.client.project;

import scrum.client.admin.User;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.PanelWidget;
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

	public ProjectOverviewWidget(final Project project) {
		ItemFieldsWidget fields = new ItemFieldsWidget();

		fields.addField("Label", new AEditableTextWidget() {

			@Override
			protected void setText(String text) {
				project.setLabel(text);
			}

			@Override
			protected String getText() {
				return project.getLabel();
			}
		});

		fields.addField("Description", new AEditableTextareaWidget(true) {

			@Override
			protected void setText(String text) {
				project.setDescription(text);
			}

			@Override
			protected String getText() {
				return project.getDescription();
			}
		});

		User productOwner = project.getProductOwner();
		if (productOwner != null) {
			fields.addField("Product Owner", new Label(productOwner.getName()));
		}

		User scrumMaster = project.getScrumMaster();
		if (scrumMaster != null) {
			fields.addField("Scrum Master", new Label(scrumMaster.getName()));
		}

		String team = ScrumUtil.toCommataSeperatedString(project.getTeamMembers());
		if (team.length() > 0) {
			fields.addField("Team", new Label(team));
		}

		String admins = ScrumUtil.toCommataSeperatedString(project.getAdmins());
		if (admins.length() > 0) {
			fields.addField("Project Admins", new Label(admins));
		}

		FlowPanel panel = new FlowPanel();
		panel.add(fields);
		Sprint sprint = project.getCurrentSprint();
		if (sprint != null) {
			panel.add(createCurrentSprintOverview(sprint));
		}

		initWidget(panel);
	}

	private Widget createCurrentSprintOverview(Sprint sprint) {
		int width = 500;
		int height = 200;
		String url = "../sprintBurndownChart.png?sprintId=" + sprint.getId() + "&width=" + width + "&height=" + height;
		return new PanelWidget("Current Sprint", new Image(url, 0, 0, width, height));
	}
}
