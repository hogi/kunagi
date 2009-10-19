package scrum.client.admin;

import ilarkesto.gwt.client.ADateAndTimeViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.ATimeViewEditWidget;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.DateAndTime;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.FieldsWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class SystemMessageManagerWidget extends AScrumWidget {

	@Override
	protected Widget onInitialization() {
		FieldsWidget fields = new FieldsWidget();
		fields.add("Text", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(getMessage().getText());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(getMessage().getText());
			}

			@Override
			protected void onEditorSubmit() {
				getMessage().setText(getEditorText());
			}
		});
		fields.add("Date and Time", new ADateAndTimeViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerValue(getMessage().getExpires());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorValue(getMessage().getExpires());
			}

			@Override
			protected void onEditorSubmit() {
				getMessage().setExpires(getEditorValue());
			}
		});

		PagePanel page = new PagePanel();
		page.addHeader("System Message Management", new ButtonWidget(new ActivateSystemMessageAction()),
			new ButtonWidget(new DeactivateSystemMessageAction()));
		page.addSection(fields);

		return page;
	}

	private SystemMessage getMessage() {
		return cm.getSystemMessageManager().getSystemMessage();
	}

}
