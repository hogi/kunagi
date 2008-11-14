package scrum.client.sprint;

import scrum.client.common.BlockListWidget;
import scrum.client.common.ScrumUtil;
import scrum.client.common.StyleSheet;
import scrum.client.project.BacklogItem;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SprintBacklogItemListWidget extends Composite {

	private Sprint sprint;
	public BlockListWidget list;

	public SprintBacklogItemListWidget(Sprint sprint) {
		this.sprint = sprint;

		Button createButton = new Button("Add Backlog-Item");
		createButton.addClickListener(new CreateClickListener());

		HorizontalPanel toolbar = new HorizontalPanel();
		toolbar.setWidth("100%");
		toolbar.setStyleName(StyleSheet.TOOLBAR);
		toolbar.add(createButton);
		ScrumUtil.addFiller(toolbar);

		list = new BlockListWidget();

		FlowPanel panel = new FlowPanel();
		panel.setWidth("100%");
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);
		initWidget(panel);

		update();
	}

	public void update() {
		list.clear();
		for (BacklogItem item : sprint.getBacklogItems()) {
			list.addBlock(new SprintBacklogItemWidget(item));
		}
	}

	class CreateClickListener implements ClickListener {

		public void onClick(Widget sender) {
		// BacklogItem item = ScrumGwtApplication.get().getProject().createNewBacklogItem();
		// SprintBacklogItemWidget block = new SprintBacklogItemWidget(item);
		// list.addBlock(block);
		// list.selectBlock(block);
		}
	}
}
