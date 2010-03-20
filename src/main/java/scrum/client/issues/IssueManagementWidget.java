package scrum.client.issues;

import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.Gwt;

import java.util.List;

import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListSelectionManager;
import scrum.client.common.BlockListWidget;
import scrum.client.project.Project;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class IssueManagementWidget extends AScrumWidget {

	public BlockListWidget<Issue> openList;
	public BlockListWidget<Issue> urgentList;
	public BlockListWidget<Issue> acceptedList;
	public BlockListWidget<Issue> closedList;
	private BlockListSelectionManager selectionManager;

	@Override
	protected Widget onInitialization() {
		getApp().callRequestIssues();

		selectionManager = new BlockListSelectionManager();

		openList = new BlockListWidget<Issue>(IssueBlock.FACTORY);
		openList.setSelectionManager(selectionManager);
		openList.setAutoSorter(Issue.ISSUE_DATE_COMPARATOR);

		urgentList = new BlockListWidget<Issue>(IssueBlock.FACTORY);
		urgentList.setSelectionManager(selectionManager);
		urgentList.setAutoSorter(getCurrentProject().getIssuesOrderComparator());
		urgentList.setDndSorting(true);
		urgentList.setMoveObserver(new UrgentMoveObserver());

		acceptedList = new BlockListWidget<Issue>(IssueBlock.FACTORY);
		acceptedList.setSelectionManager(selectionManager);
		acceptedList.setAutoSorter(Issue.ACCEPT_DATE_COMPARATOR);

		closedList = new BlockListWidget<Issue>(IssueBlock.FACTORY);
		closedList.setSelectionManager(selectionManager);
		closedList.setAutoSorter(Issue.CLOSE_DATE_COMPARATOR);

		PagePanel pendingPage = new PagePanel();
		pendingPage.addHeader("issue inbox (decision required)", new ButtonWidget(new CreateIssueAction()));
		pendingPage.addSection(openList);

		return Gwt.createFlowPanel(pendingPage, Gwt.createSpacer(1, 10), PagePanel.createSimple(
			"urgent issues (Team needs to fix this)", urgentList), Gwt.createSpacer(1, 10), PagePanel.createSimple(
			"accepted issues (ideas and candidates for stories)", acceptedList), Gwt.createSpacer(1, 10), PagePanel
				.createSimple("closed issues (done or rejected)", closedList));
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		Project project = getCurrentProject();
		openList.setObjects(project.getOpenIssues());
		urgentList.setObjects(project.getUrgentIssues());
		acceptedList.setObjects(project.getAcceptedNonUrgentIssues());
		closedList.setObjects(project.getClosedIssues());
	}

	public boolean select(Issue issue) {
		update();
		return selectionManager.select(issue);
	}

	class UrgentMoveObserver implements Runnable {

		public void run() {
			List<Issue> issues = urgentList.getObjects();
			getCurrentProject().updateUrgentIssuesOrder(issues);
			update();
		}

	}
}
