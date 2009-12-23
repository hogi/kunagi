package scrum.client.estimation;

import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PokerCardWidget extends AScrumWidget {

	private RequirementEstimationVote vote;

	private SimplePanel wrapper;

	private SimplePanel card;

	public PokerCardWidget(RequirementEstimationVote vote) {
		super();
		this.vote = vote;
	}

	@Override
	protected Widget onInitialization() {
		card = new SimplePanel();

		wrapper = new SimplePanel();
		return wrapper;
	}

	@Override
	protected void onUpdate() {
		VerticalPanel cardtext = new VerticalPanel();
		cardtext.add(new Label(vote.getUser().getName()));
		if (vote.getRequirement().isWorkEstimationVotingShowoff()) {
			card.setStyleName("PokerCard-revealed");
			String labeltext = "N/A";
			if (vote.getEstimatedWork() != null) labeltext = String.valueOf(vote.getEstimatedWork().intValue());
			cardtext.add(new Label(labeltext));
		} else {
			card.setStyleName("PokerCard-unrevealed");
			if (vote.getUser().equals(getCurrentUser()) && vote.getEstimatedWork() != null) {
				cardtext.add(new Label("(" + vote.getEstimatedWork().intValue() + ")"));
			}
		}

		card.setWidget(cardtext);
		wrapper.setWidget(card);
		super.onUpdate();
	}

}
