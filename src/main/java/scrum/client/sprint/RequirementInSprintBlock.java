package scrum.client.sprint;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.ScrumGwt;
import scrum.client.collaboration.EmoticonsWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.img.Img;
import scrum.client.journal.ActivateChangeHistoryAction;
import scrum.client.journal.ChangeHistoryWidget;
import scrum.client.project.CloseRequirementAction;
import scrum.client.project.RemoveRequirementFromSprintAction;
import scrum.client.project.ReopenRequirementAction;
import scrum.client.project.Requirement;
import scrum.client.project.RequirementWidget;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class RequirementInSprintBlock extends ABlockWidget<Requirement> {

	private BlockListWidget<Task> taskList;
	private RequirementWidget requirementWidget;
	private Widget right;
	private FlexTable bodyWidget;
	private ChangeHistoryWidget changeHistoryWidget;

	private SimplePanel statusIcon;
	private Label statusLabel;

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		Requirement requirement = getObject();
		statusIcon = header.insertPrefixIcon();
		statusLabel = header.appendCenterSuffix("");
		header.appendCell(new EmoticonsWidget(requirement), null, true, true, null);
		header.addMenuAction(new CloseRequirementAction(requirement));
		header.addMenuAction(new ReopenRequirementAction(requirement));
		header.addMenuAction(new RemoveRequirementFromSprintAction(requirement));
		header.addMenuAction(new ActivateChangeHistoryAction(requirement));
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
		statusLabel.setText(requirement.getTaskStatusLabel());
		header.setCenter(requirement.getLabel());
	}

	@Override
	protected Widget onExtendedInitialization() {
		Requirement requirement = getObject();

		requirementWidget = new RequirementWidget(requirement, false, false, true, false, false, false);
		taskList = new BlockListWidget<Task>(TaskInRequirementBlock.FACTORY);
		taskList.setAutoSorter(Task.NUMBER_COMPARATOR);
		right = ScrumGwt.createEmoticonsAndComments(requirement);
		changeHistoryWidget = new ChangeHistoryWidget(requirement);

		FlowPanel left = new FlowPanel();
		left.add(requirementWidget);
		left.add(taskList);
		left.add(changeHistoryWidget);

		bodyWidget = TableBuilder.row(20, left, right);
		return bodyWidget;
	}

	@Override
	protected void onUpdateBody() {
		requirementWidget.update();
		taskList.setObjects(getObject().getTasks());
		Gwt.update(right);
		changeHistoryWidget.update();
	}

	public void selectTask(Task task) {
		taskList.showObject(task);
	}

	public static final BlockWidgetFactory<Requirement> FACTORY = new BlockWidgetFactory<Requirement>() {

		public RequirementInSprintBlock createBlock() {
			return new RequirementInSprintBlock();
		}
	};
}
