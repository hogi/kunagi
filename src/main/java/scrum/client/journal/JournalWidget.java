package scrum.client.journal;

import ilarkesto.gwt.client.Gwt;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.UserGuideWidget;
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
		page.addSection(Gwt.createServletDownloadLink("projectJournal.rss?projectId=" + getCurrentProject().getId(),
			"RSS Feed"));
		page.addSection(new UserGuideWidget(getLocalizer().views().journal(), getCurrentProject().getProjectEvents()
				.size() < 15, getCurrentUser().getHideUserGuideJournalModel()));
		return page;
	}

	@Override
	protected void onUpdate() {
		list.setObjects(getCurrentProject().getProjectEvents());
		super.onUpdate();
	}

	public boolean select(ProjectEvent event) {
		return list.showObject(event);
	}

}
