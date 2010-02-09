package scrum.client.collaboration;

import scrum.client.common.ABlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;

import com.google.gwt.user.client.ui.Widget;

public class SubjectBlock extends ABlockWidget<ForumSupport> implements TrashSupport {

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		ForumSupport entity = getObject();
		if (entity instanceof Subject) {
			header.addMenuAction(new DeleteSubjectAction((Subject) entity));
		}
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		ForumSupport entity = getObject();
		header.setDragHandle(entity.getReference());
		header.setCenter(entity.getLabel());
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

		public SubjectBlock createBlock() {
			return new SubjectBlock();
		}

	};

}
