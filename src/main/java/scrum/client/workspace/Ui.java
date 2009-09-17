package scrum.client.workspace;

import ilarkesto.gwt.client.AGwtEntity;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.FullScreenDockWidget;
import ilarkesto.gwt.client.GwtLogger;
import ilarkesto.gwt.client.LockWidget;
import ilarkesto.gwt.client.SwitcherWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.impediments.Impediment;
import scrum.client.impediments.ImpedimentListWidget;
import scrum.client.issues.Issue;
import scrum.client.issues.IssueListWidget;
import scrum.client.project.ProductBacklogWidget;
import scrum.client.project.Quality;
import scrum.client.project.QualityBacklogWidget;
import scrum.client.project.Requirement;
import scrum.client.risks.Risk;
import scrum.client.risks.RiskListWidget;
import scrum.client.sprint.SprintBacklogWidget;
import scrum.client.sprint.Task;
import scrum.client.tasks.TaskOverviewWidget;
import scrum.client.tasks.WhiteboardWidget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * The applications root panel. It manages the top widgets (LoginWidget, ProjectSelectorWidget and
 * WorkspaceWidget). It also provides the global UI locking.
 */
public class Ui extends AWidget {

	public static final int HEADER_HEIGHT = 25;
	private static final Ui SINGLETON = new Ui();

	private LockWidget locker;
	private WaitWidget wait;
	private SwitcherWidget sidebar;
	private ProjectSidebarWidget projectSidebar;
	private WorkareaWidget workarea;

	private HeaderWidget header;

	@Override
	protected Widget onInitialization() {
		setHeight100();

		wait = new WaitWidget();
		header = new HeaderWidget();
		sidebar = new SwitcherWidget(true);
		workarea = new WorkareaWidget();

		FullScreenDockWidget dock = new FullScreenDockWidget(header, 25, sidebar, 200, workarea);

		locker = new LockWidget(dock);
		return locker;
	}

	@Override
	protected void onUpdate() {
		header.update();
		sidebar.update();
		if (projectSidebar != null) projectSidebar.update();
		workarea.update();
		locker.update();
		// TODO locker.update() is enough?
	}

	public void lock(String message) {
		GwtLogger.DEBUG("Locking UI:", message);
		wait.setMessage(message);
		locker.lock(wait);
	}

	public void unlock() {
		GwtLogger.DEBUG("Unlocking UI");
		locker.unlock();
	}

	public void showLogin() {
		sidebar.show(null);
		workarea.showLogin();
	}

	public void showProject() {
		sidebar.show(getProjectSidebar());
		workarea.showProjectOverview();
	}

	public void showConfiguration() {
		workarea.showUserconfig();
	}

	public void showStartPage() {
		sidebar.show(null);
		workarea.showStartPage();
	}

	public WorkareaWidget getWorkarea() {
		return workarea;
	}

	public ProjectSidebarWidget getProjectSidebar() {
		if (projectSidebar == null) projectSidebar = new ProjectSidebarWidget();
		return projectSidebar;
	}

	public void showError(String message) {
		final DialogBox db = new DialogBox();
		db.setSize("200", "150");
		db.setPopupPosition(100, 100);

		FlowPanel panel = new FlowPanel();
		Label text = new Label(message);
		panel.add(text);

		Button close = new Button("close");
		close.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				db.hide();
			}
		});
		panel.add(close);

		db.add(panel);
		db.show();
	}

	public static Ui get() {
		return SINGLETON;
	}

	// --- helper ---

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
		if (workarea.isCurrentWidget(workarea.getSprintBacklog())) {
			showSprintBacklog(task);
		} else if (workarea.isCurrentWidget(workarea.getTaskOverview())) {
			showTaskOverview(task);
		} else {
			showWhiteboard(task);
		}
	}

	public void showRequirement(Requirement requirement) {
		ProductBacklogWidget productBacklog = workarea.getProductBacklog();
		boolean inCurrentSprint = ScrumGwtApplication.get().getProject().isCurrentSprint(requirement.getSprint());
		if (inCurrentSprint) {
			if (workarea.isCurrentWidget(productBacklog)) {
				showProductBacklog(requirement);
			} else {
				showSprintBacklog(requirement);
			}
		} else {
			showProductBacklog(requirement);
		}
	}

	public void showWhiteboard(Task task) {
		WhiteboardWidget whiteboard = workarea.getWhiteboard();
		select(whiteboard);
		whiteboard.selectTask(task);
	}

	public void showSprintBacklog(Task task) {
		SprintBacklogWidget sprintBacklog = workarea.getSprintBacklog();
		select(sprintBacklog);
		sprintBacklog.selectTask(task);
	}

	public void showTaskOverview(Task task) {
		TaskOverviewWidget taskOverview = workarea.getTaskOverview();
		select(taskOverview);
		taskOverview.selectTask(task);
	}

	public void showSprintBacklog(Requirement requirement) {
		SprintBacklogWidget sprintBacklog = workarea.getSprintBacklog();
		select(sprintBacklog);
		if (requirement != null) sprintBacklog.selectRequirement(requirement);
	}

	public void showProductBacklog(Requirement requirement) {
		ProductBacklogWidget productBacklog = workarea.getProductBacklog();
		select(productBacklog);
		productBacklog.selectRequirement(requirement);
	}

	public void showImpedimentList(Impediment impediment) {
		ImpedimentListWidget impedimentList = workarea.getImpedimentList();
		select(impedimentList);
		impedimentList.showImpediment(impediment);
	}

	public void showIssueList(Issue issue) {
		IssueListWidget issueList = workarea.getIssueList();
		select(issueList);
		issueList.showIssue(issue);
	}

	public void showQualityBacklog(Quality quality) {
		QualityBacklogWidget qualityBacklog = workarea.getQualityBacklog();
		select(qualityBacklog);
		qualityBacklog.showQuality(quality);
	}

	public void showRiskList(Risk risk) {
		RiskListWidget riskList = workarea.getRiskList();
		select(riskList);
		riskList.showRisk(risk);
	}

	private void select(AWidget widget) {
		projectSidebar.getNavigator().select(widget);
	}
}
