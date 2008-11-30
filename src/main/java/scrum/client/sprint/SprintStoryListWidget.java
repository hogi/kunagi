package scrum.client.sprint;

import scrum.client.common.BlockListWidget;
import scrum.client.common.ScrumUtil;
import scrum.client.common.StyleSheet;
import scrum.client.project.Story;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SprintStoryListWidget extends Composite {

	private Sprint sprint;
	public BlockListWidget<SprintStoryWidget> list = new BlockListWidget<SprintStoryWidget>();

	public SprintStoryListWidget(Sprint sprint) {
		this.sprint = sprint;

		Button createButton = new Button("Add Backlog-Item");
		createButton.addClickListener(new CreateClickListener());

		HorizontalPanel toolbar = new HorizontalPanel();
		toolbar.setStyleName(StyleSheet.TOOLBAR);
		toolbar.add(createButton);
		ScrumUtil.addFiller(toolbar);

		FlowPanel panel = new FlowPanel();
		panel.setWidth("100%");
		panel.add(toolbar);
		panel.add(new HTML("<br />"));
		panel.add(list);
		initWidget(panel);

		update();
	}

	public void update() {
		list.clear();
		for (Story item : sprint.getStorys()) {
			list.addBlock(new SprintStoryWidget(item));
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
