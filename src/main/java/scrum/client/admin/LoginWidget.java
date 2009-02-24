package scrum.client.admin;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.GwtLogger;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.FieldsWidget;
import scrum.client.common.PanelWidget;
import scrum.client.workspace.Ui;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginWidget extends AWidget {

	private TextBox username;
	private PasswordTextBox password;
	private ButtonWidget loginButton;

	@Override
	protected Widget onInitialization() {
		username = new TextBox();
		username.setText("admin");
		password = new PasswordTextBox();
		password.setText("geheim");
		loginButton = new ButtonWidget(null, "Login");
		loginButton.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				login();
			}
		});

		FieldsWidget fieldsWidget = new FieldsWidget();
		fieldsWidget.addWidget("Username", username);
		fieldsWidget.addWidget("Password", password);

		FlowPanel panel = new FlowPanel();
		panel.add(fieldsWidget);
		panel.add(loginButton);

		return new PanelWidget("Login", panel);
	}

	@Override
	protected void onUpdate() {}

	private void login() {
		ScrumGwtApplication.get().getUi().lock("Checking login data...");
		ScrumGwtApplication.get().callLogin(username.getText(), password.getText(), new Runnable() {

			public void run() {
				GwtLogger.DEBUG("Login response received");
				if (ScrumGwtApplication.get().getUser() == null) {
					GwtLogger.DEBUG("LOGIN FAILED!");
					ScrumGwtApplication.get().getUi().unlock();
				} else {
					GwtLogger.DEBUG("Login succeded:", ScrumGwtApplication.get().getUi());
					Ui.get().showProjectSelector();
				}
			}
		});
	}

}
