package scrum.client.sprint;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.AWidget;

import java.util.ArrayList;
import java.util.List;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListWidget;
import scrum.client.common.FieldsWidget;
import scrum.client.common.GroupWidget;
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

	private FieldsWidget fieldsWidget;

	private List<Requirement> previousRequirements = new ArrayList<Requirement>(0);

	@Override
	protected Widget onInitialization() {

		remainingWork = new Label();
		begin = new Label();
		end = new Label();
		requirementList = new BlockListWidget<RequirementInSprintWidget>();

		view = new FlowPanel();
		fieldsWidget = new FieldsWidget();

		fieldsWidget.add("Label", new ATextViewEditWidget() {

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

		fieldsWidget.add("Goal", new ARichtextViewEditWidget() {

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
		fieldsWidget.add("Begin", begin);
		fieldsWidget.add("End", end);
		fieldsWidget.add("Remaining Work", remainingWork);
		view.add(fieldsWidget);
		view.add(requirementList);

		return new GroupWidget("Sprint Backlog", view);
	}

	@Override
	protected void onUpdate() {
		fieldsWidget.update();
		requirementList.update();

		remainingWork.setText(getSprint().getTaskEffortSumString());
		begin.setText(getSprint().getBegin().toString());
		end.setText(getSprint().getEnd().toString());

		List<Requirement> requirements = getSprint().getRequirements();
		if (!requirements.equals(previousRequirements)) {
			requirementList.clear();
			for (Requirement requirement : requirements) {
				requirementList.addBlock(new RequirementInSprintWidget(requirement, this));
			}
			previousRequirements = requirements;
		}

	}

	public Widget selectRequirement(Requirement r) {
		update();

		for (RequirementInSprintWidget rw : requirementList) {
			if (r.getId().equals(rw.getRequirement().getId())) {
				requirementList.selectBlock(rw);
				return rw;
			}
		}

		return null;
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
