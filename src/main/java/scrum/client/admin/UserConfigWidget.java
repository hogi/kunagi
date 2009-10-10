package scrum.client.admin;

import ilarkesto.gwt.client.ATextViewEditWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.FieldsWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class UserConfigWidget extends AScrumWidget {

	private FieldsWidget fields;

	@Override
	protected Widget onInitialization() {

		final User user = getCurrentUser();

		fields = new FieldsWidget();
		fields.add("Name", new ATextViewEditWidget() {

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
		fields.add("Email", new ATextViewEditWidget() {

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
		fields.add("Password", new PasswordChangeWidget());
		fields.add("Default Color", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(user.getColor());
				getViewer().getElement().getStyle().setProperty("color", user.getColor());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(user.getColor());
			}

			@Override
			protected void onEditorSubmit() {
				user.setColor(getEditorText());
			}
		});

		PagePanel page = new PagePanel();
		page.addHeader("Global Preferences");
		page.addSection(fields);
		return page;
	}

}
