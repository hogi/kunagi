package scrum.client.sprint;

import scrum.client.common.ABlockWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.editable.AEditableTextWidget;
import scrum.client.img.Img;
import scrum.client.project.BacklogItem;
import scrum.client.workspace.WorkspaceWidget;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
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
		
		VerticalPanel backlogpanel = new VerticalPanel();
		fieldsWidget.addField("Backlogitems", backlogpanel);
		for (BacklogItem backlogItem : sprint.getBacklogItems()) {
			Label label = new Label(backlogItem.getLabel());
			label.addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					// TODO
				}
				
			});
			backlogpanel.add(label);
		}
		
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
		addSprintButton.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				// TODO show backlogitem selection
				WorkspaceWidget.showBacklog();
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
		//if (impediment.isSolved()) return Img.bundle.impedimentSolvedIcon32();
		return Img.bundle.impedimentIcon32();
	}
	
}
