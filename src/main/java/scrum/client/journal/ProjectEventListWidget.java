package scrum.client.journal;

import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;

import com.google.gwt.user.client.ui.Widget;

public class ProjectEventListWidget extends AScrumWidget {

	BlockListWidget<ProjectEvent> list;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<ProjectEvent>(ProjectEventBlock.FACTORY);
		return list;
	}

}
