package scrum.client.workspace;

import ilarkesto.gwt.client.AGwtEntity;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.GwtLogger;
import ilarkesto.gwt.client.LockWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.admin.LoginWidget;
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
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * The applications root panel. It manages the top widgets (LoginWidget, ProjectSelectorWidget and
 * WorkspaceWidget). It also provides the global UI locking.
 */
public class Ui extends AWidget {

	private static final Ui SINGLETON = new Ui();

	private LockWidget locker;
	private WaitWidget wait;
	private SimplePanel contentWrapper;
	private AWidget currentWidget;

	private HeaderWidget header;
	private LoginWidget login;
	private StartPageWidget startPage;
	private WorkspaceWidget workspace;
	private UserConfigWidget userconfig;

	@Override
	protected Widget onInitialization() {
		wait = new WaitWidget();
		header = new HeaderWidget();

		currentWidget = new AWidget() {

			@Override
			protected void onUpdate() {}

			@Override
			protected Widget onInitialization() {
				return new Label("Loading...");
			}
		}.update();

		contentWrapper = new SimplePanel();
		contentWrapper.setStyleName("content");
		contentWrapper.setWidget(currentWidget);

		SimplePanel headerWrapper = new SimplePanel();
		headerWrapper.setStyleName("header");
		headerWrapper.setWidget(header);

		FlowPanel pagePanel = new FlowPanel();
		pagePanel.setStyleName("page");
		pagePanel.add(headerWrapper);
		pagePanel.add(contentWrapper);

		locker = new LockWidget(pagePanel);
		return locker;
	}

	public void reset() {
		login = null;
		startPage = null;
		workspace = null;
		userconfig = null;
	}

	@Override
	protected void onUpdate() {
		header.update();
		contentWrapper.setWidget(currentWidget);
		if (currentWidget != null) currentWidget.update();
		locker.update();
	}

	public void setCurrentWidget(AWidget widget) {
		GwtLogger.DEBUG("Setting UI widget:", widget);
		this.currentWidget = widget;
		unlock();
		update();
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

	public StartPageWidget getStartPage() {
		if (startPage == null) startPage = new StartPageWidget();
		return startPage;
	}

	public void showLogin() {
		reset();
		setCurrentWidget(getLogin());
	}

	public void showConfiguration() {
		userconfig = getUserconfig();
		userconfig.setPrevWidget(currentWidget);
		setCurrentWidget(userconfig);
	}

	public void showStartPage() {
		setCurrentWidget(getStartPage());
	}

	public WorkspaceWidget getWorkspace() {
		if (workspace == null) workspace = new WorkspaceWidget();
		return workspace;
	}

	public UserConfigWidget getUserconfig() {
		if (userconfig == null) userconfig = new UserConfigWidget();
		return userconfig;
	}

	public LoginWidget getLogin() {
		if (login == null) {
			login = new LoginWidget();
		}
		return login;
	}

	public void showWorkspace() {
		setCurrentWidget(getWorkspace());
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
		if (getWorkspace().getWorkarea().isCurrentWidget(getWorkspace().getWorkarea().getSprintBacklog())) {
			showSprintBacklog(task);
		} else if (getWorkspace().getWorkarea().isCurrentWidget(getWorkspace().getWorkarea().getTaskOverview())) {
			showTaskOverview(task);
		} else {
			showWhiteboard(task);
		}
	}

	public void showRequirement(Requirement requirement) {
		ProductBacklogWidget productBacklog = getWorkspace().getWorkarea().getProductBacklog();
		boolean inCurrentSprint = ScrumGwtApplication.get().getProject().isCurrentSprint(requirement.getSprint());
		if (inCurrentSprint) {
			if (getWorkspace().getWorkarea().isCurrentWidget(productBacklog)) {
				showProductBacklog(requirement);
			} else {
				showSprintBacklog(requirement);
			}
		} else {
			showProductBacklog(requirement);
		}
	}

	public void showWhiteboard(Task task) {
		WhiteboardWidget whiteboard = getWorkspace().getWorkarea().getWhiteboard();
		select(whiteboard);
		whiteboard.selectTask(task);
	}

	public void showSprintBacklog(Task task) {
		SprintBacklogWidget sprintBacklog = getWorkspace().getWorkarea().getSprintBacklog();
		select(sprintBacklog);
		sprintBacklog.selectTask(task);
	}

	public void showTaskOverview(Task task) {
		TaskOverviewWidget taskOverview = getWorkspace().getWorkarea().getTaskOverview();
		select(taskOverview);
		taskOverview.selectTask(task);
	}

	public void showSprintBacklog(Requirement requirement) {
		SprintBacklogWidget sprintBacklog = getWorkspace().getWorkarea().getSprintBacklog();
		select(sprintBacklog);
		if (requirement != null) sprintBacklog.selectRequirement(requirement);
	}

	public void showProductBacklog(Requirement requirement) {
		ProductBacklogWidget productBacklog = getWorkspace().getWorkarea().getProductBacklog();
		select(productBacklog);
		productBacklog.selectRequirement(requirement);
	}

	public void showImpedimentList(Impediment impediment) {
		ImpedimentListWidget impedimentList = getWorkspace().getWorkarea().getImpedimentList();
		select(impedimentList);
		impedimentList.showImpediment(impediment);
	}

	public void showIssueList(Issue issue) {
		IssueListWidget issueList = getWorkspace().getWorkarea().getIssueList();
		select(issueList);
		issueList.showIssue(issue);
	}

	public void showQualityBacklog(Quality quality) {
		QualityBacklogWidget qualityBacklog = getWorkspace().getWorkarea().getQualityBacklog();
		select(qualityBacklog);
		qualityBacklog.showQuality(quality);
	}

	public void showRiskList(Risk risk) {
		RiskListWidget riskList = getWorkspace().getWorkarea().getRiskList();
		select(riskList);
		riskList.showRisk(risk);
	}

	private void select(AWidget widget) {
		getWorkspace().getWorkarea().getNavigator().select(widget);
	}
}
