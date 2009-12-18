package scrum.client.estimation;

import scrum.client.common.AScrumWidget;
import scrum.client.project.Requirement;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class PlanningPokerWidget extends AScrumWidget {

	private Requirement requirement;
	private FlowPanel pokerTable;
	private SimplePanel wrapper;

	public PlanningPokerWidget(Requirement requirement) {
		super();
		this.requirement = requirement;
	}

	@Override
	protected Widget onInitialization() {
		pokerTable = new FlowPanel();
		pokerTable.setStyleName("PlanningPokerWidget");

		pokerTable.add(new Label("Planning Poker"));

		wrapper = new SimplePanel();
		return wrapper;
	}

	@Override
	protected void onUpdate() {
		if (requirement.isWorkEstimationVotingActive()) {
			wrapper.setWidget(pokerTable);
		} else {
			wrapper.setWidget(null);
		}
		super.onUpdate();
	}

}
