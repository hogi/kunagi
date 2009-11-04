package scrum.client.project;

import ilarkesto.gwt.client.ADropdownViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ClientConstants;
import scrum.client.common.AScrumAction;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class RequirementEstimatedWorkWidget extends AWidget {

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
			setViewerText(requirement.getEstimatedWorkAsString());
		}

		@Override
		public boolean isEditable() {
			if (requirement.isInCurrentSprint()) return false;
			return super.isEditable();
		}

		@Override
		protected void onEditorUpdate() {
			setOptions(ClientConstants.EFFORT_ROW);
			Integer work = requirement.getEstimatedWork();
			setSelectedOption(work == null ? "" : work.toString());
		}

		@Override
		protected void onEditorSubmit() {
			String value = getSelectedOption();
			requirement.setEstimatedWork(value.length() == 0 ? null : Integer.parseInt(value));
		}
	}

}
