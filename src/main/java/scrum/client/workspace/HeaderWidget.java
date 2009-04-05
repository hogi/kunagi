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

	private LogoutClickListener logoutClickListener;

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

		HorizontalPanel currentUserPanel = new HorizontalPanel();
		currentUserLabel = new Label();
		currentUserLabel.setStyleName("title");
		currentUserPanel.add(currentUserLabel);

		toolbar = new ToolbarWidget(true);
		logoutButton = new ButtonWidget("logout");
		logoutClickListener = new LogoutClickListener();
		logoutButton.addClickListener(logoutClickListener);
		toolbar.add(logoutButton);
		currentUserPanel.add(toolbar);

		panel.add(currentUserPanel);
		panel.setCellHorizontalAlignment(currentUserPanel, HasHorizontalAlignment.ALIGN_RIGHT);

		return panel;
	}

	@Override
	protected void onUpdate() {
		if (ScrumGwtApplication.get().getUser() != null) {
			currentUserLabel.setText(ScrumGwtApplication.get().getUser().getName());
		}
	}

	class LogoutClickListener implements ClickListener {

		public void onClick(Widget sender) {
			ScrumGwtApplication.get().logout();
		}

	}

}
