package scrum.client.calendar;

import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.DateEditorWidget;
import ilarkesto.gwt.client.editor.TextEditorWidget;
import ilarkesto.gwt.client.editor.TimeEditorWidget;
import scrum.client.ScrumGwt;
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

		TableBuilder tb = ScrumGwt.createFieldTable();
		tb.addFieldRow("Label", event.getLabelModel());
		tb.addFieldRow("Date", new DateEditorWidget(event.getDateModel()));
		tb.addFieldRow("Time", new TimeEditorWidget(event.getTimeModel()));
		tb.addFieldRow("Location", new TextEditorWidget(event.getLocationModel()));
		tb.addFieldRow("Agenda", event.getAgendaModel());
		tb.addFieldRow("Note", event.getNoteModel());
		tb.addRow(new CommentsWidget(event), 2);

		return tb.createTable();
	}

}
