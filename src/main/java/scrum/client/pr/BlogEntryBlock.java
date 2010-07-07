package scrum.client.pr;

import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockHeaderWidget;

import com.google.gwt.user.client.ui.Widget;

public class BlogEntryBlock extends ABlockWidget<BlogEntry> {

	@Override
	protected Widget onExtendedInitialization() {
		return new BlogEntryWidget(getObject());
	}

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		BlogEntry blogEntry = getObject();
		header.setDragHandle(blogEntry.getReference());
		header.setCenter(blogEntry.getTitle());
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
	// TODO Auto-generated method stub

	}
}
