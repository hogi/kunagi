package scrum.client.estimation;

import ilarkesto.gwt.client.TableBuilder;
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

			TableBuilder tb = new TableBuilder();
			tb.setWidth("");
			int i = 0;
			for (RequirementEstimationVote vote : requirement.getEstimationVotes()) {
				tb.add(new PokerCardWidget(vote));
				if (++i % 5 == 0) tb.nextRow();
			}
			pokerTable.add(tb.createTable());

			if (requirement.isWorkEstimationVotingShowoff() == false) {
				VerticalPanel col = new VerticalPanel();
				col.add(pokerTable);
				col.add(hand);
				wrapper.setWidget(col);
			} else {
				wrapper.setWidget(pokerTable);
			}
		} else {
			wrapper.setWidget(null);
		}
		super.onUpdate();
	}
}
