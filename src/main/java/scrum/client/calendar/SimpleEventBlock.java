package scrum.client.calendar;

import scrum.client.common.ABlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SimpleEventBlock extends ABlockWidget<SimpleEvent> implements TrashSupport {

	private Label timeLabel;
	private Label durationLabel;

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		SimpleEvent event = getObject();
		timeLabel = header.insertPrefixLabel("35px", true);
		durationLabel = header.appendCenterSuffix("");
		header.addMenuAction(new DeleteSimpleEventAction(event));
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		SimpleEvent event = getObject();
		header.setDragHandle("evt");
		timeLabel.setText(event.getTimeAsString());
		durationLabel.setText(event.getDurationAsString());
		header.setCenter(event.getLabel());
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new SimpleEventWidget(getObject());
	}

	public AScrumAction getTrashAction() {
		return new DeleteSimpleEventAction(getObject());
	}

	public static final BlockWidgetFactory<SimpleEvent> FACTORY = new BlockWidgetFactory<SimpleEvent>() {

		public SimpleEventBlock createBlock() {
			return new SimpleEventBlock();
		}

	};

}
