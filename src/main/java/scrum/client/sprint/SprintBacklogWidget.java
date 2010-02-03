package scrum.client.sprint;

import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.project.Requirement;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class SprintBacklogWidget extends AScrumWidget {

	private BlockListWidget<Requirement> requirementList;
	private Sprint sprint;

	@Override
	protected Widget onInitialization() {
		sprint = getSprint();

		requirementList = new BlockListWidget<Requirement>(RequirementInSprintBlock.FACTORY);
		requirementList.setAutoSorter(getCurrentProject().getRequirementsOrderComparator());

		PagePanel page = new PagePanel();
		page.addHeader("Stories in this Sprint");
		page.addSection(requirementList);
		page.addHeader("Sprint Properties");
		page.addSection(new SprintWidget(sprint));
		page.addHeader("Sprint Comments");
		page.addSection(new CommentsWidget(sprint));

		return page;
	}

	@Override
	protected void onUpdate() {
		if (sprint != getSprint()) reset();
		requirementList.setObjects(getSprint().getRequirements());
		super.onUpdate();
	}

	@Override
	protected boolean isResetRequired() {
		return sprint != getSprint();
	}

	public void selectRequirement(Requirement r) {
		requirementList.extendObject(r);
		requirementList.scrollToObject(r);
	}

	public void selectTask(Task task) {
		RequirementInSprintBlock rBlock = (RequirementInSprintBlock) requirementList.getBlock(task.getRequirement());
		requirementList.extendBlock(rBlock, true);
		rBlock.selectTask(task);
	}

	private Sprint getSprint() {
		return getCurrentProject().getCurrentSprint();
	}

}
