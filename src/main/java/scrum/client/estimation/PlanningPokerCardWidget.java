package scrum.client.estimation;

import ilarkesto.gwt.client.Gwt;
import scrum.client.common.AScrumWidget;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;

public class PlanningPokerCardWidget extends AScrumWidget {

	private int value;
	private ClickHandler clickHandler;

	public PlanningPokerCardWidget(int value, ClickHandler clickHandler) {
		super();
		this.value = value;
		this.clickHandler = clickHandler;
	}

	@Override
	protected Widget onInitialization() {
		FocusPanel card = new FocusPanel();
		card.setStyleName("PlanningPokerCardWidget");
		card.setWidget(Gwt.createDiv("PlanningPokerCardWidget-text", String.valueOf(value)));
		if (clickHandler != null) {
			card.addClickHandler(clickHandler);
			card.addStyleName("PlanningPokerCardWidget-clickable");
		}

		return card;
	}

}
