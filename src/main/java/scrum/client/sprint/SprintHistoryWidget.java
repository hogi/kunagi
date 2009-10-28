package scrum.client.sprint;

import ilarkesto.gwt.client.GwtLogger;

import java.util.ArrayList;
import java.util.List;

import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class SprintHistoryWidget extends AScrumWidget {

	private BlockListWidget<Sprint> sprintList;

	@Override
	protected Widget onInitialization() {
		sprintList = new BlockListWidget<Sprint>(SprintBlock.FACTORY);

		PagePanel page = new PagePanel();
		page.addHeader("Sprint history");
		page.addSection(sprintList);

		return page;
	}

	@Override
	protected void onUpdate() {
		sprintList.setObjects(getSprintHistory());
		super.onUpdate();
	}

	private List<Sprint> getSprintHistory() {
		List<Sprint> sprintHistory = new ArrayList<Sprint>();
		for (Sprint sprint : getCurrentProject().getSprints()) {
			GwtLogger.DEBUG("Sprint:", sprint);
			if (getCurrentProject().isCurrentSprint(sprint)) continue;
			if (sprint.getVelocity() == null) continue;

			sprintHistory.add(sprint);
		}
		return sprintHistory;
	}

}
