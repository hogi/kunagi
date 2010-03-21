package scrum.client.issues;

import ilarkesto.core.scope.Scope;
import scrum.client.common.TooltipBuilder;
import scrum.client.project.Requirement;
import scrum.client.workspace.ProjectWorkspaceWidgets;

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
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		if (issue.isClosed()) return false;
		if (issue.isAcceptedUrgent()) return false;
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
		getDao().createRequirement(requirement);
		issue.appendStatement("Created Story in Product Backlog");
		issue.close();
		Scope.get().getComponent(ProjectWorkspaceWidgets.class).showProductBacklog(requirement);
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
			getDao().deleteRequirement(requirement);
			getDao().createIssue(issue);
		}

	}

}