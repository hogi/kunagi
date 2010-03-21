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
	private boolean fixedOnInitialization;

	public IssueWidget(Issue issue) {
		super();
		this.issue = issue;
	}

	@Override
	protected Widget onInitialization() {
		fixedOnInitialization = issue.isFixed();

		TableBuilder left = ScrumGwt.createFieldTable();
		left.addFieldRow("Label", issue.getLabelModel());
		left.addFieldRow("Description", issue.getDescriptionModel());
		left.addFieldRow("Statement", issue.getStatementModel());
		left.addFieldRow("My emoticon", issue.createCurrentUserEmotionEditor());
		left.addRow(new ChangeHistoryWidget(issue), 2);

		FlowPanel right = new FlowPanel();
		if (issue.isOpen() && issue.getProject().isProductOwner(getCurrentUser())) {
			right.add(createActionsPanelForOpenIssue());
			right.add(ScrumGwt.createSpacer(1, 10));
		} else if (issue.isUrgent() && issue.isFixed() && issue.getProject().isProductOwner(getCurrentUser())) {
			right.add(createActionsPanelForFixedIssue());
			right.add(ScrumGwt.createSpacer(1, 10));
		}
		right.add(new CommentsWidget(issue));

		return TableBuilder.row(20, left.createTable(), right);
	}

	@Override
	protected boolean isResetRequired() {
		return fixedOnInitialization && !issue.isFixed();
	}

	private Widget createActionsPanelForOpenIssue() {
		TableBuilder tb = new TableBuilder();
		tb.setWidth(null);
		tb.setColumnWidths("30%", "10", "30%", "10", "30%");

		tb.addRow(new Label("This issue is open. As Product Owner, you have to decide:"), 5);
		tb.addRow(Gwt.createSpacer(1, 10));

		tb.add(new ButtonWidget(new AcceptIssueAction(issue)));
		tb.add(Gwt.createSpacer(10, 1));
		tb.add(new ButtonWidget(new AcceptUrgentIssueAction(issue)));
		tb.add(Gwt.createSpacer(10, 1));
		tb.add(new ButtonWidget(new CloseIssueAction(issue)));
		tb.nextRow();
		tb.addRow(Gwt.createSpacer(1, 10));

		tb.add(new Label("If this issue makes sense for the future."));
		tb.add(Gwt.createSpacer(10, 1));
		tb.add(new Label("If this issue needs to be fixed immediately by the team."));
		tb.add(Gwt.createSpacer(10, 1));
		tb.add(new Label("If this issue makes no sense, is a duplicate or is already fixed."));

		return ScrumGwt.createActionsPanel(tb.createTable());
	}

	private Widget createActionsPanelForFixedIssue() {
		TableBuilder tb = new TableBuilder();
		tb.setWidth(null);
		tb.setColumnWidths("48%", "10", "48%");

		tb.addRow(new Label("This issue is fixed. As Product Owner, you have to decide:"), 3);
		tb.addRow(Gwt.createSpacer(1, 10));

		tb.add(new ButtonWidget(new CloseIssueAction(issue)));
		tb.add(Gwt.createSpacer(10, 1));
		tb.add(new ButtonWidget(new RejectFixIssueAction(issue)));
		tb.nextRow();
		tb.addRow(Gwt.createSpacer(1, 10));

		tb.add(new Label("If this issue is fixed sufficiently."));
		tb.add(Gwt.createSpacer(10, 1));
		tb.add(new Label("If this issue is fixed insufficient. Please provide a comment in this case."));

		return ScrumGwt.createActionsPanel(tb.createTable());
	}
}
