package scrum.client.admin;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.Dao;
import scrum.client.common.BlockListWidget;
import scrum.client.project.CreateProjectAction;
import scrum.client.project.Project;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class ProjectSelectorWidget extends AWidget {

	ToolbarWidget toolbar;
	private BlockListWidget<Project> list;

	@Override
	protected Widget onInitialization() {

		list = new BlockListWidget<Project>(ProjectBlock.FACTORY);
		list.setAutoSorter(Project.LABEL_COMPARATOR);

		toolbar = new ToolbarWidget();
		toolbar.addButton(new CreateProjectAction());

		PagePanel page = new PagePanel();
		page.addHeader("Projects");
		page.addSection(toolbar);
		page.addSection(list);
		return page;
	}

	@Override
	protected void onUpdate() {
		toolbar.update();
		list.setObjects(Dao.get().getProjects());
	}

	public BlockListWidget<Project> getList() {
		return this.list;
	}

}
