package scrum.client.project;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListWidget;
import scrum.client.common.ToolbarWidget;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class StoryListWidget extends Composite {

	public BlockListWidget<StoryWidget> list = new BlockListWidget<StoryWidget>();

	public StoryListWidget() {
		ToolbarWidget toolbar = new ToolbarWidget();
		toolbar.addButton("Create new Backlog-Item").addClickListener(new CreateClickListener());

		FlowPanel panel = new FlowPanel();
		panel
				.add(new Label(
						"The product backlog (or \"backlog\") is the requirements for a system, expressed as a prioritized list of product backlog Items. These included both functional and non-functional customer requirements, as well as technical team-generated requirements. While there are multiple inputs to the product backlog, it is the sole responsibility of the product owner to prioritize the product backlog."));
		panel.add(new HTML("<br>"));
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);
		initWidget(panel);
	}

	public void update() {
		list.clear();
		for (Story item : ScrumGwtApplication.get().getProject().getStorys()) {
			list.addBlock(new StoryWidget(item));
		}
	}

	class CreateClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Story item = ScrumGwtApplication.get().getProject().createNewStory();
			StoryWidget block = new StoryWidget(item);
			list.addBlock(block);
			list.selectBlock(block);
		}
	}
}
