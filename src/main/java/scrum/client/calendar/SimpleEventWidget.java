package scrum.client.calendar;

import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.DateEditorWidget;
import ilarkesto.gwt.client.editor.TextEditorWidget;
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

		return tb.createTable();
	}

}
