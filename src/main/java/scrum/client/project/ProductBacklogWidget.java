package scrum.client.project;

import ilarkesto.gwt.client.AMultiSelectionViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;

import java.util.ArrayList;
import java.util.List;

import scrum.client.ListPredicate;
import scrum.client.ScrumGwtApplication;
import scrum.client.collaboration.Comment;
import scrum.client.common.BlockListWidget;
import scrum.client.common.BlockMoveObserver;
import scrum.client.common.GroupWidget;
import scrum.client.context.ProjectContext;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ProductBacklogWidget extends AWidget {

	private BlockListWidget<Requirement> list;
	private ToolbarWidget toolbar;
	private AMultiSelectionViewEditWidget<ListPredicate<Requirement>> predicateSelect;
	private List<ListPredicate<Requirement>> predicates;

	@Override
	protected Widget onInitialization() {
		predicates = new ArrayList<ListPredicate<Requirement>>();
		predicates.add(new ListPredicate<Requirement>("filter closed", true) {

			@Override
			protected boolean test(Requirement element) {
				return !element.isClosed();
			}
		});

		list = new BlockListWidget<Requirement>(RequirementBlock.FACTORY);
		list.setAutoSorter(ScrumGwtApplication.get().getProject().getRequirementsOrderComparator());
		list.setDndSorting(true);
		list.setMoveObserver(new MoveObserver());
		toolbar = new ToolbarWidget();
		toolbar.addButton(new CreateRequirementAction());

		VerticalPanel panel = new VerticalPanel();
		panel.setWidth("100%");
		panel.add(toolbar);
		panel.add(new HTML("<br />"));
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
			}
		};
		panel.add(predicateSelect);
		panel.add(list);

		return new GroupWidget("Product Backlog", panel);
	}

	@Override
	protected void onUpdate() {
		toolbar.update();
		predicateSelect.update();
		list.setObjects(ScrumGwtApplication.get().getProject().getRequirements());
	}

	public void selectRequirement(Requirement requirement) {
		list.extendObject(requirement);
	}

	public void activateCommentEditor(Comment comment) {
		RequirementBlock block = (RequirementBlock) list.getBlock(comment.getParent());
		block.activateCommentEditor(comment);
	}

	class MoveObserver implements BlockMoveObserver<Requirement> {

		public void onBlockMoved() {
			List<Requirement> requirements = list.getObjects();
			ScrumGwtApplication.get().getProject().updateRequirementsOrder(requirements);
		}

	}

	public static ProductBacklogWidget get() {
		return ProjectContext.get().getProductBacklog();
	}

}
