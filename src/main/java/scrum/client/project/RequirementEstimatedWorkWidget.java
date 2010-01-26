package scrum.client.project;

import ilarkesto.gwt.client.ADropdownViewEditWidget;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class RequirementEstimatedWorkWidget extends AScrumWidget {

	private Requirement requirement;
	private HorizontalPanel panel;
	private EstimatedWorkWidget estimatedWork;
	private ToolbarWidget toolbar;

	public RequirementEstimatedWorkWidget(Requirement requirement) {
		this.requirement = requirement;
	}

	@Override
	protected Widget onInitialization() {
		estimatedWork = new EstimatedWorkWidget();
		toolbar = new ToolbarWidget();

		panel = new HorizontalPanel();
		panel.setStyleName("RequirementEstimatedWorkWidget");
		panel.add(estimatedWork);
		panel.add(toolbar);
		return panel;
	}

	@Override
	protected void onUpdate() {
		estimatedWork.update();
		toolbar.clear();
		AScrumAction action = requirement.isDirty() ? new SetRequirementCleanAction(requirement)
				: new SetRequirementDirtyAction(requirement);
		ButtonWidget newButton = action.isExecutable() ? new ButtonWidget(action) : null;
		if (newButton != null) {
			toolbar.add(newButton);
		} else {
			toolbar.update();
		}
	}

	class EstimatedWorkWidget extends ADropdownViewEditWidget {

		@Override
		protected void onViewerUpdate() {
			setViewerText(requirement.getEstimatedWorkWithUnit());
		}

		@Override
		public boolean isEditable() {
			if (requirement.isInCurrentSprint()) return false;
			if (!getCurrentProject().isTeamMember(getCurrentUser())) return false;
			return true;
		}

		@Override
		protected void onEditorUpdate() {
			setOptions(Requirement.WORK_ESTIMATION_VALUES);
			String work = requirement.getEstimatedWorkAsString();
			setSelectedOption(work == null ? "" : work.toString());
		}

		@Override
		protected void onEditorSubmit() {
			String value = getSelectedOption();
			requirement.setEstimatedWork(value.length() == 0 ? null : Float.parseFloat(value));
		}
	}

}
