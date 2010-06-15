package scrum.client.admin;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.AAction;
import ilarkesto.gwt.client.AViewEditWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.common.FieldsWidget;
import scrum.client.workspace.Ui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class PasswordChangeWidget extends AViewEditWidget {

	private FieldsWidget fields;
	private TextBox currentPasswordBox;
	private TextBox newPasswordBox;
	private TextBox newPasswordRepeatBox;

	@Override
	protected Widget onViewerInitialization() {
		return new Label("*****");
	}

	@Override
	protected void onViewerUpdate() {}

	@Override
	protected Widget onEditorInitialization() {
		currentPasswordBox = new PasswordTextBox();
		newPasswordBox = new PasswordTextBox();
		newPasswordRepeatBox = new PasswordTextBox();

		fields = new FieldsWidget();
		fields.add("Current Password", currentPasswordBox);
		fields.add("New Password", newPasswordBox);
		fields.add("Repeat new Password", newPasswordRepeatBox);

		FlowPanel mainpanel = new FlowPanel();
		mainpanel.setWidth("100%");
		mainpanel.add(fields);
		ToolbarWidget toolbar = new ToolbarWidget();
		toolbar.addButton(new SaveChangesAction());
		toolbar.addButton(new CancelAction());
		mainpanel.add(toolbar.update());

		return mainpanel;
	}

	@Override
	protected void onEditorUpdate() {
		currentPasswordBox.setText("");
		newPasswordBox.setText("");
		newPasswordRepeatBox.setText("");
		fields.update();
	}

	@Override
	protected void onEditorSubmit() {
		String currentPassword = currentPasswordBox.getText();
		String newPassword = newPasswordBox.getText();
		String newPasswordRepeat = newPasswordRepeatBox.getText();
		if (currentPassword == null) {
			Scope.get().getComponent(Ui.class).getWorkspace().showError("old password is empty");
			return;
		} else if (newPassword == null) {
			Scope.get().getComponent(Ui.class).getWorkspace().showError("new password is empty");
			return;
		} else if (newPasswordRepeat == null) {
			Scope.get().getComponent(Ui.class).getWorkspace().showError("new password repeat is empty");
			return;
		} else if (!newPassword.equals(newPasswordRepeat)) {
			Scope.get().getComponent(Ui.class).getWorkspace().showError(
				"the new password repeat did not match the new password.");
			return;
		}
		new ChangePasswordServiceCall(currentPassword, newPassword).execute();
	}

	@Override
	public boolean isEditable() {
		return true;
	}

	class SaveChangesAction extends AAction {

		@Override
		public String getLabel() {
			return "Change Password";
		}

		@Override
		protected void onExecute() {
			submitEditor();
		}

	}

	class CancelAction extends AAction {

		@Override
		public String getLabel() {
			return "Cancel";
		}

		@Override
		protected void onExecute() {
			cancelEditor();
		}

	}

}
