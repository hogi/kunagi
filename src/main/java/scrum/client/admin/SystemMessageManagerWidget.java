package scrum.client.admin;

import ilarkesto.gwt.client.ADateViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.ATimeViewEditWidget;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.DateAndTime;
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
		fields.add("Day Expires", new ADateViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerValue(getMessage().getExpiresDate());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorValue(getMessage().getExpiresDate());
			}

			@Override
			protected void onEditorSubmit() {
				DateAndTime datetime = new DateAndTime(getEditorValue(), getMessage().getExpiresTime());
				getMessage().setExpires(datetime);
			}
		});
		fields.add("Time Expires", new ATimeViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerValue(getMessage().getExpiresTime());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorValue(getMessage().getExpiresTime());
			}

			@Override
			protected void onEditorSubmit() {
				DateAndTime datetime = new DateAndTime(getMessage().getExpiresDate(), getEditorValue());
				getMessage().setExpires(datetime);
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
