package scrum.client.issues;

import ilarkesto.core.scope.Scope;
import scrum.client.common.TooltipBuilder;
import scrum.client.project.Quality;
import scrum.client.workspace.ProjectWorkspaceWidgets;

public class ConvertIssueToQualityAction extends GConvertIssueToQualityAction {

	public ConvertIssueToQualityAction(scrum.client.issues.Issue issue) {
		super(issue);
	}

	@Override
	public String getLabel() {
		return "Convert to Quality";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Convert this issue to a real Quality on the Quality Backlog.");
		if (!issue.getProject().isProductOwner(getCurrentUser())) {
			tb.addRemark(TooltipBuilder.NOT_PRODUCT_OWNER);
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	public boolean isPermitted() {
		if (!issue.getProject().isProductOwner(getCurrentUser())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		Quality quality = new Quality(issue);
		getDao().createQuality(quality);
		getDao().deleteIssue(issue);
		Scope.get().getComponent(ProjectWorkspaceWidgets.class).showQualityBacklog(quality);
		addUndo(new Undo(quality));
	}

	class Undo extends ALocalUndo {

		private Quality quality;

		public Undo(Quality quality) {
			this.quality = quality;
		}

		@Override
		public String getLabel() {
			return "Undo Convert " + issue.getReference() + " " + issue.getLabel() + " to Quality";
		}

		@Override
		protected void onUndo() {
			getDao().deleteQuality(quality);
			getDao().createIssue(issue);
		}

	}

}
