package scrum.client.admin;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.FieldsWidget;
import scrum.client.common.GroupWidget;
import scrum.client.test.WidgetsTesterWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginWidget extends AWidget implements LoginDataProvider {

	private TextBox username;
	private PasswordTextBox password;

	@Override
	protected Widget onInitialization() {
		username = new TextBox();
		username.addKeyPressHandler(new InputKeyHandler());
		password = new PasswordTextBox();
		password.addKeyPressHandler(new InputKeyHandler());

		if (ScrumGwtApplication.get().isDevelopmentMode()) {
			username.setText("duke");
			password.setText("geheim");
		}

		ToolbarWidget toolbar = new ToolbarWidget(true);
		toolbar.addButton(new LoginAction(this));

		FieldsWidget fieldsWidget = new FieldsWidget();
		fieldsWidget.addWidget("Username", username);
		fieldsWidget.addWidget("Password", password);
		fieldsWidget.addWidget(null, toolbar.update());

		SimplePanel wrapper = new SimplePanel();
		wrapper.setStyleName("LoginWidget");
		wrapper.setWidget(new GroupWidget("Login", fieldsWidget));

		if (GWT.isScript()) { return wrapper; }

		FlowPanel test = new FlowPanel();
		test.add(wrapper);
		test.add(new WidgetsTesterWidget().update());

		return test;

	}

	@Override
	protected void onUpdate() {
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
