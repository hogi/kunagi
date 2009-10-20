package scrum.client.admin;

import ilarkesto.gwt.client.editor.TextEditorWidget;
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
		fields.add("Name", new TextEditorWidget(user.nameModel));
		fields.add("Email", new TextEditorWidget(user.emailModel));
		fields.add("Password", new PasswordChangeWidget());
		fields.add("Default Color", new TextEditorWidget(user.colorModel) {

			@Override
			protected void onViewerUpdate() {
				super.onViewerUpdate();
				getViewer().getElement().getStyle().setProperty("color", user.getColor());
			}
		});

		PagePanel page = new PagePanel();
		page.addHeader("Global Preferences");
		page.addSection(fields);
		return page;
	}

}
