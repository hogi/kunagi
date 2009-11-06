package scrum.client.journal;

import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;

import com.google.gwt.user.client.ui.Widget;

public class ProjectEventBlock extends ABlockWidget<ProjectEvent> {

	@Override
	protected Widget onExtendedInitialization() {
		return new ProjectEventWidget(getObject());
	}

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {

	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {

	}

	public static final BlockWidgetFactory<ProjectEvent> FACTORY = new BlockWidgetFactory<ProjectEvent>() {

		public ProjectEventBlock createBlock() {
			return new ProjectEventBlock();
		}
	};

}
