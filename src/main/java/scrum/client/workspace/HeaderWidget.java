package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class HeaderWidget extends AWidget {

	private Label currentUserLabel;

	private ToolbarWidget toolbar;

	private ButtonWidget changeProjectButton;
	private ButtonWidget changePasswordButton;
	private ButtonWidget logoutButton;

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

		changeProjectButton = toolbar.addButton("Change Project", new Command() {

			public void execute() {
				ScrumGwtApplication.get().closeProject();
			}
		});

		changePasswordButton = toolbar.addButton("Change Password", new Command() {

			public void execute() {
				Ui.get().showConfiguration();
			}
		});

		logoutButton = toolbar.addButton("Logout", new Command() {

			public void execute() {
				ScrumGwtApplication.get().logout();
			}
		});

		HorizontalPanel controlPanel = new HorizontalPanel();
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
		changeProjectButton.setVisible(true || ScrumGwtApplication.get().getProject() != null);
		changePasswordButton.setVisible(true || loggedIn);
		logoutButton.setVisible(true || loggedIn);
		toolbar.update();
	}

}
