package scrum.client.calendar;

import ilarkesto.gwt.client.Date;
import scrum.client.dnd.BlockListDropAction;
import scrum.client.workspace.VisibleDataChangedEvent;

public class ChangeSimpleEventDateDropAction implements BlockListDropAction<SimpleEvent> {

	private Date date;

	public ChangeSimpleEventDateDropAction(Date date) {
		super();
		this.date = date;
	}

	public boolean onDrop(SimpleEvent event) {
		event.setDate(date);
		new VisibleDataChangedEvent().fireInCurrentScope();
		return true;
	}

}
