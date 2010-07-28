package scrum.client.pr;

import ilarkesto.gwt.client.DateAndTime;
import scrum.client.collaboration.EmoticonsWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.img.Img;
import scrum.client.journal.ActivateChangeHistoryAction;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class BlogEntryBlock extends ABlockWidget<BlogEntry> {

	private SimplePanel statusIcon;
	private Label dateLabel;

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		BlogEntry blogEntry = getObject();
		statusIcon = header.insertPrefixIcon();
		dateLabel = header.appendCenterSuffix("");
		header.appendCell(new EmoticonsWidget(blogEntry), null, true, true, null);
		header.addMenuAction(new PublishBlogEntryAction(blogEntry));
		header.addMenuAction(new UnpublishBlogEntryAction(blogEntry));
		header.addMenuAction(new ActivateChangeHistoryAction(blogEntry));
		header.addMenuAction(new DeleteBlogEntryAction(blogEntry));
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		BlogEntry blogEntry = getObject();
		header.setDragHandle(blogEntry.getReference());
		header.setCenter(blogEntry.getTitle());
		Image statusImage = null;
		if (blogEntry.isPublished()) {
			statusImage = Img.bundle.blgPublished().createImage();
			statusImage.setTitle("Published.");
		}
		statusIcon.setWidget(statusImage);
		String dateString = null;
		DateAndTime dateAndTime = blogEntry.getDateAndTime();
		if (dateAndTime != null) {
			dateString = dateAndTime.toString();
		}
		dateLabel.setText(dateString);
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new BlogEntryWidget(getObject());
	}

	public static final BlockWidgetFactory<BlogEntry> FACTORY = new BlockWidgetFactory<BlogEntry>() {

		public BlogEntryBlock createBlock() {
			return new BlogEntryBlock();
		}
	};

}
