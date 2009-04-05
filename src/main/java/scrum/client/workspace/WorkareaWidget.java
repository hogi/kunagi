package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.admin.UserListWidget;
import scrum.client.impediments.ImpedimentListWidget;
import scrum.client.project.ProductBacklogWidget;
import scrum.client.project.ProjectOverviewWidget;
import scrum.client.project.QualityBacklogWidget;
import scrum.client.project.Requirement;
import scrum.client.risks.RiskListWidget;
import scrum.client.sprint.NextSprintWidget;
import scrum.client.sprint.SprintBacklogWidget;
import scrum.client.test.WidgetsTesterWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class WorkareaWidget extends AWidget {

	private SimplePanel wrapper = new SimplePanel();
	private Widget currentWidget; // TODO AWidget

	private ProjectOverviewWidget projectOverview;
	private SprintBacklogWidget sprintBacklog;
	private ProductBacklogWidget productBacklog;
	private QualityBacklogWidget qualityBacklog;
	private NextSprintWidget nextSprint;
	private ImpedimentListWidget impedimentList;
	private RiskListWidget riskList;
	private UserListWidget userList;
	private WidgetsTesterWidget widgetsTester;

	@Override
	protected Widget onInitialization() {
		currentWidget = new Label("workarea");
		wrapper = new SimplePanel();
		wrapper.setStyleName("WorkareaWidget");
		wrapper.add(currentWidget);
		return wrapper;
	}

	@Override
	protected void onUpdate() {
		wrapper.setWidget(currentWidget);
		if (currentWidget != null && currentWidget instanceof AWidget) {
			((AWidget) currentWidget).update();
		}
	}

	public void showProjectOverview() {
		show(getProjectOverview());
	}

	public boolean isProjectOverview() {
		return currentWidget == projectOverview;
	}

	public void showSprintBacklog() {
		ScrumGwtApplication.get().callRequestCurrentSprint();
		show(getSprintBacklog());
	}

	public void showNextSprint() {
		show(getNextSprint());
	}

	public void showSprintBacklog(final Requirement r) {
		ScrumGwtApplication.get().callRequestCurrentSprint();
		SprintBacklogWidget sprintWidget = getSprintBacklog();
		show(getSprintBacklog());
		sprintWidget.selectRequirement(r);
	}

	public void showProductBacklog() {
		ScrumGwtApplication.get().callRequestRequirements();
		show(getProductBacklog());
	}

	public void showQualityBacklog() {
		ScrumGwtApplication.get().callRequestQualitys();
		show(getQualityBacklog());
	}

	public void showWidgetsTester() {
		show(getWidgetsTester());
	}

	public void showImpedimentList() {
		ScrumGwtApplication.get().callRequestImpediments();
		show(getImpedimentList());
	}

	public void showRiskList() {
		ScrumGwtApplication.get().callRequestRisks();
		show(getRiskList());
	}

	public void showUserList() {
		show(getUserList());
	}

	private void show(AWidget widget) {
		currentWidget = widget;
		Ui.get().unlock();
		update();
	}

	public ProjectOverviewWidget getProjectOverview() {
		if (projectOverview == null) projectOverview = new ProjectOverviewWidget();
		return projectOverview;
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

	public RiskListWidget getRiskList() {
		if (riskList == null) riskList = new RiskListWidget();
		return riskList;
	}

	public UserListWidget getUserList() {
		if (userList == null) userList = new UserListWidget();
		return userList;
	}

	public WidgetsTesterWidget getWidgetsTester() {
		if (widgetsTester == null) widgetsTester = new WidgetsTesterWidget();
		return widgetsTester;
	}

	public static WorkareaWidget get() {
		return WorkspaceWidget.get().getWorkarea();
	}
}
