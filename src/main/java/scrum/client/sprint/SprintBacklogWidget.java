package scrum.client.sprint;

import ilarkesto.gwt.client.AFieldValueWidget;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.DateEditorWidget;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import ilarkesto.gwt.client.editor.TextEditorWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.project.Requirement;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class SprintBacklogWidget extends AScrumWidget {

	private BlockListWidget<Requirement> requirementList;

	private FlexTable headerFields;

	@Override
	protected Widget onInitialization() {
		Sprint sprint = getSprint();

		requirementList = new BlockListWidget<Requirement>(RequirementInSprintBlock.FACTORY);
		requirementList.setAutoSorter(getCurrentProject().getRequirementsOrderComparator());

		TableBuilder tb = new TableBuilder();

		tb.addFieldRow("Label", new TextEditorWidget(sprint.labelModel), 4);

		tb.addFieldRow("Goal", new RichtextEditorWidget(sprint.goalModel), 4);
		tb.addFieldLabel("Dates");
		tb.addField("Begin", new DateEditorWidget(sprint.beginModel));

		tb.addFieldRow("End", new DateEditorWidget(sprint.endModel));

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

		headerFields = tb.createTable();

		PagePanel page = new PagePanel();
		page.addHeader("Sprint Properties");
		page.addSection(headerFields);
		page.addHeader("Requirements in this Sprint");
		page.addSection(requirementList);

		return page;
	}

	@Override
	protected void onUpdate() {
		requirementList.setObjects(getSprint().getRequirements());
		super.onUpdate();
	}

	public void selectRequirement(Requirement r) {
		requirementList.extendObject(r);
		requirementList.scrollToObject(r);
	}

	public void selectTask(Task task) {
		RequirementInSprintBlock rBlock = (RequirementInSprintBlock) requirementList.getBlock(task.getRequirement());
		requirementList.extendBlock(rBlock, true);
		rBlock.selectTask(task);
	}

	private Sprint getSprint() {
		return getCurrentProject().getCurrentSprint();
	}

}
