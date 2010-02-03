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
		TooltipBuilder tb = new TooltipBuilder("Convert this Issue to a real Story on the Product Backlog.");
		if (!issue.getProject().isProductOwner(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_PRODUCT_OWNER);
		} else {
			if (!issue.isTypeRequirement()) tb.addRemark("Issue is not of type Story.");
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		if (!issue.isTypeRequirement()) return false;
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!issue.getProject().isProductOwner(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		Requirement requirement = new Requirement(issue);
		cm.getDao().createRequirement(requirement);
		cm.getDao().deleteIssue(issue);
		cm.getProjectContext().showProductBacklog(requirement);
		addUndo(new Undo(requirement));
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
			cm.getDao().deleteRequirement(requirement);
			cm.getDao().createIssue(issue);
		}

	}

}