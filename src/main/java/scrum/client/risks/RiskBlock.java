package scrum.client.risks;

import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.Widget;

public class RiskBlock extends AExtensibleBlockWidget<Risk> implements TrashSupport {

	@Override
	protected void onCollapsedInitialization() {
		setIcon(Img.bundle.risk16());
	}

	@Override
	protected void onUpdateHead() {
		Risk risk = getObject();
		setBlockTitle(risk.getReference() + " " + risk.getLabel() + " (" + risk.getPriorityLabel() + ")");
		addMenuAction(new DeleteRiskAction(risk));
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new RiskWidget(getObject());
	}

	public AScrumAction getTrashAction() {
		return new DeleteRiskAction(getObject());
	}

	public static final BlockWidgetFactory<Risk> FACTORY = new BlockWidgetFactory<Risk>() {

		public RiskBlock createBlock() {
			return new RiskBlock();
		}
	};
}
