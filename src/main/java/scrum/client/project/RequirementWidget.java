package scrum.client.project;

import ilarkesto.gwt.client.AFieldValueWidget;
import ilarkesto.gwt.client.AMultiSelectionViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import ilarkesto.gwt.client.editor.TextEditorWidget;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.FieldsWidget;

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

		FieldsWidget fields = new FieldsWidget();

		if (showLabel)
			fields.add("Label", new TextEditorWidget(requirement.getLabelModel(), requirement.getEditPredicate())
					.switchToEditModeIfNull());

		fields.add("Description", new RichtextEditorWidget(requirement.getDescriptionModel(), requirement
				.getEditPredicate()));

		fields.add("Qualities", new AMultiSelectionViewEditWidget<Quality>() {

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
		}.setEditable(requirement.getEditPredicate()));

		fields.add("Test", new RichtextEditorWidget(requirement.getTestDescriptionModel(), requirement
				.getEditPredicate()));

		fields.add("Estimated Work", new RequirementEstimatedWorkWidget(requirement));

		if (showTaskWork) {
			fields.add("Remainig Task Work", new AFieldValueWidget() {

				@Override
				protected void onUpdate() {
					setHours(requirement.getRemainingWork());
				}
			});
		}

		if (showSprint) fields.add("Sprint", new AFieldValueWidget() {

			@Override
			protected void onUpdate() {
				setText(requirement.getSprint());
			}
		});

		return showComments ? TableBuilder.row(20, fields, new CommentsWidget(requirement)) : fields;
	}

}
