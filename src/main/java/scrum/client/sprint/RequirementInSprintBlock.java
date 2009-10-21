package scrum.client.sprint;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.img.Img;
import scrum.client.project.CloseRequirementAction;
import scrum.client.project.ReopenRequirementAction;
import scrum.client.project.Requirement;
import scrum.client.project.RequirementWidget;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class RequirementInSprintBlock extends AExtensibleBlockWidget<Requirement> {

	private BlockListWidget<Task> taskList;
	private RequirementWidget requirementWidget;
	private CommentsWidget commentsWidget;
	private FlexTable bodyWidget;

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

		FlowPanel left = new FlowPanel();
		left.add(requirementWidget);
		left.add(commentsWidget);

		TableBuilder tb = new TableBuilder();
		tb.setColumnWidths("50%", "20px", "50%");
		tb.addRow(left, Gwt.createSpacer(20, 1), taskList);
		bodyWidget = tb.createTable();
		return bodyWidget;
	}

	@Override
	protected Widget onUpdateBody() {
		requirementWidget.update();
		taskList.setObjects(getObject().getTasks());
		commentsWidget.update();
		return bodyWidget;
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
