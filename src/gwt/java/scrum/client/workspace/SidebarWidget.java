package scrum.client.workspace;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SidebarWidget extends Composite {

	private ClipboardWidget clipboard;

	public SidebarWidget() {
		VerticalPanel sidebar = new VerticalPanel();
		sidebar.setStyleName("SidebarWidget");
		sidebar.setWidth("300px");

		sidebar.add(new HTML("<h3>sidebar</h3>"));

		Button backlogButton = new Button("Product Backlog");
		backlogButton.addClickListener(new BacklogClickListener());
		sidebar.add(backlogButton);

		Button impedimentsButton = new Button("Impediments");
		impedimentsButton.addClickListener(new ImpedimentsClickListener());
		sidebar.add(impedimentsButton);

		Button testButton = new Button("Test");
		testButton.addClickListener(new TestClickListener());
		sidebar.add(testButton);

		clipboard = new ClipboardWidget();
		sidebar.add(clipboard);

		initWidget(sidebar);
	}

	private class ImpedimentsClickListener implements ClickListener {

		public void onClick(Widget sender) {
			WorkspaceWidget.showImpediments();
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
