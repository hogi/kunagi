package scrum.client.issues;

import ilarkesto.gwt.client.ADropdownViewEditWidget;
import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.Gwt;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.FieldsWidget;

import com.google.gwt.user.client.ui.Widget;

public class IssueWidget extends AScrumWidget {

	private Issue issue;

	public IssueWidget(Issue issue) {
		super();
		this.issue = issue;
	}

	@Override
	protected Widget onInitialization() {
		FieldsWidget fields = new FieldsWidget();

		fields.add("Label", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(issue.getLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(issue.getLabel());
			}

			@Override
			protected void onEditorSubmit() {
				issue.setLabel(getEditorText());
			}

		});
		fields.add("Description", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(issue.getDescription());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(issue.getDescription());
			}

			@Override
			protected void onEditorSubmit() {
				issue.setDescription(getEditorText());
			}
		});
		fields.add("Type", new ADropdownViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(issue.getTypeLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setOptions(Issue.TYPES);
				setSelectedOption(issue.getType());
			}

			@Override
			protected void onEditorSubmit() {
				issue.setType(getSelectedOption());
			}
		});

		return Gwt.createFlowPanel(fields, new CommentsWidget(issue));
	}

}
