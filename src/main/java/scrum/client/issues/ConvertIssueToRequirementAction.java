package scrum.client.issues;

import scrum.client.common.TooltipBuilder;
import scrum.client.project.Requirement;

public class ConvertIssueToRequirementAction extends GConvertIssueToRequirementAction {

	public ConvertIssueToRequirementAction(scrum.client.issues.Issue issue) {
		super(issue);
	}

	@Override
	public String getLabel() {
		return "Convert to Story";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Convert this Issue to a Story in the Product Backlog.");
		if (!issue.getProject().isProductOwner(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_PRODUCT_OWNER);
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		if (issue.isClosed()) return false;
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!issue.getProject().isProductOwner(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		new ConvertIssueToStoryServiceCall(issue.getId()).execute();
	}

	class Undo extends ALocalUndo {

		private Requirement requirement;

		public Undo(Requirement requirement) {
			this.requirement = requirement;
		}

		@Override
		public String getLabel() {
			return "Undo Convert " + issue.getReference() + " " + issue.getLabel() + " to Story";
		}

		@Override
		protected void onUndo() {
			getDao().deleteRequirement(requirement);
			getDao().createIssue(issue);
		}

	}

}