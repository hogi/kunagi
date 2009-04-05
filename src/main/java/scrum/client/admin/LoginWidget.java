package scrum.client.admin;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.GwtLogger;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.FieldsWidget;
import scrum.client.common.GroupWidget;
import scrum.client.test.WidgetsTesterWidget;
import scrum.client.workspace.Ui;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginWidget extends AWidget {

	private TextBox username;
	private PasswordTextBox password;

	@Override
	protected Widget onInitialization() {
		username = new TextBox();
		username.setText("admin");
		password = new PasswordTextBox();
		password.setText("geheim");

		ToolbarWidget toolbar = new ToolbarWidget(true);
		toolbar.addButton("Login").addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				login();
			}
		});

		FieldsWidget fieldsWidget = new FieldsWidget();
		fieldsWidget.addWidget("Username", username);
		fieldsWidget.addWidget("Password", password);
		fieldsWidget.addWidget(null, toolbar.update());

		SimplePanel wrapper = new SimplePanel();
		wrapper.setStyleName("LoginWidget");
		wrapper.setWidget(new GroupWidget("Login", fieldsWidget));

		// TODO remove this
		FlowPanel test = new FlowPanel();
		test.add(wrapper);
		test.add(new WidgetsTesterWidget().update());
		return test;

		// return wrapper;
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
					Ui.get().showError("Login failed.");
				} else {
					GwtLogger.DEBUG("Login succeded:", ScrumGwtApplication.get().getUi());
					Ui.get().showProjectSelector();
				}
			}
		});
	}

}
