package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.impediments.ImpedimentListWidget;
import scrum.client.project.AttributeBacklogWidget;
import scrum.client.project.ProductBacklogWidget;
import scrum.client.project.ProjectOverviewWidget;
import scrum.client.project.Requirement;
import scrum.client.risks.RiskListWidget;
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
	private AttributeBacklogWidget attributeBacklog;
	private ImpedimentListWidget impedimentList;
	private RiskListWidget riskList;
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

	public void showAttributeBacklog() {
		ScrumGwtApplication.get().callRequestAttributes();
		show(getAttributeBacklog());
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

	public ProductBacklogWidget getProductBacklog() {
		if (productBacklog == null) productBacklog = new ProductBacklogWidget();
		return productBacklog;
	}

	public AttributeBacklogWidget getAttributeBacklog() {
		if (attributeBacklog == null) attributeBacklog = new AttributeBacklogWidget();
		return attributeBacklog;
	}

	public ImpedimentListWidget getImpedimentList() {
		if (impedimentList == null) impedimentList = new ImpedimentListWidget();
		return impedimentList;
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
		return WorkspaceWidget.get().getWorkarea();
	}
}
