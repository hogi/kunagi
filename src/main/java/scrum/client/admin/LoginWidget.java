package scrum.client.admin;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ApplicationInfo;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.FieldsWidget;
import scrum.client.common.GroupWidget;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginWidget extends AWidget implements LoginDataProvider {

	private Label errorMessage;
	private TextBox username;
	private PasswordTextBox password;

	@Override
	protected Widget onInitialization() {
		errorMessage = new Label();
		username = new TextBox();
		username.setWidth("150px");
		username.addKeyPressHandler(new InputKeyHandler());
		password = new PasswordTextBox();
		password.setWidth("150px");
		password.addKeyPressHandler(new InputKeyHandler());

		ToolbarWidget toolbar = new ToolbarWidget(true);
		toolbar.addButton(new LoginAction(this));

		FieldsWidget fieldsWidget = new FieldsWidget();
		fieldsWidget.addWidget("Username", username);
		fieldsWidget.addWidget("Password", password);
		fieldsWidget.addWidget(null, toolbar.update());

		FlowPanel panel = new FlowPanel();
		panel.add(errorMessage);
		panel.add(fieldsWidget);

		return Gwt.createDiv("LoginWidget", new GroupWidget("Login", panel));
	}

	public void setFailed() {
		errorMessage.setStyleName("LoginWidget-errorMessage");
		errorMessage.setText("Login failed. Try again.");
	}

	@Override
	protected void onUpdate() {
		ApplicationInfo applicationInfo = ScrumGwtApplication.get().getApplicationInfo();
		if (applicationInfo != null && applicationInfo.isDevelopmentStage()) {
			username.setText("duke");
			password.setText("geheim");
		}
		username.setFocus(true);
	}

	public String getUsername() {
		return username.getText();
	}

	public String getPassword() {
		return password.getText();
	}

	class InputKeyHandler implements KeyPressHandler {

		public void onKeyPress(KeyPressEvent event) {
			if (event.getCharCode() != KeyCodes.KEY_ENTER) return;
			if (Gwt.isEmpty(getUsername())) {
				username.setFocus(true);
				return;
			}
			if (Gwt.isEmpty(getPassword())) {
				password.setFocus(true);
				return;
			}
			new LoginAction(LoginWidget.this).execute();
		}

	}

}
