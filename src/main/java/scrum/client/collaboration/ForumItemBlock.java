package scrum.client.collaboration;

import scrum.client.common.ABlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.AScrumGwtEntity;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ForumItemBlock extends ABlockWidget<ForumSupport> implements TrashSupport {

	private Label dateLabel;

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		ForumSupport entity = getObject();
		dateLabel = header.appendCenterSuffix("");
		if (entity instanceof Subject) {
			header.addMenuAction(new DeleteSubjectAction((Subject) entity));
		}
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		ForumSupport entity = getObject();
		header.setDragHandle(entity.getReference());
		header.setCenter(entity.getLabel());
		Comment comment = ((AScrumGwtEntity) entity).getLatestComment();
		if (comment != null) {
			dateLabel.setText(comment.getDateAndTime().getPeriodToNow().toShortestString() + " ago by "
					+ comment.getAuthor().getName());
		} else {
			dateLabel.setText("");
		}
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new ForumItemWidget(getObject());
	}

	public AScrumAction getTrashAction() {
		ForumSupport entity = getObject();
		if (entity instanceof Subject) return new DeleteSubjectAction((Subject) entity);
		return null;
	}

	public static final BlockWidgetFactory<ForumSupport> FACTORY = new BlockWidgetFactory<ForumSupport>() {

		public ForumItemBlock createBlock() {
			return new ForumItemBlock();
		}

	};

}
