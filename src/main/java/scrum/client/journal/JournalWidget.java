package scrum.client.journal;

import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class JournalWidget extends AScrumWidget {

	BlockListWidget<ProjectEvent> list;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<ProjectEvent>(ProjectEventBlock.FACTORY);
		list.setAutoSorter(ProjectEvent.DATE_AND_TIME_COMPARATOR);

		PagePanel page = new PagePanel();
		page.addHeader("Project Journal");
		page.addSection(list);

		return page;
	}

	@Override
	protected void onUpdate() {
		list.setObjects(getCurrentProject().getEvents());
		super.onUpdate();
	}

}
