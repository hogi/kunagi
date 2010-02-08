package scrum.client.journal;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.DateAndTimeEditorWidget;
import scrum.client.ScrumGwt;

import com.google.gwt.user.client.ui.Widget;

public class ProjectEventWidget extends AWidget {

	private ProjectEvent projectEvent;

	public ProjectEventWidget(ProjectEvent projectEvent) {
		this.projectEvent = projectEvent;
	}

	@Override
	protected Widget onInitialization() {
		TableBuilder tb = ScrumGwt.createFieldTable();

		tb.addFieldRow("Timestamp", new DateAndTimeEditorWidget(projectEvent.getDateAndTimeModel()));
		tb.addFieldRow("Label", projectEvent.getLabelModel());

		return tb.createTable();
	}

}
