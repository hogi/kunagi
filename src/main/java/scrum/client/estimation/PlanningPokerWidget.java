package scrum.client.estimation;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.GwtLogger;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.ClientConstants;
import scrum.client.admin.User;
import scrum.client.common.AScrumWidget;
import scrum.client.project.Requirement;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class PlanningPokerWidget extends AScrumWidget {

	private static final GwtLogger LOG = GwtLogger.createLogger(PlanningPokerWidget.class);

	private Requirement requirement;

	private SimplePanel cardSlotsWrapper;
	private SimplePanel handCardsWrapper;

	public PlanningPokerWidget(Requirement requirement) {
		super();
		this.requirement = requirement;
	}

	@Override
	protected Widget onInitialization() {
		cardSlotsWrapper = new SimplePanel();
		handCardsWrapper = new SimplePanel();

		FlowPanel pokerTable = new FlowPanel();
		pokerTable.setStyleName("PlanningPokerWidget-table");
		pokerTable.add(createTableBranding());
		pokerTable.add(cardSlotsWrapper);

		if (getCurrentProject().isTeamMember(getCurrentUser())) {
			pokerTable.add(Gwt.createSpacer(1, 20));
			pokerTable.add(handCardsWrapper);
		}

		SimplePanel pokerTableBorder = Gwt.createDiv("PlanningPokerWidget-table-border", pokerTable);

		return pokerTableBorder;
	}

	@Override
	protected void onUpdate() {
		cardSlotsWrapper.setWidget(createCardSlots());
		handCardsWrapper.setWidget(createHandCards());
		super.onUpdate();
	}

	private Widget createHandCards() {
		RequirementEstimationVote vote = requirement.getEstimationVote(getCurrentUser());
		Integer voteValue = vote == null ? null : vote.getEstimatedWork();

		TableBuilder tb = new TableBuilder();
		tb.setWidth(null);
		for (String value : ClientConstants.EFFORT_ROW) {
			if (value.length() == 0) continue;
			int estimation = Integer.parseInt(value);
			PlanningPokerCardWidget card = null;
			if (voteValue == null || estimation != voteValue) {
				card = new PlanningPokerCardWidget(estimation, true, new SetEstimationClickHandler(estimation));
			}
			tb.add(new PlanningPokerCardSlotWidget(null, card));
			tb.add(Gwt.createSpacer(5, 1));
		}
		return tb.createTable();
	}

	private Widget createCardSlots() {
		TableBuilder tb = new TableBuilder();
		tb.setWidth(null);
		for (User user : getCurrentProject().getTeamMembers()) {
			boolean currentUser = user == getCurrentUser();
			RequirementEstimationVote vote = requirement.getEstimationVote(user);
			Integer estimation = vote == null ? null : vote.getEstimatedWork();
			LOG.debug("Estimation:", user.getName(), "->", estimation);

			Widget card;
			if (estimation == null) {
				card = null;
			} else {
				ClickHandler clickHandler = currentUser ? new RemoveEstimationClickHandler() : null;
				boolean visible = currentUser || requirement.isWorkEstimationVotingShowoff();
				card = new PlanningPokerCardWidget(estimation, visible, clickHandler);
			}

			tb.add(new PlanningPokerCardSlotWidget(user, card));
			tb.add(Gwt.createSpacer(5, 1));
		}
		return tb.createTable();
	}

	private Widget createTableBranding() {
		return Gwt.createDiv("PlanningPokerWidget-table-branding", "Planning Poker");
	}

	private class SetEstimationClickHandler implements ClickHandler {

		private int estimation;

		public SetEstimationClickHandler(int estimation) {
			super();
			this.estimation = estimation;
		}

		public void onClick(ClickEvent event) {
			requirement.setVote(estimation);
			cm.getEventBus().fireVisibleDataChanged();
		}
	}

	private class RemoveEstimationClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			requirement.setVote(null);
			cm.getEventBus().fireVisibleDataChanged();
		}
	}
}
