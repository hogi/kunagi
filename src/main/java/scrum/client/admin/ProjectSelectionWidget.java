package scrum.client.admin;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListWidget;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.Composite;

public class ProjectSelectionWidget extends Composite {

	private BlockListWidget list = new BlockListWidget();

	public ProjectSelectionWidget() {
		list.setHeight("400px");
		initWidget(list);
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
