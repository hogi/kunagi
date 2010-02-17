package scrum.client.project;

import ilarkesto.gwt.client.AFieldValueWidget;
import ilarkesto.gwt.client.AMultiSelectionViewEditWidget;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.IntegerEditorWidget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import scrum.client.ListPredicate;
import scrum.client.ScrumGwt;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class ProductBacklogWidget extends AScrumWidget {

	private BlockListWidget<Requirement> list;
	private AMultiSelectionViewEditWidget<ListPredicate<Requirement>> predicateSelect;
	private List<ListPredicate<Requirement>> predicates;

	@Override
	protected Widget onInitialization() {
		predicates = new ArrayList<ListPredicate<Requirement>>();
		predicates.add(new ListPredicate<Requirement>("closed", true) {

			@Override
			protected boolean test(Requirement element) {
				return element.isClosed();
			}
		});

		list = new BlockListWidget<Requirement>(RequirementBlock.FACTORY);
		list.setAutoSorter(getCurrentProject().getRequirementsOrderComparator());
		if (getCurrentProject().isProductOwner(getCurrentUser())) {
			list.setDndSorting(true);
			list.setMoveObserver(new MoveObserver());
		}

		// Filters
		Panel filterPanel = new FlowPanel();
		filterPanel.add(new Label("Filter: "));
		predicateSelect = new AMultiSelectionViewEditWidget<ListPredicate<Requirement>>() {

			@Override
			protected void onViewerUpdate() {
				setViewerItems(filter(predicates));
			}

			@Override
			protected void onEditorUpdate() {
				setEditorItems(predicates);
				setEditorSelectedItems(filter(predicates));
			}

			private List<ListPredicate<Requirement>> filter(List<ListPredicate<Requirement>> predicates) {
				List<ListPredicate<Requirement>> filteredList = new ArrayList<ListPredicate<Requirement>>(predicates
						.size());
				for (ListPredicate<Requirement> p : predicates) {
					if (p.isEnabled()) filteredList.add(p);
				}
				return filteredList;
			}

			@Override
			protected void onEditorSubmit() {
				List<ListPredicate<Requirement>> selected = getEditorSelectedItems();
				for (ListPredicate<Requirement> p : predicates) {
					p.setEnabled(selected.contains(p));
				}
				ProductBacklogWidget.this.update();
			}

			@Override
			public boolean isEditable() {
				return true;
			}
		};
		filterPanel.add(predicateSelect);

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
		page.addSection(filterPanel);
		page.addSection(Gwt.createFlowPanel(list, Gwt.createSpacer(1, 10), ScrumGwt.createPdfLink("Download as PDF",
			"productBacklog", getCurrentProject())));

		return page;
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		List<Requirement> requirements = filter(getCurrentProject().getRequirements());
		Collections.sort(requirements, getCurrentProject().getRequirementsOrderComparator());

		EstimationBarFactory.createEstimationBars(requirements, getCurrentProject().getVelocity());

		list.setObjects(requirements);
	}

	private List<Requirement> filter(List<Requirement> requirements) {
		ArrayList<Requirement> result = new ArrayList<Requirement>(requirements.size());
		for (Requirement r : requirements) {
			if (noPredicateContains(r)) result.add(r);
		}
		return result;
	}

	private boolean noPredicateContains(Requirement r) {
		for (ListPredicate<Requirement> p : predicates) {
			if (p.contains(r)) return false;
		}
		return true;
	}

	public void selectRequirement(Requirement requirement) {
		list.showObject(requirement);
	}

	class MoveObserver implements Runnable {

		public void run() {
			List<Requirement> requirements = list.getObjects();
			getCurrentProject().updateRequirementsOrder(requirements);
			update();
		}

	}

}
