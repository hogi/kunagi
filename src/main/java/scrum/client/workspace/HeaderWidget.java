package scrum.client.workspace;

import ilarkesto.gwt.client.FloatingFlowPanel;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.HyperlinkWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ApplicationInfo;
import scrum.client.admin.LogoutAction;
import scrum.client.common.AScrumWidget;
import scrum.client.project.ChangeProjectAction;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class HeaderWidget extends AScrumWidget {

	private Label title;
	private Label currentUserLabel;

	@Override
	protected Widget onInitialization() {
		setHeight100();

		title = new Label("");
		title.setStyleName("HeaderWidget-title");

		currentUserLabel = new Label();
		currentUserLabel.setStyleName("HeaderWidget-user");

		ToolbarWidget toolbar = new ToolbarWidget();
		toolbar.add(new HyperlinkWidget(new ChangeProjectAction()));
		toolbar.add(new HyperlinkWidget(new LogoutAction()));

		FloatingFlowPanel panel = new FloatingFlowPanel();
		panel.add(title);
		panel.add(toolbar, true);
		panel.add(currentUserLabel, true);

		return Gwt.createDiv("HeaderWidget", panel);
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();

		boolean loggedIn = cm.getAuth().isUserLoggedIn();

		ApplicationInfo applicationInfo = cm.getApp().getApplicationInfo();
		if (applicationInfo != null) {
			title.setText(applicationInfo.getName());
			title.setTitle(applicationInfo.getVersionDescription());
		}

		String text = "";
		if (loggedIn) {
			text = getCurrentUser().getName();
			if (cm.getProjectContext().isProjectOpen()) {
				text = text + " @ " + getCurrentProject().getLabel();
			}
		}
		currentUserLabel.setText(text);
	}

}
