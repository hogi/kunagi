package scrum.client.release;

import ilarkesto.gwt.client.Date;
import scrum.client.collaboration.EmoticonsWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;
import scrum.client.journal.ActivateChangeHistoryAction;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ReleaseBlock extends ABlockWidget<Release> implements TrashSupport {

	private Label dateLabel;

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		Release release = getObject();
		dateLabel = header.appendCenterSuffix("");
		header.appendCell(new EmoticonsWidget(release), null, true, true, null);
		header.addMenuAction(new ActivateChangeHistoryAction(release));
		header.addMenuAction(new DeleteReleaseAction(release));
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		Release release = getObject();
		String dateSuffix = null;
		Date date = release.getReleaseDate();
		if (date != null) {
			dateSuffix = date.toString();
			Date today = Date.today();
			if (date.isAfter(today)) {
				dateSuffix += ", in " + date.getPeriodTo(today).toDays() + " days";
			} else if (date.isBefore(today)) {
				dateSuffix += ", " + date.getPeriodTo(today).toShortestString() + " ago";
			}
		}
		dateLabel.setText(dateSuffix);
		header.setDragHandle(release.getReference());
		header.setCenter(release.getLabel());
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
