package scrum.client.risks;

import scrum.client.collaboration.EmoticonsWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;
import scrum.client.journal.ActivateChangeHistoryAction;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RiskBlock extends ABlockWidget<Risk> implements TrashSupport {

	private Label priorityLabel;

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		Risk risk = getObject();
		priorityLabel = header.insertPrefixLabel("100px", true);
		header.appendCell(new EmoticonsWidget(risk), null, true, true, null);
		header.addMenuAction(new ActivateChangeHistoryAction(risk));
		header.addMenuAction(new DeleteRiskAction(risk));
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		Risk risk = getObject();
		priorityLabel.setText(risk.getPriorityLabel());
		header.setDragHandle(risk.getReference());
		header.setCenter(risk.getLabel());
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
