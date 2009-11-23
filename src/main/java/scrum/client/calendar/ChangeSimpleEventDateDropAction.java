package scrum.client.calendar;

import ilarkesto.gwt.client.Date;
import scrum.client.ComponentManager;
import scrum.client.dnd.BlockListDropAction;

public class ChangeSimpleEventDateDropAction implements BlockListDropAction<SimpleEvent> {

	private Date date;

	public ChangeSimpleEventDateDropAction(Date date) {
		super();
		this.date = date;
	}

	public boolean onDrop(SimpleEvent event) {
		event.setDate(date);
		ComponentManager.get().getEventBus().fireVisibleDataChanged();
		return true;
	}

}
