package scrum.client.estimation;

import ilarkesto.gwt.client.Gwt;
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

			TableBuilder cardTable = new TableBuilder();
			cardTable.setWidth("");
			int i = 0;
			for (RequirementEstimationVote vote : requirement.getEstimationVotes()) {
				cardTable.add(new PokerCardWidget(vote));
				if (++i % 5 == 0) cardTable.nextRow();
			}

			pokerTable = new FlowPanel();
			pokerTable.setStyleName("PlanningPokerWidget-table");
			pokerTable.add(createTableBranding());
			pokerTable.add(cardTable.createTable());

			SimplePanel pokerTableBorder = Gwt.createDiv("PlanningPokerWidget-table-border", pokerTable);

			if (requirement.isWorkEstimationVotingShowoff() == false) {
				VerticalPanel col = new VerticalPanel();
				col.add(pokerTableBorder);
				col.add(hand);
				wrapper.setWidget(col);
			} else {
				wrapper.setWidget(pokerTableBorder);
			}
		} else {
			wrapper.setWidget(null);
		}
		super.onUpdate();
	}

	private Widget createTableBranding() {
		return Gwt.createDiv("PlanningPokerWidget-table-branding", "Planning Poker");
	}
}
