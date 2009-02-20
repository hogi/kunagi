package scrum.client.admin;

import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.img.Img;
import scrum.client.project.Project;
import scrum.client.workspace.Ui;
import scrum.client.workspace.WorkareaWidget;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
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
		String description = project.getDescription();
		if (description == null) description = "No description.";
		if (!isExtended()) {
			int idx = description.indexOf('.');
			if (idx > 0) {
				description = description.substring(0, idx + 1);
			}
			if (description.length() > 150) {
				description = description.substring(0, 149) + "...";
			}
		}
		return new HTML(description);
	}

	@Override
	protected Widget buildToolbar() {
		if (!isExtended()) // block is not extended -> no toolbar
			return null;

		// block is extended -> create toolbar with buttons
		ToolbarWidget toolbar = new ToolbarWidget();

		toolbar.addButton("Open Project").addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				select();
			}
		});

		return toolbar;
	}

	private void select() {
		Ui.get().lock("Loading project...");
		ScrumGwtApplication.get().setProject(project);
		ScrumGwtApplication.get().callSelectProject(project.getId(), new Runnable() {

			public void run() {
				Ui.get().unlock();
				Ui.get().showWorkspace();
				WorkareaWidget.get().showProjectOverview();
			}
		});
	}

	@Override
	public void delete() {}

	@Override
	protected DropController createDropController() {
		return null;
	}

	@Override
	protected AbstractImagePrototype getIcon16() {
		return Img.icons().projectIcon32();
	}

	@Override
	protected AbstractImagePrototype getIcon32() {
		return Img.icons().projectIcon32();
	}

}
