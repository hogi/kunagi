package scrum.client.sprint;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.editable.AEditableIntegerWidget;
import scrum.client.common.editable.AEditableListBoxWidget;
import scrum.client.common.editable.AEditableTextWidget;
import scrum.client.img.Img;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TaskWidget extends ABlockWidget {

	private Task task;

	public TaskWidget(Task task) {
		this.task = task;
	}

	@Override
	protected Widget buildContent() {
		if (!isExtended()) {
			// block is not extended -> return only a label with the summary
			return new Label(task.getLabel());
		}

		// block is extended -> create an ItemFieldsWidget
		ItemFieldsWidget fieldsWidget = new ItemFieldsWidget();

		// fieldsWidget.addField("State", new Label(task.getStateLabel()));

		fieldsWidget.addField("State", new AEditableListBoxWidget() {

			@Override
			protected String getText() {
				return String.valueOf(task.getState());
			}

			@Override
			protected void setText(String state) {
				task.setState(state);
				rebuild();
			}

			@Override
			protected String[] getItems() {
				return new String[] { Task.STATE_OPEN, Task.STATE_OWNED, Task.STATE_TEST, Task.STATE_FINISHED,
						Task.STATE_CLOSED };

			}

		});

		fieldsWidget.addField("Description", new AEditableTextWidget() {

			@Override
			protected String getText() {
				return task.getLabel();
			}

			@Override
			protected void setText(String text) {
				task.setLabel(text);
			}

		});

		fieldsWidget.addField("Effort", new AEditableIntegerWidget() {

			@Override
			protected Integer getValue() {
				return task.getEffort();
			}

			@Override
			protected void setValue(Integer value) {
				task.setEffort(value);
			}

		});

		fieldsWidget.addField("Owner", new Label(task.getOwner() == null ? "<empty>" : task.getOwner().getName()));

		return fieldsWidget;
	}

	@Override
	protected Widget buildToolbar() {
		if (!isExtended()) {
			// block is not extended -> no toolbar
			return null;
		}

		// block is extended -> create toolbar with buttons
		VerticalPanel toolbar = new VerticalPanel();
		toolbar.setStyleName("Toolbar");

		Button ownButton = new Button("Own");
		ownButton.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				task.setOwner(ScrumGwtApplication.get().getUser());
				// TODO aahhh....!!! -> rebuild or something
				// WorkspaceWidget.showPortal();
			}

		});
		toolbar.add(ownButton);

		Button deleteButton = new Button("Delete");
		deleteButton.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				delete();
			}

		});
		toolbar.add(deleteButton);

		return toolbar;
	}

	@Override
	protected String getBlockTitle() {
		return task.getLabel() + " (" + task.getState() + ")";
	}

	@Override
	protected AbstractImagePrototype getIcon() {
		return Img.bundle.taskIcon32();
	}

	// --- actions

	private void assign() {

	}

	private void solve() {

	}

	@Override
	protected DropController getDropController() {
		return null;
		// TODO: reference task list
		// return new BlockListDropController(this, null);
	}

	@Override
	public void delete() {

		task.getBacklogItem().deleteTask(task);
		task.getTaskListWidget().update();

	}
}
