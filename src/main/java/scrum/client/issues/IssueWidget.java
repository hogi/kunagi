package scrum.client.issues;

import ilarkesto.gwt.client.AMultiSelectionViewEditWidget;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.AEditorModel;
import ilarkesto.gwt.client.editor.DropdownEditorWidget;
import ilarkesto.gwt.client.editor.TextOutputWidget;
import scrum.client.ScrumGwt;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.collaboration.EmoticonSelectorWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.journal.ChangeHistoryWidget;
import scrum.client.release.Release;

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
		if (issue.isBug()) {
			left.addFieldRow("Severity", new DropdownEditorWidget<Integer>(issue.getSeverityModel(),
					Issue.SEVERITY_LABELS));
		}
		left.addFieldRow("Description", issue.getDescriptionModel());
		left.addFieldRow("Issuer", new TextOutputWidget(new AEditorModel<String>() {

			@Override
			public String getValue() {
				return issue.getIssuer();
			}

			@Override
			public void setValue(String value) {}
		}));
		left.addFieldRow("Statement", issue.getStatementModel());
		left.addFieldRow("Affected releases", new AMultiSelectionViewEditWidget<Release>() {

			@Override
			protected void onViewerUpdate() {
				setViewerItems(issue.getAffectedReleases());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorItems(issue.getProject().getReleases());
				setEditorSelectedItems(issue.getAffectedReleases());
			}

			@Override
			protected void onEditorSubmit() {
				issue.setAffectedReleases(getEditorSelectedItems());
			}

			@Override
			public boolean isEditable() {
				return true;
			}
		});
		left.addFieldRow("Fix releases", new AMultiSelectionViewEditWidget<Release>() {

			@Override
			protected void onViewerUpdate() {
				setViewerItems(issue.getFixReleases());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorItems(issue.getProject().getReleases());
				setEditorSelectedItems(issue.getFixReleases());
			}

			@Override
			protected void onEditorSubmit() {
				issue.setFixReleases(getEditorSelectedItems());
			}

			@Override
			public boolean isEditable() {
				return true;
			}
		});
		left.addRow(new ChangeHistoryWidget(issue), 2);

		TableBuilder right = ScrumGwt.createFieldTable();
		if (issue.isOpen() && issue.getProject().isProductOwner(getCurrentUser())) {
			right.addRow(createActionsPanelForOpenIssue(), 2);
			right.addRow(ScrumGwt.createSpacer(1, 10), 2);
		} else if (issue.isBug() && issue.isFixed() && issue.getProject().isProductOwner(getCurrentUser())) {
			right.addRow(createActionsPanelForFixedIssue(), 2);
			right.addRow(ScrumGwt.createSpacer(1, 10), 2);
		}
		right.addFieldRow("My emoticon", new EmoticonSelectorWidget(issue));
		right.addRow(new CommentsWidget(issue), 2);

		return TableBuilder.row(20, left.createTable(), right.createTable());
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

		tb.add(new ButtonWidget(new AcceptIssueAsIdeaAction(issue)));
		tb.add(Gwt.createSpacer(10, 1));
		tb.add(new ButtonWidget(new AcceptIssueAsBugAction(issue)));
		tb.add(Gwt.createSpacer(10, 1));
		tb.add(new ButtonWidget(new CloseIssueAction(issue)));
		tb.nextRow();
		tb.addRow(Gwt.createSpacer(1, 10));

		tb.add(new Label("If this Issue is an idea, which needs to by converted to a Story by the Product Owner."));
		tb.add(Gwt.createSpacer(10, 1));
		tb.add(new Label("If this Issue is a bug, which needs to be fixed by the Team."));
		tb.add(Gwt.createSpacer(10, 1));
		tb.add(new Label("If this Issue makes no sense, is a duplicate or is already fixed."));

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
