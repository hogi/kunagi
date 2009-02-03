package scrum.client.sprint;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListController;
import scrum.client.common.BlockListWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.editable.AEditableTextWidget;
import scrum.client.project.Project;
import scrum.client.project.Requirement;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CurrentSprintWidget extends Composite {

	private BlockListWidget<SprintRequirementWidget> requirementList;
	private FlowPanel view;
	private Label totalEffort;
	private Label begin;
	private Label end;

	public CurrentSprintWidget() {
		totalEffort = new Label();
		begin = new Label();
		end = new Label();
		requirementList = new BlockListWidget<SprintRequirementWidget>(
				new BlockListController<SprintRequirementWidget>() {

					@Override
					public void dataChanged(SprintRequirementWidget block) {
						update();
					}
				});

		view = new FlowPanel();
		ItemFieldsWidget fieldsWidget = new ItemFieldsWidget();
		fieldsWidget.addField("Label", new AEditableTextWidget() {

			@Override
			protected String getText() {
				return getSprint() == null ? null : getSprint().getLabel();
			}

			@Override
			protected void setText(String text) {
				getSprint().setLabel(text);
			}

		});
		fieldsWidget.addField("Begin", begin);
		fieldsWidget.addField("End", end);
		fieldsWidget.addField("Total Effort", totalEffort);
		view.add(fieldsWidget);
		view.add(requirementList);
		initWidget(view);
	}

	public void update() {

		totalEffort.setText(getSprint().getTaskEffortSumString());
		begin.setText(getSprint().getBegin().toString());
		end.setText(getSprint().getEnd().toString());

		requirementList.clear();
		for (Requirement item : getSprint().getRequirements()) {
			requirementList.addBlock(new SprintRequirementWidget(item));
		}
	}

	private Sprint getSprint() {
		Project project = ScrumGwtApplication.get().getProject();
		if (project == null) return null;
		return project.getCurrentSprint();
	}

	class AssignSprintListener implements ClickListener {

		public void onClick(Widget sender) {
			update();
		}
	}

}
