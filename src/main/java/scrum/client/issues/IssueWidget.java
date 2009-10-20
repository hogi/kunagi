package scrum.client.issues;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.editor.DropdownEditorWidget;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import ilarkesto.gwt.client.editor.TextEditorWidget;
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

		fields.add("Label", new TextEditorWidget(issue.labelModel));
		fields.add("Description", new RichtextEditorWidget(issue.descriptionModel));
		fields.add("Type", new DropdownEditorWidget<String>(issue.typeModel, Issue.TYPE_LABEL_PROVIDER));

		return Gwt.createFlowPanel(fields, new CommentsWidget(issue));
	}

}
