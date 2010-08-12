package scrum.client.admin;

import ilarkesto.core.base.Str;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.ScrumGwt;
import scrum.client.common.AScrumWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.HTML;
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
		boolean registrationDisabled = getDao().getSystemConfig().isRegistrationDisabled();
		message = new Label();

		username = new TextBox();
		username.addStyleName("InputMandatory");
		username.setName("username");
		username.setWidth("150px");
		username.addKeyPressHandler(new InputKeyHandler());

		email = new TextBox();
		if (getDao().getSystemConfig().isUserEmailMandatory()) email.addStyleName("InputMandatory");
		email.setName("email");
		email.setWidth("150px");
		email.addKeyPressHandler(new InputKeyHandler());

		password = new PasswordTextBox();
		password.addStyleName("InputMandatory");
		password.setName("password");
		password.setWidth("150px");
		password.addKeyPressHandler(new InputKeyHandler());

		Widget content;

		if (registrationDisabled) {
			content = new Label("Registration is disabled.");
		} else {
			TableBuilder fields = ScrumGwt.createFieldTable();
			fields.setWidth(null);
			fields.addRow(message, 2);
			fields.addFieldRow("Username", username);
			fields.addFieldRow("Email", email);
			fields.addFieldRow("Password", password);
			fields.addFieldRow("", new ButtonWidget(new RegisterAction(this)), 2);
			content = fields.createTable();
		}

		SystemConfig config = getDao().getSystemConfig();
		String message = config.getRegisterPageMessage();
		if (!Str.isBlank(message)) {
			content = TableBuilder.row(40, content, new HTML("<div class='LoginWidget-errorMessage'>" + message
					+ "</div>"));
		}

		content = Gwt.createCenterer(content);

		PagePanel page = new PagePanel();
		page.addHeader("Register");
		page.addSection(content);

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

	class InputKeyHandler implements KeyPressHandler {

		public void onKeyPress(KeyPressEvent event) {
			if (event.getCharCode() != KeyCodes.KEY_ENTER) return;
			if (Str.isBlank(getUsername())) {
				username.setFocus(true);
				return;
			}
			if (Str.isBlank(getEmail()) && getDao().getSystemConfig().isUserEmailMandatory()) {
				email.setFocus(true);
				return;
			}
			if (Str.isBlank(getPassword())) {
				password.setFocus(true);
				return;
			}
			new RegisterAction(RegisterWidget.this).execute();
		}
	}
}
