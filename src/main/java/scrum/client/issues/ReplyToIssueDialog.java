package scrum.client.issues;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwt;
import scrum.client.admin.User;
import scrum.client.common.AScrumAction;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ReplyToIssueDialog {

	private Issue issue;

	private DialogBox dialogBox;
	private TextBox from;
	private TextBox to;
	private TextBox subject;
	private TextArea text;

	public ReplyToIssueDialog(Issue issue) {
		super();
		this.issue = issue;
	}

	public void showDialog() {
		dialogBox = new DialogBox(true, true);
		dialogBox.add(createForm());
		dialogBox.center();
		dialogBox.show();
	}

	private Widget createForm() {
		Project project = issue.getProject();
		String subjectText = project.getLabel() + " " + issue.getReference() + " " + issue.getLabel();
		User user = Scope.get().getComponent(User.class);

		String template = project.getIssueReplyTemplate();
		template = fillTemplate(template, "${issue.reference}", issue.getReference());
		template = fillTemplate(template, "${issuer.name}", issue.getIssuerName());
		template = fillTemplate(template, "${issuer.email}", issue.getIssuerEmail());
		template = fillTemplate(template, "${homepage.url}", project.getHomepageUrl());
		template = fillTemplate(template, "${user.name}", user.getName());
		template = fillTemplate(template, "${user.email}", user.getEmail());

		from = ScrumGwt.createTextBox("issueReplyFrom", project.getSupportEmail(), 400);
		to = ScrumGwt.createTextBox("issueReplyTo", issue.getIssuerEmail(), 400);
		subject = ScrumGwt.createTextBox("issueReplySubject", subjectText, 400);
		text = ScrumGwt.createTextArea("issueReplyText", template, 400, 400);

		ToolbarWidget buttonbar = new ToolbarWidget();
		buttonbar.addButton(new SendEmailAction());
		buttonbar.addHyperlink(new CancelAction());

		TableBuilder tb = ScrumGwt.createFieldTable();
		tb.addFieldRow("From", from);
		tb.addFieldRow("To", to);
		tb.addFieldRow("Subject", subject);
		tb.addFieldRow("Text", text);
		tb.addRow(buttonbar, 2);
		return tb.createTable();
	}

	private String fillTemplate(String template, String key, String value) {
		if (template == null) return null;
		if (value == null) value = "";
		return template.replace(key, value);
	}

	class SendEmailAction extends AScrumAction {

		@Override
		public String getLabel() {
			return "Send email";
		}

		@Override
		protected void onExecute() {
			new SendIssueReplyEmailServiceCall(issue.getId(), from.getText(), to.getText(), subject.getText(),
					text.getText()).execute();
			dialogBox.hide();
		}
	}

	class CancelAction extends AScrumAction {

		@Override
		public String getLabel() {
			return "Cancel";
		}

		@Override
		protected void onExecute() {
			dialogBox.hide();
		}

	}
}
