package scrum.client.estimation;

import ilarkesto.gwt.client.HyperlinkWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.project.Requirement;
import scrum.client.project.RequirementEstimationVoteAction;

import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PokerHandcardWidget extends AScrumWidget {

	private Requirement requirement;

	private SimplePanel wrapper;

	private SimplePanel card;

	private RequirementEstimationVoteAction action;

	private int estimatedWork;

	public PokerHandcardWidget(Requirement requirement, int estimatedWork) {
		super();
		this.requirement = requirement;
		this.estimatedWork = estimatedWork;
	}

	@Override
	protected Widget onInitialization() {
		card = new SimplePanel();
		card.setStyleName("PokerCard-hand");

		VerticalPanel cardtext = new VerticalPanel();
		action = new RequirementEstimationVoteAction(requirement, estimatedWork);

		cardtext.add(new HyperlinkWidget(action));
		card.setWidget(cardtext);

		wrapper = new SimplePanel();
		wrapper.setWidget(card);
		return wrapper;
	}

}
