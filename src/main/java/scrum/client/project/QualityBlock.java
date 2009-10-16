package scrum.client.project;

import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.Widget;

public class QualityBlock extends AExtensibleBlockWidget<Quality> implements TrashSupport {

	@Override
	protected void onCollapsedInitialization() {
		setIcon(Img.bundle.requirement16());
	}

	@Override
	protected void onUpdateHead() {
		Quality quality = getObject();
		setBlockTitle(quality.getReference() + " " + quality.getLabel());
		addMenuAction(new DeleteQualityAction(quality));
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new QualityWidget(getObject());
	}

	public AScrumAction getTrashAction() {
		return new DeleteQualityAction(getObject());
	}

	public static final BlockWidgetFactory<Quality> FACTORY = new BlockWidgetFactory<Quality>() {

		public QualityBlock createBlock() {
			return new QualityBlock();
		}
	};
}
