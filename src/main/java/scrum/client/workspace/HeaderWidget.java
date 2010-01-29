package scrum.client.workspace;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.HyperlinkWidget;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.undo.UndoButtonWidget;
import scrum.client.ApplicationInfo;
import scrum.client.admin.LogoutAction;
import scrum.client.common.AScrumWidget;
import scrum.client.img.Img;
import scrum.client.project.ChangeProjectAction;
import scrum.client.search.SearchInputWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class HeaderWidget extends AScrumWidget {

	private SimplePanel wrapper;
	private Label title;
	private Label currentUserLabel;
	private UndoButtonWidget undoButton;
	private SearchInputWidget search;

	@Override
	protected Widget onInitialization() {
		setHeight100();

		title = new Label("");
		title.setStyleName("HeaderWidget-title");

		currentUserLabel = new Label();
		currentUserLabel.setStyleName("HeaderWidget-user");

		undoButton = new UndoButtonWidget();

		search = new SearchInputWidget();

		wrapper = Gwt.createDiv("HeaderWidget", title);
		return wrapper;
	}

	@Override
	protected void onUpdate() {
		boolean projectOpen = cm.getProjectContext().isProjectOpen();

		undoButton.setUndoManager(null);

		ApplicationInfo applicationInfo = cm.getApp().getApplicationInfo();
		if (applicationInfo != null) {
			title.setText(applicationInfo.getName());
			title.setTitle(applicationInfo.getVersionDescription());
		}

		currentUserLabel.setText(createCurrentUserText());

		TableBuilder tb = new TableBuilder();
		tb.setCellPadding(2);
		tb.setColumnWidths("", "", "", "50px", "100px", "50px");
		tb.add(createLogo(), /* title, */currentUserLabel, projectOpen ? search : Gwt.createEmptyDiv(),
			projectOpen ? undoButton : Gwt.createEmptyDiv(), new HyperlinkWidget(new ChangeProjectAction()),
			new HyperlinkWidget(new LogoutAction()));
		wrapper.setWidget(tb.createTable());

		super.onUpdate();
	}

	private String createCurrentUserText() {
		boolean loggedIn = cm.getAuth().isUserLoggedIn();
		StringBuilder text = new StringBuilder();
		if (loggedIn) {
			text.append(getCurrentUser().getName());
			if (cm.getProjectContext().isProjectOpen()) {
				text.append(getCurrentProject().getUsersRolesAsString(getCurrentUser(), " (", ")"));
				text.append(" @ " + getCurrentProject().getLabel());
				undoButton.setUndoManager(cm.getUndo().getManager());
			}
		}
		return text.toString();
	}

	private Widget createLogo() {
		return Gwt.createDiv("HeaderWidget-logo", Img.bundle.logo25().createImage());
	}

}
