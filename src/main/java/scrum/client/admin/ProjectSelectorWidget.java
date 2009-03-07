package scrum.client.admin;

import ilarkesto.gwt.client.AWidget;
import scrum.client.Dao;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.Widget;

public class ProjectSelectorWidget extends AWidget {

	private BlockListWidget<Project> list;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<Project>(ProjectWidget.class);
		return new GroupWidget("Select Project", list);
	}

	@Override
	protected void onUpdate() {
		list.setBlocks(Dao.get().getProjects());
	}

}
