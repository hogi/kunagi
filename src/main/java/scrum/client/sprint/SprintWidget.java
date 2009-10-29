package scrum.client.sprint;

import ilarkesto.gwt.client.AFieldValueWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.DateEditorWidget;
import ilarkesto.gwt.client.editor.IntegerEditorWidget;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import ilarkesto.gwt.client.editor.TextEditorWidget;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Widget;

public class SprintWidget extends AScrumWidget {

	private Sprint sprint;

	public SprintWidget(Sprint sprint) {
		super();
		this.sprint = sprint;
	}

	@Override
	protected Widget onInitialization() {
		boolean completed = sprint.isCompleted();

		TableBuilder tb = new TableBuilder();
		tb.setColumnWidths("100px");

		tb.addFieldRow("Label", new TextEditorWidget(sprint.getLabelModel()), 4);
		tb.addFieldRow("Goal", new RichtextEditorWidget(sprint.getGoalModel()), 4);

		if (completed) {
			tb.addFieldRow("Velocity", new IntegerEditorWidget(sprint.getVelocityModel()), 4);
		}

		tb.addFieldLabel("Dates");
		tb.addField("Begin", new DateEditorWidget(sprint.getBeginModel()));

		tb.addFieldRow("End", new DateEditorWidget(sprint.getEndModel()));

		if (completed == false) {
			// not completed
			tb.addFieldLabel("Requirements");
			tb.addField("Completed", new AFieldValueWidget() {

				@Override
				protected void onUpdate() {
					setText(getCurrentProject().formatEfford(getSprint().getCompletedRequirementWork()));
				}
			});
			tb.addField("Estimated", new AFieldValueWidget() {

				@Override
				protected void onUpdate() {
					setText(getCurrentProject().formatEfford(getSprint().getEstimatedRequirementWork()));
				}
			});
			tb.nextRow();

			tb.addFieldLabel("Tasks");
			tb.addField("Burned", new AFieldValueWidget() {

				@Override
				protected void onUpdate() {
					setHours(getSprint().getBurnedWork());
				}
			});
			tb.addField("Remaining", new AFieldValueWidget() {

				@Override
				protected void onUpdate() {
					setHours(getSprint().getRemainingWork());
				}
			});
			tb.nextRow();
		} else {
			// completed
			tb.addFieldRow("Completed Requirements", new RichtextEditorWidget(getSprint()
					.getCompletedRequirementLabelsModel()), 4);
		}

		tb.addFieldRow("Review Notes", new RichtextEditorWidget(sprint.getReviewNoteModel()), 4);
		tb.addFieldRow("Retrospecitve Notes", new RichtextEditorWidget(sprint.getRetrospectiveNoteModel()), 4);

		if (completed)
			tb.add(Gwt
					.createServletDownloadLink("sprintReport.pdf?sprintId=" + sprint.getId(), "Downlad Report as PDF"),
				5);

		return tb.createTable();
	}

	public Sprint getSprint() {
		return sprint;
	}

}
