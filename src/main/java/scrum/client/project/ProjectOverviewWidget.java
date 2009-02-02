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

	public ProjectOverviewWidget() {
		ItemFieldsWidget fields = new ItemFieldsWidget();

		fields.addField("Label", new AEditableTextWidget() {

			@Override
			protected void setText(String text) {
				ScrumGwtApplication.get().getProject().setLabel(text);
			}

			@Override
			protected String getText() {
				return ScrumGwtApplication.get().getProject().getLabel();
			}
		});

		fields.addField("Description", new AEditableTextareaWidget(true) {

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

		FlowPanel panel = new FlowPanel();
		panel.add(new GroupWidget("Project Properties", fields));
		Sprint sprint = ScrumGwtApplication.get().getProject().getCurrentSprint();
		if (sprint != null) {
			panel.add(createCurrentSprintOverview(sprint));
		}

		initWidget(panel);
	}

	private Widget createCurrentSprintOverview(Sprint sprint) {
		int width = 500;
		int height = 300;
		String url = "../sprintBurndownChart.png?sprintId=" + sprint.getId() + "&width=" + width + "&height=" + height;
		// String url = "http://www.google.de/intl/de_de/images/logo.gif";
		return new GroupWidget("Current Sprint", new Image(url, 0, 0, width, height));
	}
}
