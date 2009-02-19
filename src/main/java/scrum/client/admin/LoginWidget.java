package scrum.client.admin;

import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.GwtLogger;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.PanelWidget;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginWidget extends Composite {

	private TextBox username;
	private PasswordTextBox password;
	private ButtonWidget loginButton;

	public LoginWidget() {
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

		ItemFieldsWidget fieldsWidget = new ItemFieldsWidget();
		fieldsWidget.addField("Username", username);
		fieldsWidget.addField("Password", password);

		FlowPanel panel = new FlowPanel();
		panel.add(fieldsWidget);
		panel.add(loginButton);

		initWidget(new PanelWidget("Login", panel));
	}

	private void login() {
		ScrumGwtApplication.get().getWorkspace().lock("Checking login data...");
		ScrumGwtApplication.get().callLogin(username.getText(), password.getText(), new Runnable() {

			public void run() {
				GwtLogger.DEBUG("Login response received");
				if (ScrumGwtApplication.get().getUser() == null) {
					// login failed
					ScrumGwtApplication.get().getWorkspace().activateLogin();
				} else {
					// login succeeded
					ScrumGwtApplication.get().getWorkspace().getProjectSelector().update();
					ScrumGwtApplication.get().getWorkspace().activateProjectSelection();
				}
			}
		});
	}

}
