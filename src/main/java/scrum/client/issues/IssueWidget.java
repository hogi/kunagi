package scrum.client.issues;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.editor.DropdownPropertyEditorWidget;
import ilarkesto.gwt.client.editor.RichtextPropertyEditorWidget;
import ilarkesto.gwt.client.editor.TextPropertyEditorWidget;
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

		fields.add("Label", new TextPropertyEditorWidget(issue.labelEditor));
		fields.add("Description", new RichtextPropertyEditorWidget(issue.descriptionEditor));
		fields.add("Type", new DropdownPropertyEditorWidget<String>(issue.typeEditor, Issue.TYPE_LABEL_PROVIDER));

		return Gwt.createFlowPanel(fields, new CommentsWidget(issue));
	}

}
