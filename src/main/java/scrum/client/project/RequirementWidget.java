package scrum.client.project;

import ilarkesto.gwt.client.AFieldValueWidget;
import ilarkesto.gwt.client.AMultiSelectionViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.DropdownEditorWidget;
import scrum.client.ScrumGwt;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.collaboration.Emoticon;
import scrum.client.estimation.PlanningPokerWidget;

import com.google.gwt.user.client.ui.Widget;

public class RequirementWidget extends AWidget {

	private Requirement requirement;
	private boolean showLabel;
	private boolean showSprint;
	private boolean showTaskWork;
	private boolean showComments;
	private boolean planningPoker;

	public RequirementWidget(Requirement requirement, boolean showLabel, boolean showSprint, boolean showTaskWork,
			boolean showComments, boolean planningPoker) {
		this.requirement = requirement;
		this.showLabel = showLabel;
		this.showSprint = showSprint;
		this.showTaskWork = showTaskWork;
		this.showComments = showComments;
		this.planningPoker = planningPoker;
	}

	@Override
	protected Widget onInitialization() {

		TableBuilder left = ScrumGwt.createFieldTable();

		if (showLabel) left.addFieldRow("Label", requirement.getLabelModel());

		left.addFieldRow("Description", requirement.getDescriptionModel());

		left.addFieldRow("Qualities", new AMultiSelectionViewEditWidget<Quality>() {

			@Override
			protected void onViewerUpdate() {
				setViewerItems(requirement.getQualitys());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorItems(requirement.getProject().getQualitys());
				setEditorSelectedItems(requirement.getQualitys());
			}

			@Override
			protected void onEditorSubmit() {
				requirement.setQualitys(getEditorSelectedItems());
			}

			@Override
			public boolean isEditable() {
				return requirement.isEditable();
			}
		});

		left.addFieldRow("Test", requirement.getTestDescriptionModel());

		left.addFieldRow("Estimated work", new RequirementEstimatedWorkWidget(requirement));

		left.addFieldRow("Personal emoticon", new DropdownEditorWidget<String>(
				requirement.getCurrentUserEmotionModel(), Emoticon.EMOTION_LABEL_PROVIDER));

		if (showTaskWork) {
			left.addFieldRow("Remainig Task Work", new AFieldValueWidget() {

				@Override
				protected void onUpdate() {
					setHours(requirement.getRemainingWork());
				}
			});
		}

		if (showSprint) left.addFieldRow("Sprint", new AFieldValueWidget() {

			@Override
			protected void onUpdate() {
				setText(requirement.getSprint());
			}
		});

		TableBuilder right = new TableBuilder();
		if (planningPoker) {
			right.addRow(new PlanningPokerWidget(requirement));
		}
		if (showComments) {
			right.addRow(new CommentsWidget(requirement));
		}

		return showComments || planningPoker ? TableBuilder.row(20, left.createTable(), right.createTable()) : left
				.createTable();
	}
}
