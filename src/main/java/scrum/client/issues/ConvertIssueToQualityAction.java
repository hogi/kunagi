package scrum.client.issues;

import scrum.client.common.TooltipBuilder;
import scrum.client.project.Quality;

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
		} else {
			if (!issue.isTypeQuality()) tb.addRemark("Issue is not of type Quality.");
		}

		return tb.getTooltip();
	}

	@Override
	public boolean isExecutable() {
		if (!issue.isTypeQuality()) return false;
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
		cm.getDao().createQuality(quality);
		cm.getDao().deleteIssue(issue);
		cm.getProjectContext().showQualityBacklog(quality);
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
			cm.getDao().deleteQuality(quality);
			cm.getDao().createIssue(issue);
		}

	}

}
