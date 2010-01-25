package scrum.client.admin;

import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.editor.DateAndTimeEditorWidget;
import ilarkesto.gwt.client.editor.TextEditorWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.FieldsWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class SystemMessageManagerWidget extends AScrumWidget {

	@Override
	protected Widget onInitialization() {
		FieldsWidget fields = new FieldsWidget();
		fields.add("Text", new TextEditorWidget(cm.getSystemMessageManager().systemMessageTextModel));
		fields
				.add("Date and Time", new DateAndTimeEditorWidget(
						cm.getSystemMessageManager().systemMessageExpiresModel));

		PagePanel page = new PagePanel();
		page.addHeader("System Message Management", new ButtonWidget(new ActivateSystemMessageAction()),
			new ButtonWidget(new DeactivateSystemMessageAction()));
		page.addSection(fields);

		return page;
	}

}
