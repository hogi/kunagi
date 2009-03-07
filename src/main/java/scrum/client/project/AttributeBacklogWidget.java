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

	public BlockListWidget<AttributeWidget> list;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<AttributeWidget>();
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
		list.update();
		list.clear();
		for (Attribute item : ScrumGwtApplication.get().getProject().getAttributes()) {
			list.addBlock(new AttributeWidget(item));
		}
	}

	class CreateClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Attribute attribute = ScrumGwtApplication.get().getProject().createNewAttribute();
			AttributeWidget block = new AttributeWidget(attribute);
			list.addBlock(block);
			list.selectBlock(block);
		}
	}

	public static AttributeBacklogWidget get() {
		return WorkareaWidget.get().getAttributeBacklog();
	}
}
