package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ApplicationInfo;
import scrum.client.Components;
import scrum.client.ProjectContext;
import scrum.client.ScrumGwtApplication;
import scrum.client.admin.LogoutAction;
import scrum.client.project.ChangeProjectAction;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class HeaderWidget extends AWidget {

	private ProjectContext projectContext = Components.get().getProjectContext();

	private Label title;
	private Label currentUserLabel;

	private ToolbarWidget toolbar;
	private HorizontalPanel controlPanel;

	@Override
	protected Widget onInitialization() {
		setHeight100();

		title = new Label("");
		title.setStyleName("title");

		HorizontalPanel panel = new HorizontalPanel();
		panel.setStyleName("HeaderWidget");
		panel.setWidth("100%");
		panel.setHeight("100%");
		panel.add(title);
		panel.setCellVerticalAlignment(title, HasVerticalAlignment.ALIGN_MIDDLE);

		currentUserLabel = new Label();
		currentUserLabel.setStyleName("title");

		toolbar = new ToolbarWidget();

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

		ApplicationInfo applicationInfo = ScrumGwtApplication.get().getApplicationInfo();
		if (applicationInfo != null) {
			title.setText(applicationInfo.getName());
			title.setTitle(applicationInfo.getVersionDescription());
		}

		String text = "";
		if (loggedIn) {
			text = ScrumGwtApplication.get().getUser().getName();
			if (projectContext.isProjectOpen()) {
				text = text + " @ " + ScrumGwtApplication.get().getProject().getLabel();
			}
		}
		currentUserLabel.setText(text);

		controlPanel.remove(toolbar);
		toolbar = new ToolbarWidget();
		toolbar.addButton(new ChangeProjectAction());
		toolbar.addButton(new LogoutAction());
		toolbar.update();
		controlPanel.add(toolbar.update());
	}

}
