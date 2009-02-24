package scrum.client.admin;

import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.img.Img;
import scrum.client.project.Project;
import scrum.client.workspace.Ui;
import scrum.client.workspace.WorkareaWidget;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class ProjectWidget extends ABlockWidget {

	private Project project;

	private HTML content;
	private ToolbarWidget toolbar;

	public ProjectWidget(Project project) {
		this.project = project;
	}

	@Override
	protected void onBlockInitialization() {
		content = new HTML();

	}

	@Override
	protected void onBlockUpdate() {
		setBlockTitle(project.getLabel());
		setIcon(Img.icons().projectIcon32());

		String description = project.getDescription();
		if (description == null) description = "No description.";
		if (!isSelected()) {
			int idx = description.indexOf('.');
			if (idx > 0) {
				description = description.substring(0, idx + 1);
			}
			if (description.length() > 150) {
				description = description.substring(0, 149) + "...";
			}
		}
		content.setHTML(description);

		setContent(content);
		setToolbar(isSelected() ? getToolbar() : null);
	}

	protected Widget getToolbar() {
		if (toolbar == null) {
			toolbar = new ToolbarWidget();
			toolbar.addButton("Open Project").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					select();
				}
			});
		}

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
	protected AbstractImagePrototype getIcon16() {
		return Img.icons().projectIcon32();
	}

}
