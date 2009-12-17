package scrum.client.estimation;

import scrum.client.common.AScrumWidget;
import scrum.client.project.Requirement;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PlanningPokerWidget extends AScrumWidget {

	private Requirement requirement;
	private FlowPanel wrapper;

	public PlanningPokerWidget(Requirement requirement) {
		super();
		this.requirement = requirement;
	}

	@Override
	protected Widget onInitialization() {
		wrapper = new FlowPanel();
		wrapper.setStyleName("PlanningPokerWidget");

		wrapper.add(new Label("Planning Poker"));

		return wrapper;
	}

}
