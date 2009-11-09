package scrum.client.sprint;

import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.DateEditorWidget;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import ilarkesto.gwt.client.editor.TextEditorWidget;
import scrum.client.collaboration.CommentsWidget;
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
		fieldsWidget.add("Label", new TextEditorWidget(sprint.getLabelModel(), sprint.getEditPredicate()));
		fieldsWidget.add("Goal", new RichtextEditorWidget(sprint.getGoalModel(), sprint.getEditPredicate()));
		fieldsWidget.add("Begin", new DateEditorWidget(sprint.getBeginModel()));
		fieldsWidget.add("End", new DateEditorWidget(sprint.getEndModel()));
		fieldsWidget.add("Planning Note", new RichtextEditorWidget(sprint.getPlaningNoteModel(), sprint
				.getPlaningEditPredicate()));

		PagePanel page = new PagePanel();
		page.addHeader("Next Sprint", new ButtonWidget(new SwitchToNextSprintAction()));
		page.addSection(TableBuilder.row(20, fieldsWidget, new CommentsWidget(sprint)));
		return page;
	}

	private Sprint getSprint() {
		Project project = getCurrentProject();
		if (project == null) return null;
		return project.getNextSprint();
	}

}
