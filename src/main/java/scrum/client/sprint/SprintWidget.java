package scrum.client.sprint;

import ilarkesto.gwt.client.AFieldValueWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.DateEditorWidget;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import ilarkesto.gwt.client.editor.TextOutputWidget;
import scrum.client.ScrumGwt;
import scrum.client.collaboration.CommentsWidget;
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

		TableBuilder tb = ScrumGwt.createFieldTable();
		tb.setColumnWidths("100px", "80px", "100px", "80px", "100px");

		int cols = 6;
		tb.addFieldRow("Label", sprint.getLabelModel(), cols - 1);
		tb.addFieldRow("Goal", new RichtextEditorWidget(sprint.getGoalModel()), cols - 1);

		if (completed) {
			tb.addFieldRow("Velocity", new TextOutputWidget(sprint.getVelocityModel()), cols - 1);
		}

		tb.addFieldLabel("Dates");
		tb.addField("Begin", new DateEditorWidget(sprint.getBeginModel()));

		tb.addField("End", new DateEditorWidget(sprint.getEndModel()));
		tb.addRow(Gwt.createSpacer(1, 1));

		if (completed == false) {
			// not completed
			tb.addFieldLabel("Stories");
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
			tb.addFieldRow("Completed Stories", new RichtextEditorWidget(getSprint()
					.getCompletedRequirementLabelsModel()), cols - 1);
		}

		tb.addFieldRow("Planning Note", new RichtextEditorWidget(sprint.getPlanningNoteModel()), cols - 1);
		tb.addFieldRow("Review Note", new RichtextEditorWidget(sprint.getReviewNoteModel()), cols - 1);
		tb.addFieldRow("Retrospecitve Note", new RichtextEditorWidget(sprint.getRetrospectiveNoteModel()), cols - 1);
		tb.addFieldRow("My emoticon", sprint.createCurrentUserEmotionEditor());

		if (completed) {
			tb.add(ScrumGwt.createPdfLink("Download Report as PDF", "sprintReport", sprint), cols);
		} else {
			tb.add(ScrumGwt.createPdfLink("Download as PDF", "sprintBacklog", sprint), cols);
		}

		return TableBuilder.row(10, tb.createTable(), new CommentsWidget(sprint));
	}

	public Sprint getSprint() {
		return sprint;
	}

}
