package scrum.client.issues;

import ilarkesto.gwt.client.TableBuilder;
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

		fields.add("Label", new TextEditorWidget(issue.getLabelModel()));
		fields.add("Description", new RichtextEditorWidget(issue.getDescriptionModel()));
		fields.add("Type", new DropdownEditorWidget<String>(issue.getTypeModel(), Issue.TYPE_LABEL_PROVIDER));

		return TableBuilder.row(20, fields, new CommentsWidget(issue));
	}

}
