package scrum.client.project;

import ilarkesto.gwt.client.ADropdownViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ButtonWidget;
import scrum.client.ClientConstants;
import scrum.client.common.AScrumAction;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class RequirementEstimatedWorkWidget extends AWidget {

	private Requirement requirement;
	private HorizontalPanel panel;
	private EstimatedWorkWidget estimatedWork;
	private ButtonWidget currentButton;

	public RequirementEstimatedWorkWidget(Requirement requirement) {
		this.requirement = requirement;
	}

	@Override
	protected Widget onInitialization() {

		estimatedWork = new EstimatedWorkWidget();

		panel = new HorizontalPanel();
		panel.setWidth("100%");
		panel.add(estimatedWork);
		return panel;
	}

	@Override
	protected void onUpdate() {
		estimatedWork.update();
		AScrumAction action = requirement.isDirty() ? new SetRequirementCleanAction(requirement)
				: new SetRequirementDirtyAction(requirement);
		ButtonWidget newButton = action.isExecutable() ? new ButtonWidget(action) : null;
		if (currentButton != null) panel.remove(currentButton);
		if (newButton != null) {
			panel.add(newButton);
			newButton.update();
			currentButton = newButton;
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
			return true;
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
