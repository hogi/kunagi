package scrum.client.estimation;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.GwtLogger;
import ilarkesto.gwt.client.HyperlinkWidget;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.admin.User;
import scrum.client.common.AScrumWidget;
import scrum.client.project.CloseRequirementEstimationVotingAction;
import scrum.client.project.Project;
import scrum.client.project.Requirement;
import scrum.client.project.RequirementEstimationVotingShowoffAction;
import scrum.client.project.ResetRequirementEstimationVotingAction;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class PlanningPokerTableWidget extends AScrumWidget {

	private static final GwtLogger LOG = GwtLogger.createLogger(PlanningPokerTableWidget.class);

	private Requirement requirement;

	private SimplePanel cardSlotsWrapper;
	private SimplePanel handCardsWrapper;
	private SimplePanel actionsWrapper;

	public PlanningPokerTableWidget(Requirement requirement) {
		super();
		this.requirement = requirement;
	}

	@Override
	protected Widget onInitialization() {
		cardSlotsWrapper = new SimplePanel();
		handCardsWrapper = new SimplePanel();
		actionsWrapper = new SimplePanel();

		FlowPanel pokerTable = new FlowPanel();
		pokerTable.setStyleName("PlanningPokerWidget-table");
		pokerTable.add(createTableBranding());
		pokerTable.add(cardSlotsWrapper);
		if (getCurrentProject().isTeamMember(getCurrentUser())) {
			pokerTable.add(Gwt.createSpacer(1, 20));
			pokerTable.add(handCardsWrapper);
		}
		pokerTable.add(Gwt.createSpacer(1, 20));
		pokerTable.add(actionsWrapper);

		SimplePanel pokerTableBorder = Gwt.createDiv("PlanningPokerWidget-table-border", pokerTable);

		return pokerTableBorder;
	}

	@Override
	protected void onUpdate() {
		cardSlotsWrapper.setWidget(createCardSlots());
		handCardsWrapper.setWidget(createHandCards());
		actionsWrapper.setWidget(createActions());
		super.onUpdate();
	}

	private Widget createActions() {
		ResetRequirementEstimationVotingAction reset = new ResetRequirementEstimationVotingAction(requirement);
		RequirementEstimationVotingShowoffAction showoff = new RequirementEstimationVotingShowoffAction(requirement);
		CloseRequirementEstimationVotingAction close = new CloseRequirementEstimationVotingAction(requirement);

		TableBuilder tb = new TableBuilder();
		tb.setWidth(null);
		if (showoff.isExecutable() && showoff.isPermitted()) {
			tb.add(new HyperlinkWidget(showoff));
			tb.add(Gwt.createSpacer(10, 1));
		}
		if (reset.isExecutable() && reset.isPermitted()) {
			tb.add(new HyperlinkWidget(reset));
			tb.add(Gwt.createSpacer(10, 1));
		}
		if (close.isExecutable() && close.isPermitted()) {
			tb.add(new HyperlinkWidget(close));
			tb.add(Gwt.createSpacer(10, 1));
		}

		return tb.createTable();
	}

	private Widget createHandCards() {
		Project project = getCurrentProject();
		RequirementEstimationVote vote = requirement.getEstimationVote(getCurrentUser());
		Float voteValue = vote == null ? null : vote.getEstimatedWork();
		boolean showoff = requirement.isWorkEstimationVotingShowoff();

		TableBuilder tb = new TableBuilder();
		tb.setWidth(null);
		for (String value : Requirement.WORK_ESTIMATION_VALUES) {
			if (value.length() == 0) continue;
			float estimation = Float.parseFloat(value);
			PlanningPokerCardWidget card = null;
			if (!showoff && (voteValue == null || estimation != voteValue)) {
				card = new PlanningPokerCardWidget(estimation, true, new SetEstimationClickHandler(estimation),
						"Put this card on the table.");
			}
			tb.add(new PlanningPokerCardSlotWidget(value + " " + project.getEffortUnit(), card));
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
			Float estimation = vote == null ? null : vote.getEstimatedWork();
			LOG.debug("Estimation:", user.getName(), "->", estimation);

			Widget card;
			if (estimation == null) {
				card = null;
			} else {
				ClickHandler clickHandler = null;
				String clickTooltip = null;
				if (requirement.isWorkEstimationVotingShowoff()) {
					clickHandler = new SelectEstimationClickHandler(estimation);
					clickTooltip = "Use this card as the estimation for this requirement. Planning Poker will be closed.";
				} else if (currentUser) {
					clickHandler = new RemoveEstimationClickHandler();
					clickTooltip = "Remove this card from table.";
				}
				boolean visible = requirement.isWorkEstimationVotingShowoff();
				card = new PlanningPokerCardWidget(estimation, visible, clickHandler, clickTooltip);
			}

			tb.add(new PlanningPokerCardSlotWidget(user.getName(), card));
			tb.add(Gwt.createSpacer(5, 1));
		}
		return tb.createTable();
	}

	private Widget createTableBranding() {
		return Gwt.createDiv("PlanningPokerWidget-table-branding", "Planning Poker");
	}

	private class SelectEstimationClickHandler implements ClickHandler {

		private float estimation;

		public SelectEstimationClickHandler(float estimation) {
			super();
			this.estimation = estimation;
		}

		public void onClick(ClickEvent event) {
			requirement.applyEstimationVoting(estimation);
			cm.getEventBus().fireVisibleDataChanged();
		}
	}

	private class SetEstimationClickHandler implements ClickHandler {

		private float estimation;

		public SetEstimationClickHandler(float estimation) {
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
