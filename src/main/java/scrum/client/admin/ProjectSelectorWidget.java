package scrum.client.admin;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.Dao;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;
import scrum.client.project.CreateProjectAction;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class ProjectSelectorWidget extends AWidget {

	private BlockListWidget<Project> list;

	@Override
	protected Widget onInitialization() {

		list = new BlockListWidget<Project>(ProjectBlock.FACTORY);
		list.setAutoSorter(Project.LABEL_COMPARATOR);

		ToolbarWidget toolbar = new ToolbarWidget();
		toolbar.addButton(new CreateProjectAction());

		FlowPanel panel = new FlowPanel();
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);

		return new GroupWidget("Projects", panel);
	}

	@Override
	protected void onUpdate() {
		list.setObjects(Dao.get().getProjects());
	}

	public BlockListWidget<Project> getList() {
		return this.list;
	}

}
