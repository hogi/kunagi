package scrum.client.admin;

import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.TextEditorWidget;
import scrum.client.ScrumGwt;
import scrum.client.common.AScrumWidget;
import scrum.client.common.FieldsWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class UserConfigWidget extends AScrumWidget {

	private FieldsWidget fields;

	@Override
	protected Widget onInitialization() {

		final User user = getCurrentUser();

		TableBuilder tb = ScrumGwt.createFieldTable();
		tb.addFieldRow("Name", user.getNameModel());
		tb.addFieldRow("Email", user.getEmailModel());
		tb.addFieldRow("OpenID", user.getOpenIdModel());
		tb.addFieldRow("Password", new PasswordChangeWidget());
		tb.addFieldRow("Default Color", new TextEditorWidget(user.getColorModel()) {

			@Override
			protected void onViewerUpdate() {
				super.onViewerUpdate();
				getViewer().getElement().getStyle().setProperty("color", user.getColor());
			}
		});

		PagePanel page = new PagePanel();
		page.addHeader("Personal preferences for all projects");
		page.addSection(tb.createTable());
		return page;
	}

}
