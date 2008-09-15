package scrum.client.project;

import scrum.client.common.ABlockWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.editable.AEditableTextWidget;
import scrum.client.common.editable.AEditableTextareaWidget;
import scrum.client.img.Img;
import scrum.client.service.Service;
import scrum.client.workspace.WorkspaceWidget;

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
		if (!isExtended()) { return new Label(item.getLabel()); }

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
				Service.getProject().deleteBacklogItem(item);
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

}
