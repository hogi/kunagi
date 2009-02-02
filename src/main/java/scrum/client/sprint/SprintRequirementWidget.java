package scrum.client.sprint;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
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

	private Requirement story;
	private TaskListWidget taskListWidget;

	public SprintRequirementWidget(Requirement story) {
		this.story = story;
	}

	@Override
	protected Widget buildContent() {
		if (!isExtended()) { return new Label(story.getSprintBacklogSummary()); }

		ItemFieldsWidget fieldsWidget = new ItemFieldsWidget();
		fieldsWidget.addField("Description", new Label(story.getDescription()));
		fieldsWidget.addField("Test", new Label(story.getTestDescription()));
		fieldsWidget.addField("Task effort sum", new Label(story.getTaskEffortSumString()));

		FlowPanel panel = new FlowPanel();
		panel.add(fieldsWidget);

		taskListWidget = new TaskListWidget(this);
		panel.add(taskListWidget);

		// panel.add(new TaskListWidget(item));

		return panel;
	}

	@Override
	protected Widget buildToolbar() {
		if (!isExtended()) return null;
		ToolbarWidget toolbar = new ToolbarWidget();

		if (story.isDone() && !story.isClosed()) {
			toolbar.addButton("Close").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					story.setClosed(true);
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

		if (!story.isClosed()) {
			toolbar.addButton("Create new Task").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					Task task = story.createNewTask();
					taskListWidget.update(task);
				}
			});
		}

		return toolbar;
	}

	@Override
	protected String getBlockTitle() {
		return story.getLabel();
	}

	@Override
	protected AbstractImagePrototype getIcon16() {
		if (story.isDone()) return Img.bundle.storyDoneIcon16();
		return Img.bundle.storyIcon16();
	}

	@Override
	protected AbstractImagePrototype getIcon32() {
		if (story.isDone()) return Img.bundle.storyDoneIcon32();
		return Img.bundle.storyIcon32();
	}

	@Override
	public void delete() {
		ScrumGwtApplication.get().getProject().deleteRequirement(story);
	}

	@Override
	protected DropController createDropController() {
		return new BlockListDropController(this, WorkspaceWidget.backlog.list);
	}

	public Requirement getItem() {
		return story;
	}

	public void taskDataChanged() {
		controller.dataChanged(this);
	}
}
