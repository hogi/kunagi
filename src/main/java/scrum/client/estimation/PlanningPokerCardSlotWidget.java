package scrum.client.estimation;

import ilarkesto.gwt.client.Gwt;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class PlanningPokerCardSlotWidget extends AScrumWidget {

	private String text;
	private Widget card;

	public PlanningPokerCardSlotWidget(String owner, Widget card) {
		super();
		this.text = owner;
		this.card = card;
	}

	@Override
	protected Widget onInitialization() {
		FlowPanel panel = new FlowPanel();
		panel.setStyleName("PlanningPokerCardSlotWidget");
		panel.add(card == null ? createEmptySlot() : card);
		if (text != null) panel.add(Gwt.createDiv("PlanningPokerCardSlotWidget-text", text));

		return panel;
	}

	private Widget createEmptySlot() {
		return Gwt.createEmptyDiv("PlanningPokerCardSlotWidget-slot");
	}

}
