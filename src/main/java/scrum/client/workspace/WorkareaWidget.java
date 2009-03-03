package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.impediments.ImpedimentListWidget;
import scrum.client.project.ProductBacklogWidget;
import scrum.client.project.ProjectOverviewWidget;
import scrum.client.risks.RiskListWidget;
import scrum.client.sprint.SprintBacklogWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class WorkareaWidget extends AWidget {

	private SimplePanel wrapper = new SimplePanel();
	private Widget currentWidget; // TODO AWidget

	private ProjectOverviewWidget projectOverview;
	private SprintBacklogWidget sprintBacklog;
	private ProductBacklogWidget productBacklog;
	private ImpedimentListWidget impedimentList;
	private RiskListWidget riskList;

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
		Ui.get().lock("Loading Sprint...");
		ScrumGwtApplication.get().callRequestCurrentSprint(new Runnable() {

			public void run() {
				show(getSprintBacklog());
			}
		});
	}

	public void showProductBacklog() {
		Ui.get().lock("Loading Product Backlog...");
		ScrumGwtApplication.get().callRequestRequirements(new Runnable() {

			public void run() {
				show(getProductBacklog());
			}
		});
	}

	public void showImpedimentList() {
		Ui.get().lock("Loading Impediments...");
		ScrumGwtApplication.get().callRequestImpediments(new Runnable() {

			public void run() {
				show(getImpedimentList());
			}
		});
	}

	public void showRiskList() {
		Ui.get().lock("Loading Risks...");
		ScrumGwtApplication.get().callRequestRisks(new Runnable() {

			public void run() {
				show(getRiskList());
			}
		});
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

	public ImpedimentListWidget getImpedimentList() {
		if (impedimentList == null) impedimentList = new ImpedimentListWidget();
		return impedimentList;
	}

	public RiskListWidget getRiskList() {
		if (riskList == null) riskList = new RiskListWidget();
		return riskList;
	}

	public static WorkareaWidget get() {
		return WorkspaceWidget.get().getWorkarea();
	}
}
