package scrum.client.issues;

import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.Gwt;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.project.Project;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class IssueListWidget extends AScrumWidget {

	public BlockListWidget<Issue> openList;
	public BlockListWidget<Issue> acceptedList;
	public BlockListWidget<Issue> suspendedList;
	public BlockListWidget<Issue> closedList;

	@Override
	protected Widget onInitialization() {
		cm.getApp().callRequestIssues();

		openList = new BlockListWidget<Issue>(IssueBlock.FACTORY);
		openList.setAutoSorter(Issue.ISSUE_DATE_COMPARATOR);

		acceptedList = new BlockListWidget<Issue>(IssueBlock.FACTORY);
		acceptedList.setAutoSorter(Issue.ACCEPT_DATE_COMPARATOR);

		suspendedList = new BlockListWidget<Issue>(IssueBlock.FACTORY);
		suspendedList.setAutoSorter(Issue.SUSPEND_DATE_COMPARATOR);

		closedList = new BlockListWidget<Issue>(IssueBlock.FACTORY);
		closedList.setAutoSorter(Issue.CLOSE_DATE_COMPARATOR);

		PagePanel pendingPage = new PagePanel();
		pendingPage.addHeader("Open Issues", new ButtonWidget(new CreateIssueAction()));
		pendingPage.addSection(openList);

		return Gwt.createFlowPanel(pendingPage, Gwt.createSpacer(1, 10), PagePanel.createSimple(
			"Accepted Issues (to do)", acceptedList), Gwt.createSpacer(1, 10), PagePanel.createSimple(
			"Suspended Issues (whishlist)", suspendedList), Gwt.createSpacer(1, 10), PagePanel.createSimple(
			"Closed Issues", closedList));
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		Project project = getCurrentProject();
		openList.setObjects(project.getOpenIssues());
		acceptedList.setObjects(project.getAcceptedIssues());
		suspendedList.setObjects(project.getSusendedIssues());
		closedList.setObjects(project.getClosedIssues());
	}

	public void showIssue(Issue issue) {
		openList.extendObject(issue);
	}

	public void select(Issue issue) {
		openList.extendObject(issue);
	}
}
