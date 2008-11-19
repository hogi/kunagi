package scrum.client.admin;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.workspace.WorkspaceWidget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginWidget extends Composite {

	private TextBox username;
	private PasswordTextBox password;
	private Button loginButton;

	public LoginWidget() {
		username = new TextBox();
		username.setText("admin");
		password = new PasswordTextBox();
		password.setText("geheim");
		loginButton = new Button("Login");
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

		initWidget(panel);
	}

	private void login() {
		// simulate project selection
		WorkspaceWidget.lock("Loading projects...");
		ScrumGwtApplication.get().callLogin(username.getText(), password.getText(), new Runnable() {

			public void run() {
				WorkspaceWidget.projectSelection.update();
				WorkspaceWidget.activateProjectSelection();
			}
		});
	}

}
