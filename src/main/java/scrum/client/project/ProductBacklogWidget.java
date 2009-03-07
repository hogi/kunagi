package scrum.client.project;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;
import scrum.client.workspace.WorkareaWidget;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class ProductBacklogWidget extends AWidget {

	public BlockListWidget<Requirement> list;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<Requirement>(RequirementWidget.class);
		ToolbarWidget toolbar = new ToolbarWidget(true);
		toolbar.addButton("Create new Requirement").addClickListener(new CreateClickListener());

		FlowPanel panel = new FlowPanel();
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);

		return new GroupWidget("Product Backlog", panel);
	}

	@Override
	protected void onUpdate() {
		list.setBlocks(ScrumGwtApplication.get().getProject().getRequirements());
	}

	class CreateClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Requirement requirement = ScrumGwtApplication.get().getProject().createNewRequirement();
			list.addBlock(requirement, true);
		}
	}

	public static ProductBacklogWidget get() {
		return WorkareaWidget.get().getProductBacklog();
	}
}
