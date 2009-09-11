package scrum.client.admin;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.Dao;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;
import scrum.client.img.Img;
import scrum.client.project.Project;
import scrum.client.workspace.Ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class ProjectSelectorWidget extends AWidget {

	private BlockListWidget<Project> list;

	class CreateClickListener implements ClickHandler {

		public void onClick(ClickEvent event) {
			Project project = new Project(ScrumGwtApplication.get().getUser());
			ScrumGwtApplication.get().getDao().createProject(project);
			list.addObjects(project);
		}
	}

	@Override
	protected Widget onInitialization() {

		list = new BlockListWidget<Project>(ProjectBlock.FACTORY);
		list.setAutoSorter(Project.LABEL_COMPARATOR);

		ToolbarWidget toolbar = new ToolbarWidget(true);

		toolbar.addButton(Img.bundle.new16().createImage(), "Create new Project").addClickHandler(
			new CreateClickListener());

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

	public static ProjectSelectorWidget get() {
		return Ui.get().getStartPage().getProjectSelector();
	}

	public BlockListWidget<Project> getList() {
		return this.list;
	}

}
