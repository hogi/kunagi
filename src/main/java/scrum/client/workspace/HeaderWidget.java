package scrum.client.workspace;

import ilarkesto.gwt.client.FloatingFlowPanel;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.HyperlinkWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ApplicationInfo;
import scrum.client.admin.LogoutAction;
import scrum.client.common.AScrumWidget;
import scrum.client.project.ChangeProjectAction;
import scrum.client.undo.UndoButtonWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class HeaderWidget extends AScrumWidget {

	private Label title;
	private Label currentUserLabel;
	private UndoButtonWidget undoButton;

	@Override
	protected Widget onInitialization() {
		setHeight100();

		title = new Label("");
		title.setStyleName("HeaderWidget-title");

		currentUserLabel = new Label();
		currentUserLabel.setStyleName("HeaderWidget-user");

		undoButton = new UndoButtonWidget();

		ToolbarWidget toolbar = new ToolbarWidget();
		toolbar.add(undoButton);
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
		undoButton.setUndoManager(null);

		boolean loggedIn = cm.getAuth().isUserLoggedIn();

		ApplicationInfo applicationInfo = cm.getApp().getApplicationInfo();
		if (applicationInfo != null) {
			title.setText(applicationInfo.getName());
			title.setTitle(applicationInfo.getVersionDescription());
		}

		StringBuilder text = new StringBuilder();
		if (loggedIn) {
			text.append(getCurrentUser().getName());
			if (cm.getProjectContext().isProjectOpen()) {
				text.append(getCurrentProject().getUsersRolesAsString(getCurrentUser(), " (", ")"));
				text.append(" @ " + getCurrentProject().getLabel());
				undoButton.setUndoManager(cm.getUndo().getManager());
			}
		}
		currentUserLabel.setText(text.toString());

		super.onUpdate();
	}

}
