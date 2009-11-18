package scrum.client.calendar;

import scrum.client.common.TooltipBuilder;

public class CreateSimpleEventAction extends GCreateSimpleEventAction {

	@Override
	public String getLabel() {
		return "Create new Event";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Create new Event.");
		if (!getCurrentProject().isProductOwnerOrScrumMasterOrTeamMember(getCurrentUser()))
			tb.addRemark(TooltipBuilder.NOT_ANYTHING);

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!getCurrentProject().isProductOwnerOrScrumMasterOrTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		SimpleEvent event = new SimpleEvent();
		cm.getDao().createSimpleEvent(event);
		cm.getProjectContext().showCalendar(event);
	}

}