package scrum.client.release;

import ilarkesto.gwt.client.Date;
import scrum.client.collaboration.EmoticonsWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;
import scrum.client.journal.ActivateChangeHistoryAction;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class ReleaseBlock extends ABlockWidget<Release> implements TrashSupport {

	private SimplePanel typeIcon;
	private Label dateLabel;
	private Label parentLabel;

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		Release release = getObject();
		typeIcon = header.insertPrefixIcon();
		dateLabel = header.insertPrefixLabel("150px", true);
		parentLabel = header.appendCenterSuffix("");
		header.appendCell(new EmoticonsWidget(release), null, true, true, null);
		header.addMenuAction(new CreateBugfixReleaseAction(release));
		header.addMenuAction(new ReleaseReleaseAction(release));
		header.addMenuAction(new UnreleaseReleaseAction(release));
		header.addMenuAction(new ActivateChangeHistoryAction(release));
		header.addMenuAction(new DeleteReleaseAction(release));
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		Release release = getObject();
		typeIcon.setWidget(createTypeIcon());
		dateLabel.setText(getDateSuffix());
		parentLabel.setText(release.isBugfix() ? "Bugfix for " + release.getParentRelease().getLabel() : "");
		header.setDragHandle(release.getReference());
		header.setCenter(release.getLabel());
	}

	private Image createTypeIcon() {
		Release release = getObject();
		Image image;
		if (release.isBugfix()) {
			image = Img.bundle.bugfixRelease().createImage();
			image.setTitle("Bugfix Release");
		} else {
			image = Img.bundle.majorRelease().createImage();
			image.setTitle("Major Release");
		}
		return image;
	}

	private String getDateSuffix() {
		Release release = getObject();
		String dateSuffix = null;
		Date date = release.getReleaseDate();
		if (date != null) {
			dateSuffix = date.toString();
			Date today = Date.today();
			if (date.isAfter(today)) {
				dateSuffix += " (in " + today.getPeriodTo(date).toDays() + " days)";
			} else if (date.isBefore(today)) {
				dateSuffix += " (" + date.getPeriodTo(today).toShortestString() + " ago)";
			} else {
				dateSuffix += " (today)";
			}
		} else {
			dateSuffix = "unscheduled";
		}
		return dateSuffix;
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new ReleaseWidget(getObject());
	}

	public AScrumAction getTrashAction() {
		return new DeleteReleaseAction(getObject());
	}

	public static final BlockWidgetFactory<Release> FACTORY = new BlockWidgetFactory<Release>() {

		public ReleaseBlock createBlock() {
			return new ReleaseBlock();
		}
	};
}
