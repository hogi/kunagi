package scrum.client.sprint;

import ilarkesto.gwt.client.ADateViewEditWidget;
import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.FieldsWidget;
import scrum.client.common.GroupWidget;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class NextSprintWidget extends AScrumWidget {

	private FlowPanel view;

	private FieldsWidget fieldsWidget;

	private ToolbarWidget toolbar;

	@Override
	protected Widget onInitialization() {

		toolbar = new ToolbarWidget();
		toolbar.addButton(new SwitchToNextSprintAction());

		fieldsWidget = new FieldsWidget();

		fieldsWidget.add("Label", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				if (getSprint() != null) setViewerText(getSprint().getLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(getSprint().getLabel());
			}

			@Override
			protected void onEditorSubmit() {
				getSprint().setLabel(getEditorText());
			}
		});

		fieldsWidget.add("Goal", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				if (getSprint() != null) setViewerText(getSprint().getGoal());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(getSprint().getGoal());
			}

			@Override
			protected void onEditorSubmit() {
				getSprint().setGoal(getEditorText());
			}
		});

		fieldsWidget.add("Begin", new ADateViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerValue(getSprint().getBegin());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorValue(getSprint().getBegin());
			}

			@Override
			protected void onEditorSubmit() {
				getSprint().setBegin(getEditorValue());
			}
		});

		fieldsWidget.add("End", new ADateViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerValue(getSprint().getEnd());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorValue(getSprint().getEnd());
			}

			@Override
			protected void onEditorSubmit() {
				getSprint().setEnd(getEditorValue());
			}
		});

		view = new FlowPanel();
		view.add(toolbar);
		view.add(fieldsWidget);
		return new GroupWidget("Sprint Backlog", view);
	}

	@Override
	protected void onUpdate() {
		toolbar.update();
		fieldsWidget.update();
	}

	private Sprint getSprint() {
		Project project = getCurrentProject();
		if (project == null) return null;
		return project.getNextSprint();
	}

}
