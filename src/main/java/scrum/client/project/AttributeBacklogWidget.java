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

public class AttributeBacklogWidget extends AWidget {

	public BlockListWidget<Attribute> list;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<Attribute>(AttributeBlock.FACTORY);
		ToolbarWidget toolbar = new ToolbarWidget(true);
		toolbar.addButton("Create new Attribute").addClickListener(new CreateClickListener());

		FlowPanel panel = new FlowPanel();
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);

		return new GroupWidget("Attribute Backlog", panel);
	}

	@Override
	protected void onUpdate() {
		list.setBlocks(ScrumGwtApplication.get().getProject().getAttributes());
	}

	class CreateClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Attribute attribute = ScrumGwtApplication.get().getProject().createNewAttribute();
			list.addBlock(attribute, true);
		}
	}

	public static AttributeBacklogWidget get() {
		return WorkareaWidget.get().getAttributeBacklog();
	}
}
