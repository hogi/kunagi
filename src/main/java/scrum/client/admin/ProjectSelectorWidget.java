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

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class ProjectSelectorWidget extends AWidget {

	private BlockListWidget<Project> list;

	class CreateClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Project project = new Project(ScrumGwtApplication.get().getUser());
			ScrumGwtApplication.get().getDao().createProject(project);
			list.addBlock(project, true);
		}
	}

	@Override
	protected Widget onInitialization() {

		list = new BlockListWidget<Project>(ProjectBlock.FACTORY);
		list.setDndSorting(true);

		ToolbarWidget toolbar = new ToolbarWidget(true);

		toolbar.addButton(Img.bundle.new16().createImage(), "Create new Project").addClickListener(
			new CreateClickListener());

		FlowPanel panel = new FlowPanel();
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);

		return new GroupWidget("Select Project", panel);
	}

	@Override
	protected void onUpdate() {
		list.setBlocks(Dao.get().getProjects());
	}

	public static ProjectSelectorWidget get() {
		return Ui.get().getProjectSelector();
	}

	public BlockListWidget<Project> getList() {
		return this.list;
	}

}
