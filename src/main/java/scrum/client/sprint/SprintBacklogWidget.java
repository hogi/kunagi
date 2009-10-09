package scrum.client.sprint;

import ilarkesto.gwt.client.ADateViewEditWidget;
import ilarkesto.gwt.client.AFieldValueWidget;
import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.ComponentManager;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;
import scrum.client.project.Requirement;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class SprintBacklogWidget extends AScrumWidget {

	private BlockListWidget<Requirement> requirementList;

	private FlexTable headerFields;

	@Override
	protected Widget onInitialization() {

		requirementList = new BlockListWidget<Requirement>(RequirementInSprintBlock.FACTORY);
		requirementList.setAutoSorter(getCurrentProject().getRequirementsOrderComparator());

		TableBuilder tb = new TableBuilder();

		tb.addFieldRow("Label", new ATextViewEditWidget() {

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
		}, 4);

		tb.addFieldRow("Goal", new ARichtextViewEditWidget() {

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
		}, 4);

		tb.addFieldLabel("Dates");
		tb.addField("Begin", new ADateViewEditWidget() {

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

		tb.addFieldRow("End", new ADateViewEditWidget() {

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

		FlowPanel view = new FlowPanel();
		view.add(headerFields);
		view.add(requirementList);

		return new GroupWidget("Sprint Backlog", view);
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

	class AssignSprintListener implements ClickListener {

		public void onClick(Widget sender) {
			update();
		}
	}

	public static SprintBacklogWidget get() {
		return ComponentManager.get().getProjectContext().getSprintBacklog();
	}

}
