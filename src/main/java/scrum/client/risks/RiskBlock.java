package scrum.client.risks;

import scrum.client.common.ABlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;

import com.google.gwt.user.client.ui.Widget;

public class RiskBlock extends ABlockWidget<Risk> implements TrashSupport {

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		Risk risk = getObject();
		header.addMenuAction(new DeleteRiskAction(risk));
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		Risk risk = getObject();
		header.setDragHandle(risk.getReference());
		header.setCenter(risk.getLabel() + " (" + risk.getPriorityLabel() + ")");
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
