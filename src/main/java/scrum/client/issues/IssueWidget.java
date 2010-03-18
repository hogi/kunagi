package scrum.client.issues;

import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.ScrumGwt;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.journal.ChangeHistoryWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class IssueWidget extends AScrumWidget {

	private Issue issue;

	public IssueWidget(Issue issue) {
		super();
		this.issue = issue;
	}

	@Override
	protected Widget onInitialization() {
		TableBuilder left = ScrumGwt.createFieldTable();
		left.addFieldRow("Label", issue.getLabelModel());
		left.addFieldRow("Description", issue.getDescriptionModel());
		left.addFieldRow("Statement", issue.getStatementModel());
		left.addFieldRow("My emoticon", issue.createCurrentUserEmotionEditor());
		left.addRow(new ChangeHistoryWidget(issue), 2);

		FlowPanel right = new FlowPanel();
		if (issue.isOpen() && getCurrentProject().isProductOwnerOrScrumMaster(getCurrentUser())) {
			right.add(createActionsPanelForOpenIssue());
			right.add(ScrumGwt.createSpacer(1, 10));
		}
		right.add(new CommentsWidget(issue));

		return TableBuilder.row(20, left.createTable(), right);
	}

	private Widget createActionsPanelForOpenIssue() {
		return ScrumGwt.createActionsPanel(new Label("This issue is open. Product Owner needs to decide."), Gwt
				.createSpacer(1, 10), TableBuilder.row(false, 10, new ButtonWidget(new AcceptIssueAction(issue)),
			new ButtonWidget(new AcceptUrgentIssueAction(issue)), new ButtonWidget(new CloseIssueAction(issue))));
	}
}
