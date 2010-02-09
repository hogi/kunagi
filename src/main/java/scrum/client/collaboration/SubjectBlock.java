package scrum.client.collaboration;

import scrum.client.common.ABlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;

import com.google.gwt.user.client.ui.Widget;

public class SubjectBlock extends ABlockWidget<Subject> implements TrashSupport {

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		Subject subject = getObject();
		header.addMenuAction(new DeleteSubjectAction(subject));
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		Subject subject = getObject();
		header.setDragHandle(subject.getReference());
		header.setCenter(subject.getLabel());
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new SubjectWidget(getObject());
	}

	public AScrumAction getTrashAction() {
		return new DeleteSubjectAction(getObject());
	}

	public static final BlockWidgetFactory<Subject> FACTORY = new BlockWidgetFactory<Subject>() {

		public SubjectBlock createBlock() {
			return new SubjectBlock();
		}

	};

}
