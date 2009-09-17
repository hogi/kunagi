package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.admin.ConfigureAction;
import scrum.client.admin.LogoutAction;
import scrum.client.project.ChangeProjectAction;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class HeaderWidget extends AWidget {

	private Label currentUserLabel;

	private ToolbarWidget toolbar;
	private HorizontalPanel controlPanel;

	@Override
	protected Widget onInitialization() {
		setHeight100();

		Label title = new Label("Scrum42");
		title.setStyleName("title");

		HorizontalPanel panel = new HorizontalPanel();
		panel.setStyleName("HeaderWidget");
		panel.setWidth("100%");
		panel.setHeight("100%");
		panel.add(title);
		panel.setCellVerticalAlignment(title, HasVerticalAlignment.ALIGN_MIDDLE);

		currentUserLabel = new Label();
		currentUserLabel.setStyleName("title");

		toolbar = new ToolbarWidget(true);

		controlPanel = new HorizontalPanel();
		controlPanel.setStyleName("HeaderWidget-controlPanel");
		controlPanel.add(currentUserLabel);
		controlPanel.add(toolbar);

		panel.add(controlPanel);
		panel.setCellHorizontalAlignment(controlPanel, HasHorizontalAlignment.ALIGN_RIGHT);

		return panel;
	}

	@Override
	protected void onUpdate() {
		boolean loggedIn = ScrumGwtApplication.get().getUser() != null;

		currentUserLabel.setText(loggedIn ? ScrumGwtApplication.get().getUser().getName() : "");

		controlPanel.remove(toolbar);
		toolbar = new ToolbarWidget(true);
		toolbar.addButton(new ChangeProjectAction());
		toolbar.addButton(new ConfigureAction());
		toolbar.addButton(new LogoutAction());
		toolbar.update();
		controlPanel.add(toolbar);
	}

}
