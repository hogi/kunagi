package scrum.client.journal;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ProjectEventWidget extends AWidget {

	private ProjectEvent projectEvent;

	public ProjectEventWidget(ProjectEvent projectEvent) {
		this.projectEvent = projectEvent;
	}

	@Override
	protected Widget onInitialization() {
		TableBuilder tb = new TableBuilder();

		// TODO use DateAndTimeEditorWidget
		tb.addFieldRow("Timestamp", new Label(projectEvent.getDateAndTime().toString()));
		tb.addFieldRow("Label", new RichtextEditorWidget(projectEvent.getLabelModel(), Gwt.predicate(false)));

		return tb.createTable();
	}

}
