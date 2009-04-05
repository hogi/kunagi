package scrum.client.sprint;

import ilarkesto.gwt.client.ADateViewEditWidget;
import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.FieldsWidget;
import scrum.client.common.GroupWidget;
import scrum.client.project.Project;
import scrum.client.workspace.Ui;
import scrum.client.workspace.WorkareaWidget;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class NextSprintWidget extends AWidget {

	private FlowPanel view;

	private FieldsWidget fieldsWidget;

	@Override
	protected Widget onInitialization() {

		ToolbarWidget toolbar = new ToolbarWidget(true);

		toolbar.addButton("Activate this Sprint").addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				Ui.get().lock("Switching to next Sprint");
				ScrumGwtApplication.get().callSwitchToNextSprint(new Runnable() {

					public void run() {
						WorkareaWidget.get().showSprintBacklog();
					}
				});
			}
		});

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
		fieldsWidget.update();
	}

	private Sprint getSprint() {
		Project project = ScrumGwtApplication.get().getProject();
		if (project == null) return null;
		return project.getNextSprint();
	}

	public static NextSprintWidget get() {
		return WorkareaWidget.get().getNextSprint();
	}

}
