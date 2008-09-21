package scrum.client.workspace;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SidebarWidget extends Composite {

	private DropWidget dropArea;

	public SidebarWidget() {
		VerticalPanel sidebar = new VerticalPanel();
		sidebar.setStyleName("SidebarWidget");
		sidebar.setWidth("200px");
		sidebar.setHeight("600px");

		sidebar.add(new HTML("<h3>sidebar</h3>"));

		Button summaryButton = new Button("Project Summary");
		summaryButton.addClickListener(new SummaryClickListener());
		sidebar.add(summaryButton);

		Button backlogButton = new Button("Product Backlog");
		backlogButton.addClickListener(new BacklogClickListener());
		sidebar.add(backlogButton);

		Button sprintsButton = new Button("Manage Sprints");
		sprintsButton.addClickListener(new SprintsClickListener());
		sidebar.add(sprintsButton);

		Button impedimentsButton = new Button("Impediments");
		impedimentsButton.addClickListener(new ImpedimentsClickListener());
		sidebar.add(impedimentsButton);

		Button testButton = new Button("Test");
		testButton.addClickListener(new TestClickListener());
		sidebar.add(testButton);

		dropArea = new DropWidget();
		sidebar.add(dropArea);

		initWidget(sidebar);
	}

	private class SprintsClickListener implements ClickListener {

		public void onClick(Widget sender) {
			WorkspaceWidget.showSprints();
		}
	}

	private class ImpedimentsClickListener implements ClickListener {

		public void onClick(Widget sender) {
			WorkspaceWidget.showImpediments();
		}
	}

	private class SummaryClickListener implements ClickListener {

		public void onClick(Widget sender) {
			WorkspaceWidget.showSummary();
		}
	}

	private class BacklogClickListener implements ClickListener {

		public void onClick(Widget sender) {
			WorkspaceWidget.showBacklog();
		}
	}

	private class TestClickListener implements ClickListener {

		public void onClick(Widget sender) {
			WorkspaceWidget.showTest();
		}
	}
}
