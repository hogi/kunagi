package scrum.client.project;

import ilarkesto.gwt.client.AFieldValueWidget;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.IntegerEditorWidget;

import java.util.Collections;
import java.util.List;

import scrum.client.ScrumGwt;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.DocumentationWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class ProductBacklogWidget extends AScrumWidget {

	private BlockListWidget<Requirement> list;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<Requirement>(RequirementBlock.FACTORY);
		list.setAutoSorter(getCurrentProject().getRequirementsOrderComparator());
		if (getCurrentProject().isProductOwner(getCurrentUser())) {
			list.setDndSorting(true);
			list.setMoveObserver(new MoveObserver());
		}

		TableBuilder tb = ScrumGwt.createFieldTable();
		tb.addField("Assumed Velocity", new IntegerEditorWidget(getCurrentProject().getVelocityModel()));
		tb.addField("Velocity History", new AFieldValueWidget() {

			@Override
			protected void onUpdate() {
				setText(getCurrentProject().getVelocitiesFromLastSprints());
				super.onUpdate();
			}
		});

		PagePanel page = new PagePanel();
		page.addHeader("Product Backlog", new ButtonWidget(new CreateRequirementAction()));
		page.addSection(tb.createTable());
		DocumentationWidget documentation = new DocumentationWidget(getCurrentProject().getProductBacklogRequirements()
				.size() < 5, getLocalizer().views().productBacklog());
		page.addSection(Gwt.createFlowPanel(list, Gwt.createSpacer(1, 10),
			ScrumGwt.createPdfLink("Download as PDF", "productBacklog", getCurrentProject()), Gwt.createSpacer(1, 10),
			documentation));

		return page;
	}

	@Override
	protected void onUpdate() {
		List<Requirement> requirements = getCurrentProject().getProductBacklogRequirements();
		Collections.sort(requirements, getCurrentProject().getRequirementsOrderComparator());
		EstimationBarFactory.createEstimationBars(requirements, getCurrentProject().getVelocity());

		list.setObjects(requirements);

		super.onUpdate();
	}

	public boolean select(Requirement requirement) {
		if (!list.contains(requirement)) update();
		return list.showObject(requirement);
	}

	class MoveObserver implements Runnable {

		public void run() {
			List<Requirement> requirements = list.getObjects();
			getCurrentProject().updateRequirementsOrder(requirements);
			update();
		}

	}

}
