package scrum.client.sprint;

import scrum.client.common.BlockListController;
import scrum.client.common.BlockListWidget;
import scrum.client.common.StyleSheet;
import scrum.client.project.Story;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;

public class SprintStoryListWidget extends Composite {

	private CurrentSprintWidget sprintWidget;
	public BlockListWidget<SprintStoryWidget> list = new BlockListWidget<SprintStoryWidget>();

	public SprintStoryListWidget(CurrentSprintWidget sprintWidget) {
		this.sprintWidget = sprintWidget;

		list.setController(new BlockListController<SprintStoryWidget>() {

			@Override
			public void dataChanged(SprintStoryWidget block) {
				SprintStoryListWidget.this.sprintWidget.update();
			}
		});

		FlowPanel panel = new FlowPanel();
		panel.setStyleName(StyleSheet.ELEMENT_SPRINT_STORY_LIST_WIDGET);
		panel.add(new HTML("<br />"));
		panel.add(list);
		initWidget(panel);

		update();
	}

	public void update() {
		list.clear();
		for (Story item : sprintWidget.getSprint().getStorys()) {
			list.addBlock(new SprintStoryWidget(item));
		}
	}

}
