package scrum.client.admin;

import ilarkesto.core.scope.Scope;
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
		SystemMessageManager systemMessageManager = Scope.get().getComponent(SystemMessageManager.class);

		FieldsWidget fields = new FieldsWidget();
		fields.add("Text", new TextEditorWidget(systemMessageManager.systemMessageTextModel));
		fields.add("Date and Time", new DateAndTimeEditorWidget(systemMessageManager.systemMessageExpiresModel));

		PagePanel page = new PagePanel();
		page.addHeader("System Message Management", new ButtonWidget(new ActivateSystemMessageAction()),
			new ButtonWidget(new DeactivateSystemMessageAction()));
		page.addSection(fields);

		return page;
	}

}
