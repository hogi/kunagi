package scrum.client.sprint;

import ilarkesto.gwt.client.ToolbarWidget;

import java.util.ArrayList;
import java.util.List;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockListController;
import scrum.client.common.BlockListWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.dnd.BlockListDropController;
import scrum.client.img.Img;
import scrum.client.project.Requirement;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RequirementInSprintWidget extends ABlockWidget {

	private Requirement requirement;
	private BlockListWidget<TaskWidget> taskList;

	private Label taskEffortSum;

	private List<Task> previousTasks = new ArrayList<Task>(0);

	public RequirementInSprintWidget(Requirement requirement) {
		this.requirement = requirement;
		taskEffortSum = new Label();

		taskList = new BlockListWidget<TaskWidget>(new BlockListController<TaskWidget>() {

			@Override
			public void dataChanged(TaskWidget block) {
				update();
				notifyListControllerDataChanged();
			}
		});
	}

	public Requirement getRequirement() {
		return requirement;
	}

	@Override
	protected Widget buildContent() {
		if (!isExtended()) { return new Label(requirement.getSprintBacklogSummary()); }

		ItemFieldsWidget fieldsWidget = new ItemFieldsWidget();
		fieldsWidget.addField("Description", new Label(requirement.getDescription()));
		fieldsWidget.addField("Test", new Label(requirement.getTestDescription()));
		fieldsWidget.addField("Remainig Work", taskEffortSum);

		FlowPanel panel = new FlowPanel();
		panel.add(fieldsWidget);

		panel.add(taskList);

		update();

		return panel;
	}

	public void update() {
		taskEffortSum.setText(requirement.getRemainingWork());

		TaskWidget selectedBlock = taskList.getSelectedBlock();
		Task selectedTask = selectedBlock == null ? null : selectedBlock.getTask();

		List<Task> tasks = requirement.getTasks();
		if (!tasks.equals(previousTasks)) {
			taskList.clear();
			for (Task task : tasks) {
				TaskWidget block = addBlock(task);
				if (selectedTask == task) {
					taskList.selectBlock(block);
				}
			}
			previousTasks = tasks;
		}
	}

	private TaskWidget addBlock(Task task) {
		TaskWidget block = new TaskWidget(task);
		taskList.addBlock(block);
		return block;
	}

	@Override
	protected Widget buildToolbar() {
		if (!isExtended()) return null;
		ToolbarWidget toolbar = new ToolbarWidget();

		if (requirement.isDone() && !requirement.isClosed()) {
			toolbar.addButton("Close").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					requirement.setClosed(true);
					rebuild();
				}
			});
		}

		if (!requirement.isClosed()) {
			toolbar.addButton("Create new Task").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					Task task = requirement.createNewTask();
					TaskWidget block = addBlock(task);
					taskList.selectBlock(block);
					update();
				}
			});
		}

		return toolbar;
	}

	@Override
	protected String getBlockTitle() {
		return requirement.getLabel();
	}

	@Override
	protected AbstractImagePrototype getIcon16() {
		if (requirement.isDone()) return Img.bundle.storyDoneIcon16();
		return Img.bundle.storyIcon16();
	}

	@Override
	protected AbstractImagePrototype getIcon32() {
		if (requirement.isDone()) return Img.bundle.storyDoneIcon32();
		return Img.bundle.storyIcon32();
	}

	@Override
	public void delete() {
		ScrumGwtApplication.get().getProject().deleteRequirement(requirement);
	}

	@Override
	protected DropController createDropController() {
		return new BlockListDropController(this, ScrumGwtApplication.get().getWorkspace().getBacklog().list);
	}

	public Requirement getItem() {
		return requirement;
	}

}
