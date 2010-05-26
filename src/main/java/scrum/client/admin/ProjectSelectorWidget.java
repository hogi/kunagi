package scrum.client.admin;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ButtonWidget;
import scrum.client.Dao;
import scrum.client.common.BlockListWidget;
import scrum.client.project.CreateExampleProjectAction;
import scrum.client.project.CreateProjectAction;
import scrum.client.project.Project;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class ProjectSelectorWidget extends AWidget {

	private BlockListWidget<Project> list;

	@Override
	protected Widget onInitialization() {

		list = new BlockListWidget<Project>(ProjectBlock.FACTORY);
		list.setAutoSorter(Project.LABEL_COMPARATOR);

		PagePanel page = new PagePanel();
		page.addHeader("Projects", new ButtonWidget(new CreateProjectAction()), new ButtonWidget(
				new CreateExampleProjectAction()));
		page.addSection(list);
		return page;
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		list.setObjects(Dao.get().getProjects());
	}

	public BlockListWidget<Project> getList() {
		return this.list;
	}

}
