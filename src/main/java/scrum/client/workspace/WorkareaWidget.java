package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.SwitcherWidget;
import ilarkesto.gwt.client.SwitchingNavigatorWidget;
import scrum.client.admin.LoginWidget;
import scrum.client.img.Img;
import scrum.client.impediments.ImpedimentListWidget;
import scrum.client.issues.IssueListWidget;
import scrum.client.project.ProductBacklogWidget;
import scrum.client.project.ProjectOverviewWidget;
import scrum.client.project.QualityBacklogWidget;
import scrum.client.risks.RiskListWidget;
import scrum.client.sprint.NextSprintWidget;
import scrum.client.sprint.SprintBacklogWidget;
import scrum.client.tasks.TaskOverviewWidget;
import scrum.client.tasks.WhiteboardWidget;
import scrum.client.test.WidgetsTesterWidget;

import com.google.gwt.user.client.ui.Widget;

public class WorkareaWidget extends AWidget {

	private SwitcherWidget switcher;

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
	private LoginWidget login;
	private StartPageWidget startPage;
	private UserConfigWidget userconfig;

	@Override
	protected Widget onInitialization() {
		setHeight100();
		switcher = new SwitcherWidget(true);

		SwitchingNavigatorWidget navigator = ProjectSidebarWidget.get().getNavigator();
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
		navigator.addItem(Img.bundle.test16(), "WidgetsTester", getWidgetsTester());

		return switcher;
	}

	public SwitcherWidget getSwitcher() {
		return switcher;
	}

	@Override
	protected void onUpdate() {
		switcher.update();
	}

	public boolean isCurrentWidget(AWidget widget) {
		return switcher.isShowing(widget);
	}

	void showLogin() {
		login = null;
		show(getLogin());
	}

	void showUserconfig() {
		getUserconfig().setPrevWidget(switcher.getCurrentWidget());
		show(getUserconfig());
	}

	void showStartPage() {
		show(getStartPage());
	}

	public void showProjectOverview() {
		ProjectSidebarWidget.get().getNavigator().select(getProjectOverview());
	}

	public boolean isProjectOverview() {
		return isCurrentWidget(projectOverview);
	}

	public void show(Widget widget) {
		switcher.show(widget);
		Ui.get().unlock();
	}

	public LoginWidget getLogin() {
		if (login == null) login = new LoginWidget();
		return login;
	}

	public UserConfigWidget getUserconfig() {
		if (userconfig == null) userconfig = new UserConfigWidget();
		return userconfig;
	}

	public StartPageWidget getStartPage() {
		if (startPage == null) startPage = new StartPageWidget();
		return startPage;
	}

	public ProjectOverviewWidget getProjectOverview() {
		if (projectOverview == null) projectOverview = new ProjectOverviewWidget();
		return projectOverview;
	}

	public TaskOverviewWidget getTaskOverview() {
		if (taskOverview == null) taskOverview = new TaskOverviewWidget();
		return taskOverview;
	}

	public WhiteboardWidget getWhiteboard() {
		if (whiteboard == null) whiteboard = new WhiteboardWidget();
		return whiteboard;
	}

	public SprintBacklogWidget getSprintBacklog() {
		if (sprintBacklog == null) sprintBacklog = new SprintBacklogWidget();
		return sprintBacklog;
	}

	public NextSprintWidget getNextSprint() {
		if (nextSprint == null) nextSprint = new NextSprintWidget();
		return nextSprint;
	}

	public ProductBacklogWidget getProductBacklog() {
		if (productBacklog == null) productBacklog = new ProductBacklogWidget();
		return productBacklog;
	}

	public QualityBacklogWidget getQualityBacklog() {
		if (qualityBacklog == null) qualityBacklog = new QualityBacklogWidget();
		return qualityBacklog;
	}

	public ImpedimentListWidget getImpedimentList() {
		if (impedimentList == null) impedimentList = new ImpedimentListWidget();
		return impedimentList;
	}

	public IssueListWidget getIssueList() {
		if (issueList == null) issueList = new IssueListWidget();
		return issueList;
	}

	public RiskListWidget getRiskList() {
		if (riskList == null) riskList = new RiskListWidget();
		return riskList;
	}

	public WidgetsTesterWidget getWidgetsTester() {
		if (widgetsTester == null) widgetsTester = new WidgetsTesterWidget();
		return widgetsTester;
	}

	public static WorkareaWidget get() {
		return Ui.get().getWorkarea();
	}
}
