package scrum.client.admin;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListController;
import scrum.client.common.BlockListWidget;
import scrum.client.common.PanelWidget;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.Composite;

public class ProjectSelectionWidget extends Composite {

	private BlockListWidget<ProjectWidget> list;

	public ProjectSelectionWidget() {
		list = new BlockListWidget<ProjectWidget>(new BlockListController<ProjectWidget>());
		initWidget(new PanelWidget("Select Project", list));
		update();
	}

	public void update() {
		list.clear();
		for (Project project : ScrumGwtApplication.get().getDao().getProjects()) {
			ProjectWidget block = new ProjectWidget(project);
			list.addBlock(block);
		}
	}

}
