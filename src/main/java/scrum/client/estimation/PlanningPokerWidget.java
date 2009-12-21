package scrum.client.estimation;

import scrum.client.common.AScrumWidget;
import scrum.client.project.Requirement;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PlanningPokerWidget extends AScrumWidget {

	private Requirement requirement;
	private FlowPanel pokerTable;
	private HorizontalPanel hand;
	private SimplePanel wrapper;

	public PlanningPokerWidget(Requirement requirement) {
		super();
		this.requirement = requirement;
	}

	@Override
	protected Widget onInitialization() {
		hand = new HorizontalPanel();
		hand.setStyleName("PokerHand");

		hand.add(new PokerHandcardWidget(requirement, 1));
		hand.add(new PokerHandcardWidget(requirement, 2));
		hand.add(new PokerHandcardWidget(requirement, 3));
		hand.add(new PokerHandcardWidget(requirement, 5));
		hand.add(new PokerHandcardWidget(requirement, 8));
		hand.add(new PokerHandcardWidget(requirement, 13));
		hand.add(new PokerHandcardWidget(requirement, 20));
		hand.add(new PokerHandcardWidget(requirement, 40));
		hand.add(new PokerHandcardWidget(requirement, 100));

		wrapper = new SimplePanel();
		return wrapper;
	}

	@Override
	protected void onUpdate() {
		if (requirement.isWorkEstimationVotingActive()) {
			pokerTable = new FlowPanel();
			pokerTable.setStyleName("PlanningPokerWidget");

			VerticalPanel col = new VerticalPanel();
			for (RequirementEstimationVote vote : requirement.getEstimationVotes()) {
				pokerTable.add(new PokerCardWidget(vote));
			}

			col.add(pokerTable);
			if (requirement.isWorkEstimationVotingShowoff() == false) {
				col.add(hand);
			}

			wrapper.setWidget(col);
		} else {
			wrapper.setWidget(null);
		}
		super.onUpdate();
	}
}
