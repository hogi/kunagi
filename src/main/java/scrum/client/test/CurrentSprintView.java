package scrum.client.test;

import java.util.List;

import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.ListProvider;
import scrum.client.common.editable.AEditableTextWidget;
import scrum.client.project.BacklogItem;
import scrum.client.project.BacklogItemListWidget;
import scrum.client.service.ScrumClient;
import scrum.client.sprint.Sprint;
import scrum.client.sprint.Sprint.State;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CurrentSprintView extends Composite {

	private VerticalPanel view;

	private Sprint sprint;

	public CurrentSprintView() {
		view = new VerticalPanel();
		view.setWidth("100%");

		initWidget(view);
	}

	public void update() {
		sprint = ScrumClient.getProject().getCurrentSprint();

		if (sprint == null) {
			Button assignSprintButton = new Button("Select current sprint (tmp: create new)");
			assignSprintButton.addClickListener(new AssignSprintListener());

			view.add(assignSprintButton);

			return;
		}

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
				// rebuild();
			}

		});

		int effortOverall = 0;
		for (BacklogItem backlogItem : sprint.getBacklogItems()) {
			if (backlogItem.getEffort() == null) continue;
			effortOverall += backlogItem.getEffort().intValue();
		}
		fieldsWidget.addField("Effort overall", new Label(String.valueOf(effortOverall)));

		view.add(fieldsWidget);

		view.add(new BacklogItemListWidget(new ListProvider() {

			public List<BacklogItem> getList() {
				return sprint.getBacklogItems();
			}

		}));
	}

	class AssignSprintListener implements ClickListener {

		public void onClick(Widget sender) {
			sprint = ScrumClient.getProject().createNewSprint("New Sprint");
			sprint.setState(State.Development);

			update();
		}
	}

}
