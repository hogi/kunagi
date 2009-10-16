package scrum.client.sprint;

import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.img.Img;
import scrum.client.project.CloseRequirementAction;
import scrum.client.project.ReopenRequirementAction;
import scrum.client.project.Requirement;
import scrum.client.project.RequirementWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class RequirementInSprintBlock extends AExtensibleBlockWidget<Requirement> {

	private BlockListWidget<Task> taskList;
	private FlowPanel panel;
	private RequirementWidget requirementWidget;
	private CommentsWidget commentsWidget;

	@Override
	protected void onCollapsedInitialization() {
		setIcon(Img.bundle.requirement16());
	}

	@Override
	protected void onUpdateHead() {
		Requirement requirement = getObject();
		setBlockTitle(requirement.getReference() + " " + requirement.getLabel());
		setAdditionalStyleName(requirement.isDone() ? "RequirementInSprintBlock-done" : null);
		addMenuAction(new CloseRequirementAction(requirement));
		addMenuAction(new ReopenRequirementAction(requirement));
		addMenuAction(new CreateTaskAction(requirement));
	}

	@Override
	protected Widget onExtendedInitialization() {
		Requirement requirement = getObject();

		requirementWidget = new RequirementWidget(requirement, false, false, true, false);
		taskList = new BlockListWidget<Task>(TaskInRequirementBlock.FACTORY);
		commentsWidget = new CommentsWidget(requirement);

		panel = new FlowPanel();
		panel.add(requirementWidget);
		panel.add(taskList);
		panel.add(commentsWidget);
		return panel;
	}

	@Override
	protected Widget onUpdateBody() {
		requirementWidget.update();
		taskList.setObjects(getObject().getTasks());
		commentsWidget.update();
		return panel;
	}

	public void selectTask(Task task) {
		taskList.extendObject(task);
	}

	public static final BlockWidgetFactory<Requirement> FACTORY = new BlockWidgetFactory<Requirement>() {

		public RequirementInSprintBlock createBlock() {
			return new RequirementInSprintBlock();
		}
	};
}
