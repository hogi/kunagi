package scrum.client.sprint;

import java.util.ArrayList;
import java.util.List;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListController;
import scrum.client.common.BlockListWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.editable.AEditableTextWidget;
import scrum.client.common.editable.AEditableTextareaWidget;
import scrum.client.project.Project;
import scrum.client.project.Requirement;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SprintBacklogWidget extends Composite {

	private BlockListWidget<RequirementInSprintWidget> requirementList;
	private FlowPanel view;
	private Label remainingWork;
	private Label begin;
	private Label end;

	private List<Requirement> previousRequirements = new ArrayList<Requirement>(0);

	public SprintBacklogWidget() {
		remainingWork = new Label();
		begin = new Label();
		end = new Label();
		requirementList = new BlockListWidget<RequirementInSprintWidget>(
				new BlockListController<RequirementInSprintWidget>() {

					@Override
					public void dataChanged(RequirementInSprintWidget block) {
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
		fieldsWidget.addField("Goal", new AEditableTextareaWidget() {

			@Override
			protected void setText(String text) {
				getSprint().setGoal(text);
			}

			@Override
			protected String getText() {
				return getSprint() == null ? null : getSprint().getGoal();
			}
		});
		fieldsWidget.addField("Begin", begin);
		fieldsWidget.addField("End", end);
		fieldsWidget.addField("Remaining Work", remainingWork);
		view.add(fieldsWidget);
		view.add(requirementList);
		initWidget(view);
	}

	public void update() {
		remainingWork.setText(getSprint().getTaskEffortSumString());
		begin.setText(getSprint().getBegin().toString());
		end.setText(getSprint().getEnd().toString());

		List<Requirement> requirements = getSprint().getRequirements();
		if (!requirements.equals(previousRequirements)) {
			requirementList.clear();
			for (Requirement requirement : requirements) {
				requirementList.addBlock(new RequirementInSprintWidget(requirement));
			}
			previousRequirements = requirements;
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
