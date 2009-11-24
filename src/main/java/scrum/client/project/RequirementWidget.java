package scrum.client.project;

import ilarkesto.gwt.client.AFieldValueWidget;
import ilarkesto.gwt.client.AMultiSelectionViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.collaboration.CommentsWidget;

import com.google.gwt.user.client.ui.Widget;

public class RequirementWidget extends AWidget {

	private Requirement requirement;
	private boolean showLabel;
	private boolean showSprint;
	private boolean showTaskWork;
	private boolean showComments;

	public RequirementWidget(Requirement requirement, boolean showLabel, boolean showSprint, boolean showTaskWork,
			boolean showComments) {
		this.requirement = requirement;
		this.showLabel = showLabel;
		this.showSprint = showSprint;
		this.showTaskWork = showTaskWork;
		this.showComments = showComments;
	}

	@Override
	protected Widget onInitialization() {

		TableBuilder tb = new TableBuilder();
		tb.setCellPadding(2);

		if (showLabel) tb.addFieldRow("Label", requirement.getLabelModel());

		tb.addFieldRow("Description", requirement.getDescriptionModel());

		tb.addFieldRow("Qualities", new AMultiSelectionViewEditWidget<Quality>() {

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

		tb.addFieldRow("Test", requirement.getTestDescriptionModel());

		tb.addFieldRow("Estimated Work", new RequirementEstimatedWorkWidget(requirement));

		if (showTaskWork) {
			tb.addFieldRow("Remainig Task Work", new AFieldValueWidget() {

				@Override
				protected void onUpdate() {
					setHours(requirement.getRemainingWork());
				}
			});
		}

		if (showSprint) tb.addFieldRow("Sprint", new AFieldValueWidget() {

			@Override
			protected void onUpdate() {
				setText(requirement.getSprint());
			}
		});

		return showComments ? TableBuilder.row(20, tb.createTable(), new CommentsWidget(requirement)) : tb
				.createTable();
	}
}
