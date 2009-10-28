package scrum.client.sprint;

import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;

import com.google.gwt.user.client.ui.Widget;

public class SprintBlock extends ABlockWidget<Sprint> {

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		Sprint sprint = getObject();
		header.setCenter(sprint.getLabel());
		header.setDragHandle("spr");
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new SprintWidget(getObject());
	}

	public static final BlockWidgetFactory<Sprint> FACTORY = new BlockWidgetFactory<Sprint>() {

		public SprintBlock createBlock() {
			return new SprintBlock();
		}
	};

}
