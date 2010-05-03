package scrum.client.release;

import ilarkesto.gwt.client.ButtonWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListSelectionManager;
import scrum.client.common.BlockListWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class ReleaseManagementWidget extends AScrumWidget {

	public BlockListWidget<Release> planned;
	public BlockListWidget<Release> published;
	private BlockListSelectionManager selectionManager;

	@Override
	protected Widget onInitialization() {
		selectionManager = new BlockListSelectionManager();

		planned = new BlockListWidget<Release>(ReleaseBlock.FACTORY);
		planned.setSelectionManager(selectionManager);
		planned.setAutoSorter(Release.DATE_COMPARATOR);

		published = new BlockListWidget<Release>(ReleaseBlock.FACTORY);
		published.setSelectionManager(selectionManager);
		published.setAutoSorter(Release.DATE_COMPARATOR);

		PagePanel page = new PagePanel();
		page.addHeader("Planned releases", new ButtonWidget(new CreateReleaseAction()));
		page.addSection(planned);
		page.addHeader("Published releases");
		page.addSection(published);
		return page;
	}

	@Override
	protected void onUpdate() {
		planned.setObjects(getCurrentProject().getPlannedReleases());
		published.setObjects(getCurrentProject().getReleasedReleases());
		super.onUpdate();
	}

	public boolean select(Release release) {
		update();
		return selectionManager.select(release);
	}

}
