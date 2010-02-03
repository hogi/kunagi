package scrum.client.estimation;

import ilarkesto.gwt.client.Gwt;
import scrum.client.common.AScrumWidget;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;

public class PlanningPokerCardWidget extends AScrumWidget {

	private float value;
	private ClickHandler clickHandler;
	private String clickTooltip;
	private boolean visible;

	public PlanningPokerCardWidget(float value, boolean visible, ClickHandler clickHandler, String clickTooltip) {
		super();
		this.value = value;
		this.visible = visible;
		this.clickHandler = clickHandler;
		this.clickTooltip = clickTooltip;
	}

	@Override
	protected Widget onInitialization() {
		FocusPanel card = new FocusPanel();
		card.setStyleName("PlanningPokerCardWidget");
		if (visible) {
			String label = value < 1 ? String.valueOf(value) : String.valueOf((int) value);
			card.setWidget(Gwt.createDiv("PlanningPokerCardWidget-text", label));
		} else {
			card.setWidget(Gwt.createEmptyDiv("PlanningPokerCardWidget-back"));
		}
		if (clickHandler != null) {
			card.addClickHandler(clickHandler);
			card.addStyleName("PlanningPokerCardWidget-clickable");
			card.setTitle(clickTooltip);
		}

		return card;
	}

}
