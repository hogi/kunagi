package scrum.client.sprint;

import ilarkesto.gwt.client.TableBuilder;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.img.Img;
import scrum.client.project.CloseRequirementAction;
import scrum.client.project.ReopenRequirementAction;
import scrum.client.project.Requirement;
import scrum.client.project.RequirementWidget;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class RequirementInSprintBlock extends ABlockWidget<Requirement> {

	private BlockListWidget<Task> taskList;
	private RequirementWidget requirementWidget;
	private CommentsWidget commentsWidget;
	private FlexTable bodyWidget;

	private SimplePanel statusIcon;

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		Requirement requirement = getObject();
		statusIcon = header.insertPrefixIcon();
		header.addMenuAction(new CloseRequirementAction(requirement));
		header.addMenuAction(new ReopenRequirementAction(requirement));
		header.addMenuAction(new CreateTaskAction(requirement));
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		Requirement requirement = getObject();
		header.setDragHandle(requirement.getReference());
		Image statusImage = null;
		if (requirement.isClosed()) {
			statusImage = Img.bundle.reqClosed().createImage();
			statusImage.setTitle("Closed.");
		} else if (requirement.isTasksClosed()) {
			statusImage = Img.bundle.reqTasksClosed().createImage();
			statusImage.setTitle("All tasks done.");
		}
		statusIcon.setWidget(statusImage);
		header.setCenter(requirement.getLabel());
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

		bodyWidget = TableBuilder.row(20, left, taskList);
		return bodyWidget;
	}

	@Override
	protected void onUpdateBody() {
		requirementWidget.update();
		taskList.setObjects(getObject().getTasks());
		commentsWidget.update();
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
