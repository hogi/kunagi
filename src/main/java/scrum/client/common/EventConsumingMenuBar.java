package scrum.client.common;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.MenuBar;

public class EventConsumingMenuBar extends MenuBar {

	public EventConsumingMenuBar() {
		super();
	}

	public EventConsumingMenuBar(boolean vertical) {
		super(vertical);
	}

	public EventConsumingMenuBar(MenuBarImages images) {
		super(images);
	}

	public EventConsumingMenuBar(boolean vertical, MenuBarImages images) {
		super(vertical, images);
	}

	@Override
	public void onBrowserEvent(Event event) {
		super.onBrowserEvent(event);
		eatEvent(event);
	}

	private void eatEvent(Event event) {
		DOM.eventCancelBubble(event, true);
		DOM.eventPreventDefault(event);
	}
}
