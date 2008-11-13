package scrum.client.sprint;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.editable.AEditableTextWidget;
import scrum.client.img.Img;
import scrum.client.project.BacklogItem;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SprintWidget extends ABlockWidget {

	private final Sprint sprint;

	public SprintWidget(Sprint sprint) {
		this.sprint = sprint;
	}

	@Override
	protected Widget buildContent() {
		if (!isExtended()) {
			// block is not extended -> return only a label with the summary
			return new Label(sprint.getLabel());
		}

		// block is extended -> create an ItemFieldsWidget
		ItemFieldsWidget fieldsWidget = new ItemFieldsWidget();
		fieldsWidget.setWidth("100%");
		fieldsWidget.addField("Label", new AEditableTextWidget() {

			@Override
			protected String getText() {
				return sprint.getLabel();
			}

			@Override
			protected void setText(String text) {
				sprint.setLabel(text);
				rebuild();
			}

		});

		fieldsWidget.addField("State", new Label(sprint.getStateLabel()));

		// VerticalPanel backlogpanel = new VerticalPanel();
		// fieldsWidget.addField("Backlogitems", backlogpanel);
		// for (BacklogItem backlogItem : sprint.getBacklogItems()) {
		// Label label = new Label(backlogItem.getLabel());
		// label.addClickListener(new ClickListener() {
		// public void onClick(Widget sender) {
		//
		// }
		// });
		// backlogpanel.add(label);
		// }

		FlexTable ft = new FlexTable();

		int row = 0;
		for (BacklogItem backlogItem : sprint.getBacklogItems()) {
			Label label = new Label(backlogItem.getLabel());
			ft.setWidget(row, 0, label);
			Button remove = new Button("Remove");
			remove.setSize("50", "26");
			ft.setWidget(row, 1, remove);

			row++;
		}
		fieldsWidget.addField("Backlogitems", ft);

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

		Button addSprintButton = new Button("Assign Backlogitem");
		// addSprintButton.addClickListener(new AssignClickListener());
		final SuggestBox suggest = new SuggestBox(getOracle());
		toolbar.add(suggest);
		addSprintButton.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				assignBacklogItem(suggest.getText());
			}
		});
		toolbar.add(addSprintButton);

		return toolbar;
	}

	@Override
	protected String getBlockTitle() {
		return sprint.getLabel() + " (" + sprint.getStateLabel() + ")";
	}

	@Override
	protected AbstractImagePrototype getIcon() {
		return Img.bundle.sprintIcon32();
	}

	// TODO wird nicht funktionieren (bugfrei :-)) da das label kein indiz für equals ist.
	private MultiWordSuggestOracle getOracle() {
		MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();

		project: for (BacklogItem projectBacklogItem : ScrumGwtApplication.get().getProject().getBacklogItems()) {
			for (BacklogItem sprintBacklogItem : sprint.getBacklogItems()) {
				if (projectBacklogItem.getLabel().equals(sprintBacklogItem.getLabel())) {
					continue project;
				}
			}
			oracle.add(projectBacklogItem.getLabel());
		}

		return oracle;
	}

	private void assignBacklogItem(String label) {
		if ("".equals(label)) return;

		for (BacklogItem backlogItem : ScrumGwtApplication.get().getProject().getBacklogItems()) {
			if (backlogItem.getLabel().equals(label) == false) continue;

			sprint.getBacklogItems().add(backlogItem);
			rebuild();
			break;
		}
	}

	// --- TASTING - TASTING - TASTING --- STILL IN DEV ---------//

	private class AssignClickListener implements ClickListener {

		DialogBox box = new DialogBox();
		SuggestBox suggest;

		public AssignClickListener() {
			box.setPopupPosition(SprintWidget.this.getAbsoluteLeft() + 10, SprintWidget.this.getAbsoluteTop() + 10);
			box.setAnimationEnabled(true);
			// box.setSize("300", "200");
			VerticalPanel mainpanel = new VerticalPanel();
			suggest = new SuggestBox(getOracle());
			mainpanel.add(suggest);

			Button addButton = new Button("Add");
			addButton.addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					box.hide();
				}

			});
			mainpanel.add(addButton);
			box.add(mainpanel);
		}

		public void onClick(Widget sender) {
			box.show();
		}

		private MultiWordSuggestOracle getOracle() {
			MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
			for (BacklogItem backlogItem : ScrumGwtApplication.get().getProject().getBacklogItems()) {
				oracle.add(backlogItem.getLabel());
			}
			return oracle;
		}
	}

	@Override
	protected DropController getDropController() {
		return null;
		// throw new RuntimeException("Not implemented.");

		// TODO: reference sprint list
		// return new BlockListDropController(this, null);
	}

	@Override
	public void delete() {
		throw new RuntimeException("Not implemented.");
		// TODO: delete item and remove block from gui
	}
}
