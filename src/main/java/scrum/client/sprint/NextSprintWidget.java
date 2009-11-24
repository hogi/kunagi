package scrum.client.sprint;

import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.DateEditorWidget;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.project.Project;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class NextSprintWidget extends AScrumWidget {

	@Override
	protected Widget onInitialization() {
		Sprint sprint = getSprint();

		TableBuilder tb = new TableBuilder();
		tb.setCellPadding(2);
		tb.addFieldRow("Label", sprint.getLabelModel());
		tb.addFieldRow("Goal", new RichtextEditorWidget(sprint.getGoalModel()).setTemplate(cm.getWiki().getTemplate(
			"sprint.goal")));
		tb.addFieldRow("Begin", new DateEditorWidget(sprint.getBeginModel()));
		tb.addFieldRow("End", new DateEditorWidget(sprint.getEndModel()));
		tb.addFieldRow("Planning Note", new RichtextEditorWidget(sprint.getPlanningNoteModel()).setTemplate(cm
				.getWiki().getTemplate("sprint.planning")));

		PagePanel page = new PagePanel();
		page.addHeader("Next Sprint", new ButtonWidget(new SwitchToNextSprintAction()));
		page.addSection(TableBuilder.row(20, tb.createTable(), new CommentsWidget(sprint)));
		return page;
	}

	private Sprint getSprint() {
		Project project = getCurrentProject();
		if (project == null) return null;
		return project.getNextSprint();
	}

}
