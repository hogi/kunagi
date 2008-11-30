package scrum.client.sprint;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.editable.AEditableTextWidget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CurrentSprintWidget extends Composite {

	private VerticalPanel view;

	private Sprint sprint;

	public CurrentSprintWidget() {
		view = new VerticalPanel();
		view.setWidth("100%");

		initWidget(view);
	}

	public void update() {
		view.clear();
		view
				.add(new Label(
						"A sprint is an iteration of work during which an increment of product functionality is implemented. By the book, an iteration lasts 30 days. This is longer than in other agile methods to take into account the fact that a functional increment of product must be produced each sprint.\n"
								+ "\n"
								+ "The sprint starts with a one-day sprint planning meeting. Many daily Scrum meetings occur during the sprint (one per day). At the end of the sprint we have a sprint review meeting, followed by a sprint retrospective meeting.\n"
								+ "\n"
								+ "During the sprint, the team must not be interrupted with additional requests. Guaranteeing the team won't be interrupted allows it to make real commitments it can be expected to keep."));
		view.add(new HTML("<br />"));
		sprint = ScrumGwtApplication.get().getProject().getCurrentSprint();

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
		fieldsWidget.addField("Effort sum", new Label(sprint.getTaskEffortSumString()));
		view.add(fieldsWidget);

		view.add(new SprintStoryListWidget(this));
	}

	public Sprint getSprint() {
		return sprint;
	}

	class AssignSprintListener implements ClickListener {

		public void onClick(Widget sender) {
			sprint = ScrumGwtApplication.get().getProject().createNewSprint();
			update();
		}
	}

}
