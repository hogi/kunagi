package scrum.client.sprint;

import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.UserGuideWidget;
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
		page.addSection(new UserGuideWidget(getLocalizer().views().sprintBacklog(), getCurrentProject()
				.getCurrentSprint().getRequirements().size() < 3, getCurrentUser().getHideUserGuideSprintBacklogModel()));
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
		requirementList.showObject(r);
	}

	public void selectTask(Task task) {
		update();
		RequirementInSprintBlock rBlock = (RequirementInSprintBlock) requirementList.getBlock(task.getRequirement());
		requirementList.extendBlock(rBlock, true);
		rBlock.selectTask(task);
	}

	private Sprint getSprint() {
		return getCurrentProject().getCurrentSprint();
	}

}
