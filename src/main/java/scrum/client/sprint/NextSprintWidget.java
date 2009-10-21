package scrum.client.sprint;

import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.editor.DateEditorWidget;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import ilarkesto.gwt.client.editor.TextEditorWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.FieldsWidget;
import scrum.client.project.Project;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class NextSprintWidget extends AScrumWidget {

	private FieldsWidget fieldsWidget;

	@Override
	protected Widget onInitialization() {
		Sprint sprint = getSprint();

		fieldsWidget = new FieldsWidget();
		fieldsWidget.add("Label", new TextEditorWidget(sprint.labelModel));
		fieldsWidget.add("Goal", new RichtextEditorWidget(sprint.goalModel));
		fieldsWidget.add("Begin", new DateEditorWidget(sprint.beginModel));
		fieldsWidget.add("End", new DateEditorWidget(sprint.endModel));

		PagePanel page = new PagePanel();
		page.addHeader("Sprint Backlog", new ButtonWidget(new SwitchToNextSprintAction()));
		page.addSection(fieldsWidget);
		return page;
	}

	private Sprint getSprint() {
		Project project = getCurrentProject();
		if (project == null) return null;
		return project.getNextSprint();
	}

}
