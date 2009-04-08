package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class HeaderWidget extends AWidget {

	private Label currentUserLabel;

	private ToolbarWidget toolbar;

	private ButtonWidget logoutButton;

	private ButtonWidget changePasswordButton;

	@Override
	protected Widget onInitialization() {
		// Label label = new Label("Scrum42");
		// label.setStyleName("HeaderWidget");
		// return label;

		// SimplePanel panel = new SimplePanel();
		// panel.setStyleName("HeaderWidget");
		// panel.setWidget(new Label("Scrum42"));
		// return panel;

		Label title = new Label("Scrum42");
		title.setStyleName("title");

		HorizontalPanel panel = new HorizontalPanel();
		panel.setStyleName("HeaderWidget");
		panel.setWidth("100%");
		panel.add(title);
		panel.setCellVerticalAlignment(title, HasVerticalAlignment.ALIGN_MIDDLE);

		currentUserLabel = new Label();
		currentUserLabel.setStyleName("title");

		toolbar = new ToolbarWidget(true);
		logoutButton = new ButtonWidget("logout");
		logoutButton.addClickListener(new LogoutClickListener());
		toolbar.add(logoutButton);

		changePasswordButton = new ButtonWidget("change pwd");
		changePasswordButton.addClickListener(new ChangePasswordClickListener());
		toolbar.add(changePasswordButton);

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
		logoutButton.setVisible(loggedIn);
		changePasswordButton.setVisible(loggedIn);
	}

	class LogoutClickListener implements ClickListener {

		public void onClick(Widget sender) {
			ScrumGwtApplication.get().logout();
		}

	}

	class ChangePasswordClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Ui.get().showConfiguration();
		}

	}

}
