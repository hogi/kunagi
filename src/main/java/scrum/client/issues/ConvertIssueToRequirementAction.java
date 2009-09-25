package scrum.client.issues;

import scrum.client.context.ProjectContext;
import scrum.client.project.Requirement;

public class ConvertIssueToRequirementAction extends GConvertIssueToRequirementAction {

	public ConvertIssueToRequirementAction(scrum.client.issues.Issue issue) {
		super(issue);
	}

	@Override
	public String getLabel() {
		return "Convert to Requirement";
	}

	@Override
	public String getTooltip() {
		return "Convert this issue to a real requirement on the product backlog.";
	}

	@Override
	public boolean isExecutable() {
		if (!issue.getProject().isProductOwner(getUser())) return false;
		if (!issue.isTypeRequirement()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		Requirement requirement = new Requirement(issue);
		getDao().createRequirement(requirement);
		getDao().deleteIssue(issue);
		ProjectContext.get().showProductBacklog(requirement);
	}

}