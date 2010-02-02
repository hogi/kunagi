package scrum.client.admin;

import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ApplicationInfo;
import scrum.client.common.AScrumWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginWidget extends AScrumWidget implements LoginDataProvider {

	private Label errorMessage;
	private TextBox username;
	private PasswordTextBox password;

	@Override
	protected Widget onInitialization() {
		errorMessage = new Label();
		username = new TextBox();
		username.setName("username");
		username.setWidth("150px");
		username.addKeyPressHandler(new InputKeyHandler());
		password = new PasswordTextBox();
		password.setName("password");
		password.setWidth("150px");
		password.addKeyPressHandler(new InputKeyHandler());

		ToolbarWidget toolbar = new ToolbarWidget();
		toolbar.addButton(new LoginAction(this));

		TableBuilder tb = new TableBuilder();
		tb.setCellPadding(2);
		tb.setWidth(null);

		tb.addRow(errorMessage, 2);
		tb.addFieldRow("Username", username);
		tb.addFieldRow("Password", password);
		tb.addFieldRow("", new ButtonWidget(new LoginAction(this)), 2);

		PagePanel page = new PagePanel();
		page.addHeader("Login");
		page.addSection(Gwt.createCenterer(tb.createTable()));

		return page;
	}

	public void setFailed() {
		errorMessage.setStyleName("LoginWidget-errorMessage");
		errorMessage.setText("Login failed. Try again.");
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		ApplicationInfo applicationInfo = cm.getApp().getApplicationInfo();
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
