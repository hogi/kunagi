package scrum.client.sprint;

import ilarkesto.gwt.client.ATextWidget;
import ilarkesto.gwt.client.ToolbarWidget;

import java.util.ArrayList;
import java.util.List;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.FieldsWidget;
import scrum.client.img.Img;
import scrum.client.project.Requirement;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RequirementInSprintWidget extends AExtensibleBlockWidget {

	private Requirement requirement;
	private SprintBacklogWidget autoUpdateWidget; // widget to update, when fields modified

	private BlockListWidget<TaskWidget> taskList;
	private Label summary;
	private FlowPanel panel;
	private FieldsWidget fields;

	private List<Task> previousTasks = new ArrayList<Task>(0);

	public RequirementInSprintWidget(Requirement requirement, SprintBacklogWidget autoUpdateWidget) {
		this.requirement = requirement;
		this.autoUpdateWidget = autoUpdateWidget;
	}

	@Override
	protected void onCollapsedInitialization() {
		summary = new Label();
	}

	@Override
	protected void onCollapsedUpdate() {
		setBlockTitle(requirement.getLabel());
		setIcon(requirement.isDone() ? Img.bundle.done32() : Img.bundle.requirement32());
		summary.setText(requirement.getSprintBacklogSummary());
		setContent(summary);
		setToolbar(null);
	}

	@Override
	protected void onExtendedInitialization() {

		fields = new FieldsWidget();
		fields.setAutoUpdateWidget(autoUpdateWidget);
		fields.add("Description", new ATextWidget() {

			@Override
			protected void onUpdate() {
				setText(requirement.getDescription());
			}
		});

		fields.add("Test", new ATextWidget() {

			@Override
			protected void onUpdate() {
				setText(requirement.getTestDescription());
			}
		});

		fields.add("Remainig Work", new ATextWidget() {

			@Override
			protected void onUpdate() {
				setText(requirement.getRemainingWorkAsString());
			}
		});

		taskList = new BlockListWidget<TaskWidget>();

		panel = new FlowPanel();
		panel.add(fields);
		panel.add(taskList);
	}

	@Override
	protected void onExtendedUpdate() {
		setBlockTitle(requirement.getLabel());
		setIcon(requirement.isDone() ? Img.bundle.done32() : Img.bundle.requirement32());
		fields.update();

		taskList.update();

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

		setContent(panel);
		setToolbar(createToolbar());
	}

	public Requirement getRequirement() {
		return requirement;
	}

	private TaskWidget addBlock(Task task) {
		TaskWidget block = new TaskWidget(task, autoUpdateWidget);
		taskList.addBlock(block);
		return block;
	}

	protected Widget createToolbar() {
		ToolbarWidget toolbar = new ToolbarWidget();

		if (requirement.isDone() && !requirement.isClosed()) {
			toolbar.addButton("Close").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					requirement.setClosed(true);
					autoUpdateWidget.update();
				}
			});
		}

		if (!requirement.isClosed()) {
			toolbar.addButton("Create new Task").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					Task task = requirement.createNewTask();
					TaskWidget block = addBlock(task);
					taskList.selectBlock(block);
					autoUpdateWidget.update();
				}
			});
		}
		return toolbar;
	}

	@Override
	public AbstractImagePrototype getIcon16() {
		if (requirement.isDone()) return Img.bundle.requirement16();
		return Img.bundle.requirement16();
	}

	@Override
	public void delete() {
		ScrumGwtApplication.get().getProject().deleteRequirement(requirement);
	}

	public Requirement getItem() {
		return requirement;
	}

}
