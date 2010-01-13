package scrum.client.estimation;

import ilarkesto.gwt.client.Gwt;
import scrum.client.admin.User;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class PlanningPokerCardSlotWidget extends AScrumWidget {

	private User owner;
	private Widget card;

	public PlanningPokerCardSlotWidget(User owner, Widget card) {
		super();
		this.owner = owner;
		this.card = card;
	}

	@Override
	protected Widget onInitialization() {
		FlowPanel panel = new FlowPanel();
		panel.setStyleName("PlanningPokerCardSlotWidget");
		panel.add(card == null ? createEmptySlot() : card);
		if (owner != null) panel.add(Gwt.createDiv("PlanningPokerCardSlotWidget-name", owner.getName()));

		return panel;
	}

	private Widget createEmptySlot() {
		return Gwt.createEmptyDiv("PlanningPokerCardSlotWidget-slot");
	}

}
