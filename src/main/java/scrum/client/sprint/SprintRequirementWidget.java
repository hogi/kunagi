package scrum.client.sprint;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockListController;
import scrum.client.common.BlockListWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.ToolbarWidget;
import scrum.client.dnd.BlockListDropController;
import scrum.client.img.Img;
import scrum.client.project.Requirement;
import scrum.client.workspace.WorkspaceWidget;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SprintRequirementWidget extends ABlockWidget {

	private Requirement requirement;
	private BlockListWidget<TaskWidget> taskList;

	private Label taskEffortSum;

	public SprintRequirementWidget(Requirement story) {
		this.requirement = story;
		taskEffortSum = new Label();
	}

	@Override
	protected Widget buildContent() {
		if (!isExtended()) { return new Label(requirement.getSprintBacklogSummary()); }

		ItemFieldsWidget fieldsWidget = new ItemFieldsWidget();
		fieldsWidget.addField("Description", new Label(requirement.getDescription()));
		fieldsWidget.addField("Test", new Label(requirement.getTestDescription()));
		fieldsWidget.addField("Task effort sum", taskEffortSum);

		FlowPanel panel = new FlowPanel();
		panel.add(fieldsWidget);

		taskList = new BlockListWidget<TaskWidget>(new BlockListController<TaskWidget>() {

			@Override
			public void dataChanged(TaskWidget block) {
				update();
				controller.dataChanged(SprintRequirementWidget.this);
			}
		});
		panel.add(taskList);

		update();

		return panel;
	}

	public void update() {
		taskEffortSum.setText(requirement.getTaskEffortSumString());

		taskList.clear();
		for (Task task : requirement.getTasks()) {
			TaskWidget block = new TaskWidget(task);
			taskList.addBlock(block);
		}
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

		toolbar.addButton("Remove").addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
			// ScrumGwtApplication.get().getProject().deleteBacklogItem(item);
			// WorkspaceWidget.backlog.list.removeSelectedRow();
			}
		});

		if (!requirement.isClosed()) {
			toolbar.addButton("Create new Task").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					Task task = requirement.createNewTask();
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
		return new BlockListDropController(this, WorkspaceWidget.backlog.list);
	}

	public Requirement getItem() {
		return requirement;
	}

}
