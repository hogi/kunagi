package scrum.client.admin;

import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ApplicationInfo;
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

		TableBuilder fields = ScrumGwt.createFieldTable();
		fields.setWidth(null);

		fields.addRow(errorMessage, 2);
		fields.addFieldRow("Username / Email", username);
		fields.addFieldRow("Secret Password", password);
		fields.addFieldRow("", new ButtonWidget(new LoginAction(this)), 2);

		Widget content = fields.createTable();

		ApplicationInfo appInfo = getApp().getApplicationInfo();
		if (!appInfo.isProductionStage()) {
			StringBuilder sb = new StringBuilder();
			sb.append("<div class='LoginWidget-errorMessage'>");
			sb
					.append("This is a demo system with preconfigured projects.<br>All your data will be deleted by next day.");
			sb.append("</div>");
			sb.append("<div style='color: gray;'>");
			sb.append("<br>Test&nbsp;users:&nbsp;");
			sb.append("<strong>duke</strong>&nbsp;(PO),&nbsp;");
			sb.append("<strong>spinne</strong>&nbsp;(SM),&nbsp;");
			sb.append("<strong>cartman</strong>&nbsp;(T),&nbsp;");
			sb.append("<strong>homer</strong>&nbsp;(T)<br>");
			sb.append("Password: <strong>geheim</strong>");
			sb.append("</div>");
			content = TableBuilder.row(40, content, new HTML(sb.toString()));
		}

		PagePanel page = new PagePanel();
		page.addHeader("Login");
		page.addSection(Gwt.createCenterer(content));

		return page;
	}

	public void setFailed() {
		errorMessage.setStyleName("LoginWidget-errorMessage");
		errorMessage.setText("Login failed. Try again.");
	}

	public void clear() {
		username.setText(null);
		password.setText(null);
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		ApplicationInfo applicationInfo = getApp().getApplicationInfo();
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
