package scrum.client.workspace;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.AGwtEntity;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.SwitcherWidget;
import ilarkesto.gwt.client.SwitchingNavigatorWidget;
import scrum.client.admin.ProjectUserConfigWidget;
import scrum.client.admin.PunishmentsWidget;
import scrum.client.admin.User;
import scrum.client.calendar.CalendarWidget;
import scrum.client.calendar.SimpleEvent;
import scrum.client.collaboration.Chat;
import scrum.client.collaboration.ForumSupport;
import scrum.client.collaboration.ForumWidget;
import scrum.client.collaboration.Subject;
import scrum.client.collaboration.WikiWidget;
import scrum.client.collaboration.Wikipage;
import scrum.client.context.UserHighlightSupport;
import scrum.client.dashboard.DashboardWidget;
import scrum.client.files.File;
import scrum.client.files.FileRepositoryWidget;
import scrum.client.impediments.Impediment;
import scrum.client.impediments.ImpedimentListWidget;
import scrum.client.issues.Issue;
import scrum.client.issues.IssueManagementWidget;
import scrum.client.journal.JournalWidget;
import scrum.client.project.ProductBacklogWidget;
import scrum.client.project.Project;
import scrum.client.project.ProjectOverviewWidget;
import scrum.client.project.Quality;
import scrum.client.project.QualityBacklogWidget;
import scrum.client.project.Requirement;
import scrum.client.risks.Risk;
import scrum.client.risks.RiskListWidget;
import scrum.client.search.Search;
import scrum.client.search.SearchResultsWidget;
import scrum.client.sprint.NextSprintWidget;
import scrum.client.sprint.Sprint;
import scrum.client.sprint.SprintBacklogWidget;
import scrum.client.sprint.SprintHistoryWidget;
import scrum.client.sprint.Task;
import scrum.client.tasks.WhiteboardWidget;

import com.google.gwt.user.client.ui.Widget;

public class ProjectWorkspaceWidgets extends GProjectWorkspaceWidgets {

	private ProjectSidebarWidget sidebar = new ProjectSidebarWidget();
	private DashboardWidget dashboard;
	private ProjectOverviewWidget projectOverview;
	private WhiteboardWidget whiteboard;
	private SprintBacklogWidget sprintBacklog;
	private ProductBacklogWidget productBacklog;
	private QualityBacklogWidget qualityBacklog;
	private ForumWidget forum;
	private CalendarWidget calendar;
	private NextSprintWidget nextSprint;
	private ImpedimentListWidget impedimentList;
	private IssueManagementWidget issueList;
	private RiskListWidget riskList;
	private WikiWidget wiki;
	private SprintHistoryWidget sprintHistory;
	private ProjectUserConfigWidget projectUserConfig;
	private PunishmentsWidget punishments;
	private JournalWidget projectEventList;
	private FileRepositoryWidget fileRepository;

	private boolean searchResultsAdded;

	private User highlightedUser;

	public ProjectWorkspaceWidgets() {
		projectOverview = new ProjectOverviewWidget();
		dashboard = new DashboardWidget();
		whiteboard = new WhiteboardWidget();
		sprintBacklog = new SprintBacklogWidget();
		productBacklog = new ProductBacklogWidget();
		qualityBacklog = new QualityBacklogWidget();
		forum = new ForumWidget();
		calendar = new CalendarWidget();
		nextSprint = new NextSprintWidget();
		impedimentList = new ImpedimentListWidget();
		issueList = new IssueManagementWidget();
		riskList = new RiskListWidget();
		projectUserConfig = new ProjectUserConfigWidget();
		sprintHistory = new SprintHistoryWidget();
		wiki = new WikiWidget();
		punishments = new PunishmentsWidget();
		projectEventList = new JournalWidget();
		fileRepository = new FileRepositoryWidget();

		SwitchingNavigatorWidget navigator = getSidebar().getNavigator();
		navigator.addItem("Dashboard", dashboard);

		String sprintGroupKey = "sprint";
		navigator.addGroup("Sprint", sprintGroupKey);
		navigator.addItem(sprintGroupKey, "Sprint Backlog", getSprintBacklog());
		navigator.addItem(sprintGroupKey, "Whiteboard", getWhiteboard());

		String productGroupKey = "product";
		navigator.addGroup("Product", productGroupKey);
		navigator.addItem(productGroupKey, "Product Backlog", getProductBacklog());
		navigator.addItem(productGroupKey, "Quality Backlog", getQualityBacklog());
		navigator.addItem(productGroupKey, "Issue Management", getIssueList());

		String projectGroupKey = "project";
		navigator.addGroup("Project", projectGroupKey);
		navigator.addItem(projectGroupKey, "Impediment List", getImpedimentList());
		navigator.addItem(projectGroupKey, "Risk Management", getRiskList());
		navigator.addItem(projectGroupKey, "Project Journal", getProjectEventList());
		navigator.addItem(projectGroupKey, "Next Sprint", getNextSprint());
		navigator.addItem(projectGroupKey, "Sprint History", getSprintHistory());

		String collaborationGroupKey = "collaboration";
		navigator.addGroup("Collaboration", collaborationGroupKey);
		navigator.addItem(collaborationGroupKey, "Forum", getForum());
		navigator.addItem(collaborationGroupKey, "Wiki", getWiki());
		navigator.addItem(collaborationGroupKey, "Calendar", calendar);
		navigator.addItem(collaborationGroupKey, "File Repository", fileRepository);
		navigator.addItem(collaborationGroupKey, "Courtroom", punishments);

		navigator.addItem("Personal Preferences", getProjectUserConfig());
	}

	public void activate() {
		Scope.get().getComponent(Ui.class).show(sidebar, dashboard);
	}

	public void highlightUser(User user) {
		if (highlightedUser == user) return;
		Widget currentWidget = getWorkarea().getCurrentWidget();
		if (currentWidget instanceof UserHighlightSupport) {
			((UserHighlightSupport) currentWidget).highlightUser(user);
		}
		highlightedUser = user;
	}

	public ProjectUserConfigWidget getProjectUserConfig() {
		return projectUserConfig;
	}

	public void showSearchResults() {
		SwitchingNavigatorWidget navigator = getSidebar().getNavigator();
		SearchResultsWidget results = Scope.get().getComponent(Search.class).getResultsWidget();
		if (!searchResultsAdded) {
			navigator.addItem("Search Results", results);
			searchResultsAdded = true;
		}
		navigator.select(results);
	}

	public void showEntityByReference(final String reference) {
		log.debug("Showing entity by reference:", reference);

		AGwtEntity entity = dao.getEntityByReference(reference);
		if (entity != null) {
			showEntity(entity);
			return;
		}
		Scope.get().getComponent(Ui.class).lock("Searching for " + reference);
		app.callRequestEntityByReference(reference, new Runnable() {

			public void run() {
				AGwtEntity entity = dao.getEntityByReference(reference);
				Ui ui = Scope.get().getComponent(Ui.class);
				if (entity == null) {
					ui.unlock();
					Scope.get().getComponent(Chat.class)
							.postSystemMessage("Object does not exist: " + reference, false);
					return;
				}
				ui.unlock();
				showEntity(entity);
			}
		});
	}

	public void showEntityById(final String entityId) {
		log.debug("Showing entity by id:", entityId);

		AGwtEntity entity = dao.getEntity(entityId);
		if (entity != null) {
			showEntity(entity);
			return;
		}
		Scope.get().getComponent(Ui.class).lock("Searching for " + entityId);
		app.callRequestEntity(entityId, new Runnable() {

			public void run() {
				AGwtEntity entity = dao.getEntity(entityId);
				Ui ui = Scope.get().getComponent(Ui.class);
				if (entity == null) {
					ui.unlock();
					Scope.get().getComponent(Chat.class).postSystemMessage("Entity does not exist: " + entityId, false);
					return;
				}
				ui.unlock();
				showEntity(entity);
			}
		});
	}

	public void showEntity(AGwtEntity entity) {
		log.debug("Showing entity:", entity);
		if (entity instanceof Task) {
			showTask((Task) entity);
		} else if (entity instanceof Requirement) {
			showRequirement((Requirement) entity);
		} else if (entity instanceof Issue) {
			showIssue((Issue) entity);
		} else if (entity instanceof Risk) {
			showRisk((Risk) entity);
		} else if (entity instanceof Quality) {
			showQualityBacklog((Quality) entity);
		} else if (entity instanceof Subject) {
			showForum((Subject) entity);
		} else if (entity instanceof Impediment) {
			showImpediment((Impediment) entity);
		} else if (entity instanceof File) {
			showFile((File) entity);
		} else if (entity instanceof Sprint) {
			showSprint((Sprint) entity);
		} else if (entity instanceof Wikipage) {
			showWiki((Wikipage) entity);
		} else if (entity instanceof SimpleEvent) {
			showCalendar((SimpleEvent) entity);
		} else if (entity instanceof Project) {
			showDashboard();
		} else {
			throw new RuntimeException("Showing entity not supported: " + entity.getClass().getName());
		}
	}

	public void showDashboard() {
		select(dashboard);
	}

	public void showSprint(Sprint sprint) {
		if (sprint.isCurrent()) {
			showSprintBacklog((Requirement) null);
		} else {
			showSprintHistory(sprint);
		}
	}

	public void showSprintHistory(Sprint sprint) {
		select(sprintHistory);
		sprintHistory.select(sprint);
	}

	public void showIssue(Issue issue) {
		select(issueList);
		issueList.select(issue);
	}

	public void showImpediment(Impediment impediment) {
		select(impedimentList);
		impedimentList.select(impediment);
	}

	public void showFile(File file) {
		select(fileRepository);
		fileRepository.select(file);
	}

	public void showRisk(Risk risk) {
		select(riskList);
		riskList.select(risk);
	}

	public void showTask(Task task) {
		if (getWorkarea().isShowing(whiteboard)) {
			showWhiteboard(task);
		} else {
			showSprintBacklog(task);
		}
	}

	public void showRequirement(Requirement requirement) {
		boolean inCurrentSprint = requirement.isInCurrentSprint();
		if (inCurrentSprint) {
			if (getWorkarea().isShowing(productBacklog)) {
				showProductBacklog(requirement);
			} else {
				showSprintBacklog(requirement);
			}
		} else {
			showProductBacklog(requirement);
		}
	}

	public void showWiki(String page) {
		select(wiki);
		if (page != null) wiki.showPage(page);
	}

	public void showWiki(Wikipage page) {
		select(wiki);
		if (page != null) wiki.showPage(page);
	}

	private SwitcherWidget getWorkarea() {
		return Scope.get().getComponent(Ui.class).getWorkspace().getWorkarea();
	}

	public void showWhiteboard(Task task) {
		select(whiteboard);
		whiteboard.selectTask(task);
	}

	public void showSprintBacklog(Task task) {
		select(sprintBacklog);
		sprintBacklog.selectTask(task);
	}

	public void showSprintBacklog(Requirement requirement) {
		select(sprintBacklog);
		if (requirement != null) sprintBacklog.selectRequirement(requirement);
	}

	public void showProductBacklog(Requirement requirement) {
		select(productBacklog);
		productBacklog.select(requirement);
	}

	public void showImpedimentList(Impediment impediment) {
		select(impedimentList);
		impedimentList.select(impediment);
	}

	public void showForum(ForumSupport entity) {
		select(forum);
		forum.select(entity);
	}

	public void showIssueList(Issue issue) {
		select(issueList);
		issueList.select(issue);
	}

	public void showQualityBacklog(Quality quality) {
		select(qualityBacklog);
		qualityBacklog.select(quality);
	}

	public void showRiskList(Risk risk) {
		select(riskList);
		riskList.select(risk);
	}

	public void showCalendar(SimpleEvent event) {
		select(calendar);
		calendar.showEvent(event);
	}

	private void select(AWidget widget) {
		getSidebar().getNavigator().select(widget);
	}

	public WikiWidget getWiki() {
		return wiki;
	}

	public SprintHistoryWidget getSprintHistory() {
		return sprintHistory;
	}

	public CalendarWidget getCalendar() {
		return calendar;
	}

	public JournalWidget getProjectEventList() {
		return projectEventList;
	}

	public ImpedimentListWidget getImpedimentList() {
		return impedimentList;
	}

	public FileRepositoryWidget getFileRepository() {
		return fileRepository;
	}

	public IssueManagementWidget getIssueList() {
		return issueList;
	}

	public NextSprintWidget getNextSprint() {
		return nextSprint;
	}

	public ProductBacklogWidget getProductBacklog() {
		return productBacklog;
	}

	public ProjectOverviewWidget getProjectOverview() {
		return projectOverview;
	}

	public QualityBacklogWidget getQualityBacklog() {
		return qualityBacklog;
	}

	public ForumWidget getForum() {
		return forum;
	}

	public RiskListWidget getRiskList() {
		return riskList;
	}

	public ProjectSidebarWidget getSidebar() {
		return sidebar;
	}

	public SprintBacklogWidget getSprintBacklog() {
		return sprintBacklog;
	}

	public WhiteboardWidget getWhiteboard() {
		return whiteboard;
	}

}
