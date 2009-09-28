package scrum.client.context;

import ilarkesto.gwt.client.AGwtEntity;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.SwitcherWidget;
import ilarkesto.gwt.client.SwitchingNavigatorWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.admin.User;
import scrum.client.admin.UserConfigWidget;
import scrum.client.img.Img;
import scrum.client.impediments.Impediment;
import scrum.client.impediments.ImpedimentListWidget;
import scrum.client.issues.Issue;
import scrum.client.issues.IssueListWidget;
import scrum.client.project.ProductBacklogWidget;
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
import scrum.client.workspace.Ui;

import com.google.gwt.user.client.ui.Widget;

public class ProjectContext extends AContext {

	private static ProjectContext singleton;

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
	private WidgetsTesterWidget widgetsTester;
	private UserConfigWidget projectUserConfig;

	private User highlightedUser;

	public ProjectContext() {
		assert singleton == null;
		singleton = this;

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
		widgetsTester = new WidgetsTesterWidget();
		projectUserConfig = new UserConfigWidget();

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
		navigator.addItem(Img.bundle.sprint16(), "Next Sprint", getNextSprint());
		navigator.addItem(Img.bundle.test16(), "Personal Preferences", getProjectUserConfig());
		navigator.addItem(Img.bundle.test16(), "WidgetsTester", getWidgetsTester());
	}

	public void highlightUser(User user) {
		if (highlightedUser == user) return;
		Widget currentWidget = Ui.get().getWorkarea().getCurrentWidget();
		if (currentWidget instanceof UserHighlightSupport) {
			((UserHighlightSupport) currentWidget).highlightUser(user);
		}
		highlightedUser = user;
	}

	public UserConfigWidget getProjectUserConfig() {
		return projectUserConfig;
	}

	@Override
	public Widget getSidebarWidget() {
		return sidebar;
	}

	@Override
	public Widget getWorkareaWidget() {
		return projectOverview;
	}

	public void showEntity(AGwtEntity entity) {
		if (entity instanceof Task) {
			showTask((Task) entity);
		} else if (entity instanceof Requirement) {
			showRequirement((Requirement) entity);
		} else {
			throw new RuntimeException("Showing entity not supported: " + entity.getClass().getName());
		}
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
		boolean inCurrentSprint = ScrumGwtApplication.get().getProject().isCurrentSprint(requirement.getSprint());
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

	private SwitcherWidget getWorkarea() {
		return Ui.get().getWorkarea();
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

	public static boolean isActive() {
		return singleton != null;
	}

	public static ProjectContext get() {
		assert singleton != null;
		return singleton;
	}

	public static void destroy() {
		singleton = null;
	}

}
