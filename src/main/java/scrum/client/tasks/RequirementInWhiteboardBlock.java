package scrum.client.tasks;

import scrum.client.collaboration.EmoticonsWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.project.Requirement;
import scrum.client.project.RequirementWidget;
import scrum.client.sprint.CreateTaskAction;

import com.google.gwt.user.client.ui.Widget;

public class RequirementInWhiteboardBlock extends ABlockWidget<Requirement> {

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		Requirement requirement = getObject();
		header.appendCell(new EmoticonsWidget(requirement), null, true, true, null);
		header.addMenuAction(new CreateTaskAction(requirement));
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		Requirement requirement = getObject();
		header.setDragHandle(requirement.getReference());
		header.setCenter(requirement.getLabel());
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new RequirementWidget(getObject(), true, false, true, true, false);
	}

	public static final BlockWidgetFactory<Requirement> FACTORY = new BlockWidgetFactory<Requirement>() {

		public RequirementInWhiteboardBlock createBlock() {
			return new RequirementInWhiteboardBlock();
		}
	};
}
