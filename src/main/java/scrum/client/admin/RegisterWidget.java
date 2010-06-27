package scrum.client.admin;

import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.ScrumGwt;
import scrum.client.common.AScrumWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class RegisterWidget extends AScrumWidget implements RegisterDataProvider {

	private Label message;

	private TextBox username;
	private PasswordTextBox password;
	private TextBox email;

	@Override
	protected Widget onInitialization() {
		message = new Label();

		username = new TextBox();
		username.setName("username");
		username.setWidth("150px");
		username.addKeyPressHandler(new InputKeyHandler());

		email = new TextBox();
		email.setName("email");
		email.setWidth("150px");
		email.addKeyPressHandler(new InputKeyHandler());

		password = new PasswordTextBox();
		password.setName("password");
		password.setWidth("150px");
		password.addKeyPressHandler(new InputKeyHandler());

		TableBuilder fields = ScrumGwt.createFieldTable();
		fields.setWidth(null);

		fields.addRow(message, 2);
		fields.addFieldRow("Username", username);
		fields.addFieldRow("E-Mail", email);
		fields.addFieldRow("Password", password);
		fields.addFieldRow("", new ButtonWidget(new RegisterAction(this)), 2);

		Widget content = fields.createTable();

		PagePanel page = new PagePanel();
		page.addHeader("Register");
		page.addSection(Gwt.createCenterer(content));

		return page;
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		username.setFocus(true);
	}

	public void clear() {
		username.setText(null);
		password.setText(null);
		email.setText(null);
	}

	public String getEmail() {
		return email.getText();
	}

	public String getPassword() {
		return password.getText();
	}

	public String getUsername() {
		return username.getText();
	}

	public void setFailed(String message) {
		this.message.setStyleName("LoginWidget-errorMessage");
		this.message.setText(message);
	}

	public void setSuccessful(String message) {
		this.message.setStyleName("LoginWidget-successMessage");
		this.message.setText(message);
	}

	class InputKeyHandler implements KeyPressHandler {

		public void onKeyPress(KeyPressEvent event) {
			if (event.getCharCode() != KeyCodes.KEY_ENTER) return;
			if (Gwt.isEmpty(getUsername())) {
				username.setFocus(true);
				return;
			}
			if (Gwt.isEmpty(getEmail())) {
				email.setFocus(true);
				return;
			}
			if (Gwt.isEmpty(getPassword())) {
				password.setFocus(true);
				return;
			}
			new RegisterAction(RegisterWidget.this).execute();
		}
	}
}
