package scrum.client.admin;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.StyleSheet;
import scrum.client.img.Img;
import scrum.client.project.Project;
import scrum.client.workspace.WorkspaceWidget;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ProjectWidget extends ABlockWidget {

	private Project project;

	public ProjectWidget(Project project) {
		this.project = project;
	}

	@Override
	protected String getBlockTitle() {
		return project.getLabel();
	}

	@Override
	protected Widget buildContent() {
		return new Label("Crazy project");
	}

	@Override
	protected Widget buildToolbar() {
		if (!isExtended()) // block is not extended -> no toolbar
			return null;

		// block is extended -> create toolbar with buttons
		VerticalPanel toolbar = new VerticalPanel();
		toolbar.setStyleName(StyleSheet.TOOLBAR);

		Button selectButton = new Button("Select");
		selectButton.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				select();
			}
		});
		toolbar.add(selectButton);

		return toolbar;
	}

	private void select() {
		WorkspaceWidget.lock("Loading project...");
		ScrumGwtApplication.get().callSelectProject(project.getId(), new Runnable() {

			public void run() {
				WorkspaceWidget.unlock();
				WorkspaceWidget.showTest();
			}
		});
	}

	@Override
	public void delete() {}

	@Override
	protected DropController getDropController() {
		return null;
	}

	@Override
	protected AbstractImagePrototype getIcon() {
		return Img.icons().projectIcon32();
	}

}
