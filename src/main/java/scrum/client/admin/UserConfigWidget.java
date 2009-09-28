package scrum.client.admin;

import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.FieldsWidget;
import scrum.client.common.GroupWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserConfigWidget extends AWidget {

	private FieldsWidget projectFields;
	private FieldsWidget globalFields;

	@Override
	protected Widget onInitialization() {

		// --- project config ---

		final ProjectUserConfig config = ScrumGwtApplication.get().getUser().getProjectConfig();

		projectFields = new FieldsWidget();
		projectFields.add("Color", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(config.getColor());
				getViewer().getElement().getStyle().setProperty("color", config.getColor());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(config.getColor());
			}

			@Override
			protected void onEditorSubmit() {
				config.setColor(getEditorText());
			}
		});

		// --- global config ---

		final User user = ScrumGwtApplication.get().getUser();

		globalFields = new FieldsWidget();
		globalFields.add("Name", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(user.getName());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(user.getName());
			}

			@Override
			protected void onEditorSubmit() {
				user.setName(getEditorText());
			}
		});
		globalFields.add("Email", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(user.getEmail());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(user.getEmail());
			}

			@Override
			protected void onEditorSubmit() {
				user.setEmail(getEditorText());
			}
		});
		globalFields.add("Password", new PasswordChangeWidget());

		// ---

		FlowPanel panel = new FlowPanel();
		panel.add(new GroupWidget("Project Preferences", projectFields));
		panel.add(new GroupWidget("Global Preferences", globalFields));

		return panel;
	}

	@Override
	protected void onUpdate() {
		projectFields.update();
	}

}
