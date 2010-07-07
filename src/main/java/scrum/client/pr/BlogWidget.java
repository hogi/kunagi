package scrum.client.pr;

import ilarkesto.gwt.client.ButtonWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class BlogWidget extends AScrumWidget {

	public BlockListWidget<BlogEntry> list;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<BlogEntry>(BlogEntryBlock.FACTORY);
		list.setAutoSorter(BlogEntry.DATE_COMPARATOR);

		PagePanel page = new PagePanel();
		page.addHeader("Blog", new ButtonWidget(new CreateBlogEntryAction()));
		page.addSection(list);
		return page;
	}

	@Override
	protected void onUpdate() {
		list.setObjects(getCurrentProject().getBlogEntrys());
		super.onUpdate();
	}

	public boolean select(BlogEntry blockEntry) {
		if (!list.contains(blockEntry)) update();
		return list.showObject(blockEntry);
	}

}
