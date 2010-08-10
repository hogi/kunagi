package scrum.client.sprint;

import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class SprintHistoryWidget extends AScrumWidget {

	private BlockListWidget<Sprint> sprintList;

	@Override
	protected Widget onInitialization() {
		sprintList = new BlockListWidget<Sprint>(SprintBlock.FACTORY);
		sprintList.setAutoSorter(Sprint.END_DATE_COMPARATOR);

		PagePanel page = new PagePanel();
		page.addHeader("Sprint history");
		page.addSection(sprintList);
		return page;
	}

	@Override
	protected void onUpdate() {
		sprintList.setObjects(getCurrentProject().getCompletedSprints());
		super.onUpdate();
	}

	public boolean select(Sprint sprint) {
		return sprintList.showObject(sprint);
	}
}
