package scrum.client.sprint;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.AWidget;

import java.util.ArrayList;
import java.util.List;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListController;
import scrum.client.common.BlockListWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.project.Project;
import scrum.client.project.Requirement;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SprintBacklogWidget extends AWidget {

	private BlockListWidget<RequirementInSprintWidget> requirementList;
	private FlowPanel view;
	private Label remainingWork;
	private Label begin;
	private Label end;

	private ATextViewEditWidget label;
	private ARichtextViewEditWidget goal;

	private List<Requirement> previousRequirements = new ArrayList<Requirement>(0);

	@Override
	protected Widget onInitialization() {

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

		label = fieldsWidget.addField("Label", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				if (getSprint() != null) setViewerText(getSprint().getLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(getSprint().getLabel());
			}

			@Override
			protected void onEditorSubmit() {
				getSprint().setLabel(getEditorText());
			}
		});

		goal = fieldsWidget.addField("Goal", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				if (getSprint() != null) setViewerText(getSprint().getGoal());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(getSprint().getGoal());
			}

			@Override
			protected void onEditorSubmit() {
				getSprint().setGoal(getEditorText());
			}
		});
		fieldsWidget.addField("Begin", begin);
		fieldsWidget.addField("End", end);
		fieldsWidget.addField("Remaining Work", remainingWork);
		view.add(fieldsWidget);
		view.add(requirementList);
		return view;
	}

	@Override
	protected void onUpdate() {
		label.update();
		goal.update();
		requirementList.update();

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
