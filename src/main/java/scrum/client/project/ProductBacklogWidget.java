package scrum.client.project;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;

import java.util.List;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListWidget;
import scrum.client.common.BlockMoveObserver;
import scrum.client.common.GroupWidget;
import scrum.client.img.Img;
import scrum.client.workspace.WorkareaWidget;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class ProductBacklogWidget extends AWidget {

	public BlockListWidget<Requirement> list;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<Requirement>(RequirementBlock.FACTORY);
		list.setAutoSorter(ScrumGwtApplication.get().getProject().getRequirementsOrderComparator());
		list.setDndSorting(true);
		list.setMoveObserver(new MoveObserver());
		ToolbarWidget toolbar = new ToolbarWidget(true);
		toolbar.addButton(Img.bundle.new16().createImage(), "Create new Requirement").addClickListener(
			new CreateClickListener());

		FlowPanel panel = new FlowPanel();
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);

		return new GroupWidget("Product Backlog", panel);
	}

	@Override
	protected void onUpdate() {
		list.setObjects(ScrumGwtApplication.get().getProject().getRequirements());
	}

	public void selectRequirement(Requirement requirement) {
		list.selectObject(requirement);
	}

	class CreateClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Requirement requirement = ScrumGwtApplication.get().getProject().createNewRequirement();
			list.addObject(requirement, true);
		}
	}

	class MoveObserver implements BlockMoveObserver<Requirement> {

		public void onBlockMoved() {
			List<Requirement> requirements = list.getObjects();
			ScrumGwtApplication.get().getProject().updateRequirementsOrder(requirements);
		}

	}

	public static ProductBacklogWidget get() {
		return WorkareaWidget.get().getProductBacklog();
	}
}
