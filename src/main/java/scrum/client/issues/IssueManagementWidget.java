package scrum.client.issues;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.Gwt;

import java.util.List;

import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListSelectionManager;
import scrum.client.common.BlockListWidget;
import scrum.client.common.UserGuideWidget;
import scrum.client.project.Project;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class IssueManagementWidget extends AScrumWidget {

	private IssueManager issueManager;

	public BlockListWidget<Issue> openList;
	public BlockListWidget<Issue> bugList;
	public BlockListWidget<Issue> ideaList;
	public BlockListWidget<Issue> closedList;
	private BlockListSelectionManager selectionManager;
	private SimplePanel suspensionStatusButtonWrapper;

	@Override
	protected Widget onInitialization() {
		new RequestAcceptedIssuesServiceCall().execute();
		issueManager = Scope.get().getComponent(IssueManager.class);

		selectionManager = new BlockListSelectionManager();

		openList = new BlockListWidget<Issue>(IssueBlock.FACTORY);
		openList.setSelectionManager(selectionManager);
		openList.setAutoSorter(Issue.ISSUE_DATE_COMPARATOR);

		bugList = new BlockListWidget<Issue>(IssueBlock.FACTORY);
		bugList.setSelectionManager(selectionManager);
		bugList.setAutoSorter(Issue.SEVERITY_COMPARATOR);
		// bugList.setAutoSorter(getCurrentProject().getIssuesOrderComparator());
		// bugList.setDndSorting(true);
		// bugList.setMoveObserver(new UrgentMoveObserver());

		ideaList = new BlockListWidget<Issue>(IssueBlock.FACTORY);
		ideaList.setSelectionManager(selectionManager);
		ideaList.setAutoSorter(Issue.ACCEPT_DATE_COMPARATOR);

		closedList = new BlockListWidget<Issue>(IssueBlock.FACTORY);
		closedList.setSelectionManager(selectionManager);
		closedList.setAutoSorter(Issue.CLOSE_DATE_COMPARATOR);

		suspensionStatusButtonWrapper = new SimplePanel();

		PagePanel inboxPage = new PagePanel();
		inboxPage.addHeader("issue inbox (decision required)", new ButtonWidget(new CreateIssueAction()),
			suspensionStatusButtonWrapper);
		inboxPage.addSection(openList);

		PagePanel documentationPage = new PagePanel();
		documentationPage.addSection(new UserGuideWidget(getLocalizer().views().issues(), getCurrentProject()
				.getIssues().size() < 15, getCurrentUser().getHideUserGuideIssuesModel()));

		return Gwt.createFlowPanel(inboxPage, Gwt.createSpacer(1, 10),
			PagePanel.createSimple("bugs (Team needs to fix this)", bugList), Gwt.createSpacer(1, 10),
			PagePanel.createSimple("ideas (Product owner needs to create stories)", ideaList), Gwt.createSpacer(1, 10),
			createClosedPage(), Gwt.createSpacer(1, 10), documentationPage);
	}

	private Widget createClosedPage() {
		PagePanel page = new PagePanel();
		page.addHeader("closed issues (done or rejected)", new ButtonWidget(new RequestClosedIssuesAction()));
		page.addSection(closedList);
		return page;
	}

	@Override
	protected void onUpdate() {
		suspensionStatusButtonWrapper.setWidget(new ButtonWidget(
				issueManager.isSuspendedIssuesVisible() ? new HideSuspendedIssuesAction()
						: new ShowSuspendedIssuesAction()));

		Project project = getCurrentProject();
		openList.setObjects(project.getOpenIssues(issueManager.isSuspendedIssuesVisible()));
		bugList.setObjects(project.getBugs());
		ideaList.setObjects(project.getIdeas());
		closedList.setObjects(project.getClosedIssues());
		super.onUpdate();
	}

	public boolean select(Issue issue) {
		update();
		return selectionManager.select(issue);
	}

	class UrgentMoveObserver implements Runnable {

		public void run() {
			List<Issue> issues = bugList.getObjects();
			getCurrentProject().updateUrgentIssuesOrder(issues);
			update();
		}

	}
}
