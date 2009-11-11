package scrum.client.journal;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.DateAndTimeEditorWidget;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;

import com.google.gwt.user.client.ui.Widget;

public class ProjectEventWidget extends AWidget {

	private ProjectEvent projectEvent;

	public ProjectEventWidget(ProjectEvent projectEvent) {
		this.projectEvent = projectEvent;
	}

	@Override
	protected Widget onInitialization() {
		TableBuilder tb = new TableBuilder();
		tb.setCellSpacing(5);

		// TODO use DateAndTimeEditorWidget
		tb.addFieldRow("Timestamp", new DateAndTimeEditorWidget(projectEvent.getDateAndTimeModel()));
		tb.addFieldRow("Label", new RichtextEditorWidget(projectEvent.getLabelModel()));

		return tb.createTable();
	}

}
