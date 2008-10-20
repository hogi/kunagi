package scrum.client.project;

import scrum.client.common.ABlockWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.editable.AEditableListBoxWidget;
import scrum.client.common.editable.AEditableTextWidget;
import scrum.client.common.editable.AEditableTextareaWidget;
import scrum.client.dnd.BlockListDropController;
import scrum.client.img.Img;
import scrum.client.service.ScrumClient;
import scrum.client.workspace.WorkspaceWidget;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BacklogItemWidget extends ABlockWidget {

	private BacklogItem item;

	public BacklogItemWidget(BacklogItem item) {
		this.item = item;
	}

	@Override
	protected Widget buildContent() {
		if (!isExtended()) { return new Label(item.getSummary()); }

		ItemFieldsWidget fieldsWidget = new ItemFieldsWidget();
		fieldsWidget.addField("Label", new AEditableTextWidget() {

			@Override
			protected String getText() {
				return item.getLabel();
			}

			@Override
			protected void setText(String text) {
				item.setLabel(text);
				rebuild();
			}

		});
		fieldsWidget.addField("Description", new AEditableTextareaWidget() {

			@Override
			protected String getText() {
				return item.getDescription();
			}

			@Override
			protected void setText(String text) {
				item.setDescription(text);
				rebuild();
			}

		});
		fieldsWidget.addField("Test", new AEditableTextareaWidget() {

			@Override
			protected String getText() {
				return item.getTestDescription();
			}

			@Override
			protected void setText(String text) {
				item.setTestDescription(text);
				rebuild();
			}

		});
		// fieldsWidget.addField("Effort", new AEditableIntegerWidget() {
		//
		// @Override
		// protected Integer getValue() {
		// return item.getEffort();
		// }
		//
		// @Override
		// protected void setValue(Integer value) {
		// item.setEffort(value);
		// rebuild();
		// }
		//
		// });
		fieldsWidget.addField("Effort", new AEditableListBoxWidget() {

			@Override
			protected String getText() {
				return String.valueOf(item.getEffort());
			}

			@Override
			protected void setText(String value) {
				item.setEffort(Integer.parseInt(value));
				rebuild();
			}

			@Override
			protected String[] getItems() {
				return new String[] { "1", "2", "3", "5", "8", "13", "21" };

			}

		});
		return fieldsWidget;
	}

	@Override
	protected Widget buildToolbar() {
		if (!isExtended()) return null;
		VerticalPanel toolbar = new VerticalPanel();
		toolbar.setStyleName("Toolbar");

		Button deleteButton = new Button("Delete");
		deleteButton.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				ScrumClient.getProject().deleteBacklogItem(item);
				WorkspaceWidget.backlog.list.removeSelectedRow();
			}
		});
		toolbar.add(deleteButton);

		if (!item.isDone()) {
			Button solveButton = new Button("Done");
			solveButton.addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					item.setDone(true);
					rebuild();
				}
			});
			toolbar.add(solveButton);
		} else {
			Button unsolveButton = new Button("Undone");
			unsolveButton.addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					item.setDone(false);
					rebuild();
				}
			});
			toolbar.add(unsolveButton);
		}

		return toolbar;
	}

	@Override
	protected String getBlockTitle() {
		return item.getLabel();
	}

	@Override
	protected AbstractImagePrototype getIcon() {
		if (item.isDone()) return Img.bundle.backlogItemDoneIcon32();
		return Img.bundle.backlogItemIcon32();
	}

	@Override
	public void delete() {
		ScrumClient.getProject().deleteBacklogItem(item);
		WorkspaceWidget.backlog.list.remove(this);
	}

	@Override
	protected DropController getDropController() {
		return new BlockListDropController(this, WorkspaceWidget.backlog.list);
	}
}
