package scrum.client.calendar;

import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.DateEditorWidget;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import ilarkesto.gwt.client.editor.TextEditorWidget;
import ilarkesto.gwt.client.editor.TimeEditorWidget;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Widget;

public class SimpleEventWidget extends AScrumWidget {

	private SimpleEvent event;

	public SimpleEventWidget(SimpleEvent event) {
		super();
		this.event = event;
	}

	@Override
	protected Widget onInitialization() {

		TableBuilder tb = new TableBuilder();
		tb.setCellPadding(2);
		tb.addFieldRow("Label", new TextEditorWidget(event.getLabelModel()).switchToEditModeIfNull());
		tb.addFieldRow("Date", new DateEditorWidget(event.getDateModel()));
		tb.addFieldRow("Time", new TimeEditorWidget(event.getTimeModel()));
		tb.addFieldRow("Location", new TextEditorWidget(event.getLocationModel()));
		tb.addFieldRow("Note", new RichtextEditorWidget(event.getNoteModel()));
		tb.addRow(new CommentsWidget(event), 2);

		return tb.createTable();
	}

}
