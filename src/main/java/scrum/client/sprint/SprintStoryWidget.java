package scrum.client.sprint;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.StyleSheet;
import scrum.client.dnd.BlockListDropController;
import scrum.client.img.Img;
import scrum.client.project.Story;
import scrum.client.workspace.WorkspaceWidget;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SprintStoryWidget extends ABlockWidget {

	private Story story;
	private TaskListWidget taskListWidget;

	public SprintStoryWidget(Story story) {
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
		VerticalPanel toolbar = new VerticalPanel();
		toolbar.setStyleName(StyleSheet.TOOLBAR);

		if (story.isDone() && !story.isClosed()) {
			Button closeButton = new Button("Close");
			closeButton.addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					story.setClosed(true);
					rebuild();
				}
			});
		}

		Button removeButton = new Button("Remove");
		removeButton.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
			// ScrumGwtApplication.get().getProject().deleteBacklogItem(item);
			// WorkspaceWidget.backlog.list.removeSelectedRow();
			}
		});
		toolbar.add(removeButton);

		if (!story.isClosed()) {
			Button newTaskButton = new Button("Create Task");
			newTaskButton.addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					Task task = story.createNewTask();
					taskListWidget.update(task);
				}
			});
			toolbar.add(newTaskButton);
		}

		return toolbar;
	}

	@Override
	protected String getBlockTitle() {
		return story.getLabel();
	}

	@Override
	protected AbstractImagePrototype getIcon() {
		if (story.isDone()) return Img.bundle.storyDoneIcon32();
		return Img.bundle.storyIcon32();
	}

	@Override
	public void delete() {
		ScrumGwtApplication.get().getProject().deleteStory(story);
		WorkspaceWidget.backlog.list.remove(this);
	}

	@Override
	protected DropController createDropController() {
		return new BlockListDropController(this, WorkspaceWidget.backlog.list);
	}

	public Story getItem() {
		return story;
	}

	public void taskDataChanged() {
		controller.dataChanged(this);
	}
}
