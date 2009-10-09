package scrum.client;

import ilarkesto.gwt.client.AGwtEntity;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.GwtLogger;
import ilarkesto.gwt.client.ObjectMappedFlowPanel;
import ilarkesto.gwt.client.SwitcherWidget;
import ilarkesto.gwt.client.SwitchingNavigatorWidget;

import java.util.List;

import scrum.client.admin.ProjectUserConfigWidget;
import scrum.client.admin.User;
import scrum.client.collaboration.Comment;
import scrum.client.collaboration.WikiWidget;
import scrum.client.common.AScrumComponent;
import scrum.client.context.UiComponent;
import scrum.client.context.UserHighlightSupport;
import scrum.client.img.Img;
import scrum.client.impediments.Impediment;
import scrum.client.impediments.ImpedimentListWidget;
import scrum.client.issues.Issue;
import scrum.client.issues.IssueListWidget;
import scrum.client.project.ProductBacklogWidget;
import scrum.client.project.Project;
import scrum.client.project.ProjectOverviewWidget;
import scrum.client.project.Quality;
import scrum.client.project.QualityBacklogWidget;
import scrum.client.project.Requirement;
import scrum.client.risks.Risk;
import scrum.client.risks.RiskListWidget;
import scrum.client.sprint.NextSprintWidget;
import scrum.client.sprint.SprintBacklogWidget;
import scrum.client.sprint.Task;
import scrum.client.tasks.TaskOverviewWidget;
import scrum.client.tasks.WhiteboardWidget;
import scrum.client.test.WidgetsTesterWidget;
import scrum.client.workspace.ProjectSidebarWidget;

import com.google.gwt.user.client.ui.Widget;

public class ProjectContext extends AScrumComponent implements UiComponent {

	private Project project;

	private ProjectSidebarWidget sidebar = new ProjectSidebarWidget();
	private ProjectOverviewWidget projectOverview;
	private TaskOverviewWidget taskOverview;
	private WhiteboardWidget whiteboard;
	private SprintBacklogWidget sprintBacklog;
	private ProductBacklogWidget productBacklog;
	private QualityBacklogWidget qualityBacklog;
	private NextSprintWidget nextSprint;
	private ImpedimentListWidget impedimentList;
	private IssueListWidget issueList;
	private RiskListWidget riskList;
	private WikiWidget wiki;
	private ProjectUserConfigWidget projectUserConfig;
	private WidgetsTesterWidget widgetsTester;

	private User highlightedUser;

	ProjectContext() {
		projectOverview = new ProjectOverviewWidget();
		taskOverview = new TaskOverviewWidget();
		whiteboard = new WhiteboardWidget();
		sprintBacklog = new SprintBacklogWidget();
		productBacklog = new ProductBacklogWidget();
		qualityBacklog = new QualityBacklogWidget();
		nextSprint = new NextSprintWidget();
		impedimentList = new ImpedimentListWidget();
		issueList = new IssueListWidget();
		riskList = new RiskListWidget();
		projectUserConfig = new ProjectUserConfigWidget();
		wiki = new WikiWidget();
		widgetsTester = new WidgetsTesterWidget();

		SwitchingNavigatorWidget navigator = getSidebar().getNavigator();
		navigator.addItem(Img.bundle.project16(), "Project Overview", getProjectOverview());
		navigator.addItem(Img.bundle.task16(), "Task Overview", getTaskOverview());
		navigator.addItem(Img.bundle.task16(), "Whiteboard", getWhiteboard());
		navigator.addItem(Img.bundle.sprint16(), "Sprint Backlog", getSprintBacklog());
		navigator.addItem(Img.bundle.requirement16(), "Product Backlog", getProductBacklog());
		navigator.addItem(Img.bundle.requirement16(), "Quality Backlog", getQualityBacklog());
		navigator.addItem(Img.bundle.impediment16(), "Impediment List", getImpedimentList());
		navigator.addItem(Img.bundle.issue16(), "Issue List", getIssueList());
		navigator.addItem(Img.bundle.risk16(), "Risk Management", getRiskList());
		navigator.addItem(Img.bundle.wiki16(), "Wiki", getWiki());
		navigator.addItem(Img.bundle.sprint16(), "Next Sprint", getNextSprint());
		navigator.addItem(Img.bundle.test16(), "Personal Preferences", getProjectUserConfig());
		navigator.addItem(Img.bundle.test16(), "WidgetsTester", getWidgetsTester());
	}

	@Override
	protected void onDestroy() {
		ObjectMappedFlowPanel.objectHeights.clear();
	}

	public void openProject(Project project) {
		this.project = project;

		cm.getUi().getWorkspace().lock("Loading project...");
		ScrumGwtApplication.get().callSelectProject(project.getId(), new Runnable() {

			public void run() {
				cm.getUi().getWorkspace().unlock();
				cm.getUi().getWorkspace().activateProjectView();
			}
		});

		cm.getEventBus().fireProjectOpened();
	}

	public void closeProject(boolean activateHomeView) {
		assert project != null;
		cm.getUi().getWorkspace().lock("Closing project...");
		project = null;
		ScrumGwtApplication.get().callCloseProject();
		cm.getEventBus().fireProjectClosed();
		if (activateHomeView) cm.getUi().getWorkspace().activateStartView();
	}

	public Project getProject() {
		return project;
	}

	public boolean isProjectOpen() {
		return project != null;
	}

	public List<Comment> getComments(AGwtEntity entity) {
		return cm.getDao().getCommentsByParent(entity);
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

	public Widget getSidebarWidget() {
		return sidebar;
	}

	public Widget getWorkareaWidget() {
		return projectOverview;
	}

	public void showEntity(AGwtEntity entity) {
		GwtLogger.DEBUG("Showing entity:", entity);
		if (entity instanceof Task) {
			showTask((Task) entity);
		} else if (entity instanceof Requirement) {
			showRequirement((Requirement) entity);
		} else if (entity instanceof Issue) {
			showIssue((Issue) entity);
		} else if (entity instanceof Risk) {
			showRisk((Risk) entity);
		} else if (entity instanceof Quality) {
			showQuality((Quality) entity);
		} else if (entity instanceof Impediment) {
			showImpediment((Impediment) entity);
		} else {
			throw new RuntimeException("Showing entity not supported: " + entity.getClass().getName());
		}
	}

	public void showIssue(Issue issue) {
		select(issueList);
		issueList.select(issue);
	}

	public void showQuality(Quality quality) {
		select(qualityBacklog);
		qualityBacklog.select(quality);
	}

	public void showImpediment(Impediment impediment) {
		select(impedimentList);
		impedimentList.select(impediment);
	}

	public void showRisk(Risk risk) {
		select(riskList);
		riskList.select(risk);
	}

	public void showTask(Task task) {
		if (getWorkarea().isShowing(sprintBacklog)) {
			showSprintBacklog(task);
		} else if (getWorkarea().isShowing(taskOverview)) {
			showTaskOverview(task);
		} else {
			showWhiteboard(task);
		}
	}

	public void showRequirement(Requirement requirement) {
		boolean inCurrentSprint = getCurrentProject().isCurrentSprint(requirement.getSprint());
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

	private SwitcherWidget getWorkarea() {
		return cm.getUi().getWorkspace().getWorkarea();
	}

	public void showWhiteboard(Task task) {
		select(whiteboard);
		whiteboard.selectTask(task);
	}

	public void showSprintBacklog(Task task) {
		select(sprintBacklog);
		sprintBacklog.selectTask(task);
	}

	public void showTaskOverview(Task task) {
		select(taskOverview);
		taskOverview.selectTask(task);
	}

	public void showSprintBacklog(Requirement requirement) {
		select(sprintBacklog);
		if (requirement != null) sprintBacklog.selectRequirement(requirement);
	}

	public void showProductBacklog(Requirement requirement) {
		select(productBacklog);
		productBacklog.selectRequirement(requirement);
	}

	public void showImpedimentList(Impediment impediment) {
		select(impedimentList);
		impedimentList.showImpediment(impediment);
	}

	public void showIssueList(Issue issue) {
		select(issueList);
		issueList.showIssue(issue);
	}

	public void showQualityBacklog(Quality quality) {
		select(qualityBacklog);
		qualityBacklog.showQuality(quality);
	}

	public void showRiskList(Risk risk) {
		select(riskList);
		riskList.showRisk(risk);
	}

	private void select(AWidget widget) {
		getSidebar().getNavigator().select(widget);
	}

	public WikiWidget getWiki() {
		return wiki;
	}

	public ImpedimentListWidget getImpedimentList() {
		return impedimentList;
	}

	public IssueListWidget getIssueList() {
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

	public RiskListWidget getRiskList() {
		return riskList;
	}

	public ProjectSidebarWidget getSidebar() {
		return sidebar;
	}

	public SprintBacklogWidget getSprintBacklog() {
		return sprintBacklog;
	}

	public TaskOverviewWidget getTaskOverview() {
		return taskOverview;
	}

	public WhiteboardWidget getWhiteboard() {
		return whiteboard;
	}

	public WidgetsTesterWidget getWidgetsTester() {
		return widgetsTester;
	}

}
