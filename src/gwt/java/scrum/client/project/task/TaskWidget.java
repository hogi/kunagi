package scrum.client.project.task;

import scrum.client.common.ABlockWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.editable.AEditableTextWidget;
import scrum.client.img.Img;
import scrum.client.service.Service;

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
			return new Label(task.getDescription());
		}

		// block is extended -> create an ItemFieldsWidget
		ItemFieldsWidget fieldsWidget = new ItemFieldsWidget();

		fieldsWidget.addField("State", new Label(task.getStateLabel()));

		fieldsWidget.addField("Description", new AEditableTextWidget() {

			@Override
			protected String getText() {
				return task.getDescription();
			}

			@Override
			protected void setText(String text) {
				task.setDescription(text);
			}

		});

		fieldsWidget.addField("Effort", new AEditableTextWidget() {

			@Override
			protected String getText() {
				return String.valueOf(task.getEffort());
			}

			@Override
			protected void setText(String text) {
				// TODO errorhandling - aka only int-field
				int effort = Integer.parseInt(text);
				task.setEffort(effort);
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
				task.setOwner(Service.getUser());
				// TODO aahhh....!!! -> rebuild or something
				// WorkspaceWidget.showPortal();
			}

		});
		toolbar.add(ownButton);

		Button deleteButton = new Button("Delete");
		deleteButton.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				Service.getProject().deleteTask(task);
				// TODO ???
				// WorkspaceWidget.tasks.list.removeSelectedRow();
			}

		});
		toolbar.add(deleteButton);

		return toolbar;
	}

	@Override
	protected String getBlockTitle() {
		return task.getDescription() + " (" + task.getStateLabel() + ")";
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
		throw new RuntimeException("Not implemented.");
		// TODO: reference task list
		// return new BlockListDropController(this, null);
	}
}
