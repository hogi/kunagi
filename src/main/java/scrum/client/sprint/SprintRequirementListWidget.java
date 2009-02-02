package scrum.client.sprint;

import scrum.client.common.BlockListController;
import scrum.client.common.BlockListWidget;
import scrum.client.project.Requirement;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;

public class SprintRequirementListWidget extends Composite {

	private CurrentSprintWidget sprintWidget;
	public BlockListWidget<SprintRequirementWidget> list = new BlockListWidget<SprintRequirementWidget>();

	public SprintRequirementListWidget(CurrentSprintWidget sprintWidget) {
		this.sprintWidget = sprintWidget;

		list.setController(new BlockListController<SprintRequirementWidget>() {

			@Override
			public void dataChanged(SprintRequirementWidget block) {
				SprintRequirementListWidget.this.sprintWidget.update();
			}
		});

		FlowPanel panel = new FlowPanel();
		panel.add(new HTML("<br />"));
		panel.add(list);
		initWidget(panel);

		update();
	}

	public void update() {
		list.clear();
		for (Requirement item : sprintWidget.getSprint().getRequirements()) {
			list.addBlock(new SprintRequirementWidget(item));
		}
	}

}
