package scrum.client.project;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.StyleSheet;
import scrum.client.common.editable.AEditableListBoxWidget;
import scrum.client.common.editable.AEditableTextWidget;
import scrum.client.common.editable.AEditableTextareaWidget;
import scrum.client.dnd.BlockListDropController;
import scrum.client.img.Img;
import scrum.client.workspace.WorkspaceWidget;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BacklogItemWidget extends ABlockWidget {

	private Story item;

	public BacklogItemWidget(Story item) {
		this.item = item;
	}

	@Override
	protected Widget buildContent() {
		if (!isExtended()) { return new Label(item.getProductBacklogSummary()); }

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

		fieldsWidget.addField("Effort", new AEditableListBoxWidget() {

			@Override
			protected String getText() {
				Integer effort = item.getEffort();
				return effort == null ? "No effort estimated." : effort.toString() + " "
						+ item.getProject().getEffortUnit();
			}

			@Override
			protected String[] getSelectableValues() {
				return new String[] { "", "1", "2", "3", "5", "8", "13", "21" };
			}

			@Override
			protected String getSelectedValue() {
				Integer effort = item.getEffort();
				return effort == null ? "" : effort.toString();
			}

			@Override
			protected void setValue(String value) {
				item.setEffort(value.length() == 0 ? null : Integer.parseInt(value));
				rebuild();
			}

		});

		return fieldsWidget;
	}

	@Override
	protected Widget buildToolbar() {
		if (!isExtended()) return null;
		VerticalPanel toolbar = new VerticalPanel();
		toolbar.setStyleName(StyleSheet.TOOLBAR);

		Button deleteButton = new Button("Delete");
		deleteButton.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				ScrumGwtApplication.get().getProject().deleteBacklogItem(item);
				WorkspaceWidget.backlog.list.removeSelectedRow();
			}
		});
		toolbar.add(deleteButton);

		if (!item.isClosed() && item.isDone()) {
			Button unsolveButton = new Button("Close");
			unsolveButton.addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					// item.setDone(false);
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
		if (item.isClosed()) return Img.bundle.backlogItemDoneIcon32();
		return Img.bundle.backlogItemIcon32();
	}

	@Override
	public void delete() {
		ScrumGwtApplication.get().getProject().deleteBacklogItem(item);
		WorkspaceWidget.backlog.list.remove(this);
	}

	@Override
	protected DropController createDropController() {
		return new BlockListDropController(this, WorkspaceWidget.backlog.list);
	}

	public Story getItem() {
		return item;
	}
}
