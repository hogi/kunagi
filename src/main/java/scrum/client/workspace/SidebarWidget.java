package scrum.client.workspace;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.PanelWidget;
import scrum.client.common.StyleSheet;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SidebarWidget extends Composite {

	public SidebarWidget() {
		VerticalPanel sidebar = new VerticalPanel();
		sidebar.setStyleName(StyleSheet.ELEMENT_SIDEBAR_WIDGET);

		VerticalPanel buttonsPanel = new VerticalPanel();
		buttonsPanel.setWidth("100%");

		Button projectOverviewButton = new Button("Project Overview");
		projectOverviewButton.addClickListener(new ProjectOverviewClickListener());
		buttonsPanel.add(projectOverviewButton);

		Button backlogButton = new Button("Product Backlog");
		backlogButton.addClickListener(new BacklogClickListener());
		buttonsPanel.add(backlogButton);

		Button sprintButton = new Button("Current Sprint");
		sprintButton.addClickListener(new SprintClickListener());
		buttonsPanel.add(sprintButton);

		Button impedimentsButton = new Button("Impediments");
		impedimentsButton.addClickListener(new ImpedimentsClickListener());
		buttonsPanel.add(impedimentsButton);

		Button testButton = new Button("Test");
		testButton.addClickListener(new TestClickListener());
		buttonsPanel.add(testButton);

		sidebar.add(new PanelWidget("Navigation", buttonsPanel));
		sidebar.add(new HTML("&nbsp;"));
		sidebar.add(new PanelWidget("Clipboard", new ClipboardWidget()));

		initWidget(sidebar);
	}

	private class ProjectOverviewClickListener implements ClickListener {

		public void onClick(Widget sender) {
			WorkspaceWidget.showProjectOverview();
		}

	}

	private class ImpedimentsClickListener implements ClickListener {

		public void onClick(Widget sender) {
			WorkspaceWidget.lock("Loading impediments...");
			ScrumGwtApplication.get().callRequestImpediments(new Runnable() {

				public void run() {
					WorkspaceWidget.showImpediments();
				}
			});
		}
	}

	private class SprintClickListener implements ClickListener {

		public void onClick(Widget sender) {
			WorkspaceWidget.lock("Loading Sprint...");
			ScrumGwtApplication.get().callRequestCurrentSprint(new Runnable() {

				public void run() {
					WorkspaceWidget.showSprint();
				}
			});
		}

	}

	private class BacklogClickListener implements ClickListener {

		public void onClick(Widget sender) {
			WorkspaceWidget.lock("Loading Backlog Items...");
			ScrumGwtApplication.get().callRequestBacklogItems(new Runnable() {

				public void run() {
					WorkspaceWidget.showBacklog();
				}
			});
		}
	}

	private class TestClickListener implements ClickListener {

		public void onClick(Widget sender) {
			WorkspaceWidget.showTest();
		}
	}
}
