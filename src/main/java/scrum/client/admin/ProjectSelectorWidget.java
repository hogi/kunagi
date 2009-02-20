package scrum.client.admin;

import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListController;
import scrum.client.common.BlockListWidget;
import scrum.client.common.PanelWidget;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.Widget;

public class ProjectSelectorWidget extends AWidget {

	private BlockListWidget<ProjectWidget> list;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<ProjectWidget>(new BlockListController<ProjectWidget>());
		return new PanelWidget("Select Project", list);
	}

	@Override
	protected void onUpdate() {
		list.clear();
		for (Project project : ScrumGwtApplication.get().getDao().getProjects()) {
			ProjectWidget block = new ProjectWidget(project);
			list.addBlock(block);
		}
	}

}
