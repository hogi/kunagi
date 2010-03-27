package scrum.client.issues;

import scrum.client.collaboration.EmoticonsWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;
import scrum.client.journal.ActivateChangeHistoryAction;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class IssueBlock extends ABlockWidget<Issue> implements TrashSupport {

	private SimplePanel statusIcon;
	// private Label typeLabel;
	private Label statusSuffix;
	private Label severityPrefix;

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		Issue issue = getObject();

		if (issue.isBug()) statusIcon = header.insertPrefixIcon();
		if (issue.isBug()) severityPrefix = header.insertPrefixLabel("50px", false);
		statusSuffix = header.appendCenterSuffix("");

		header.appendCell(new EmoticonsWidget(issue), null, true, true, null);

		header.addMenuAction(new AcceptIssueAsBugAction(issue));
		header.addMenuAction(new AcceptIssueAsIdeaAction(issue));
		header.addMenuAction(new ClaimIssueAction(issue));
		header.addMenuAction(new FixIssueAction(issue));
		header.addMenuAction(new RejectFixIssueAction(issue));
		header.addMenuAction(new ConvertIssueToRequirementAction(issue));
		header.addMenuAction(new ReopenIssueAction(issue));
		header.addMenuAction(new SuspendIssueAction(issue, 7));
		header.addMenuAction(new SuspendIssueAction(issue, 30));
		header.addMenuAction(new SuspendIssueAction(issue, 180));
		header.addMenuAction(new UnsuspendIssueAction(issue));
		header.addMenuAction(new CloseIssueAction(issue));
		header.addMenuAction(new ActivateChangeHistoryAction(issue));
		header.addMenuAction(new DeleteIssueAction(issue));
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		Issue issue = getObject();
		if (issue.isBug()) {
			Image statusImage = null;
			if (severityPrefix != null) severityPrefix.setText(issue.getSeverityLabel());
			if (issue.isFixed()) {
				statusImage = Img.bundle.issFixed().createImage();
				statusImage.setTitle("Closed.");
			} else if (issue.isOwnerSet()) {
				statusImage = Img.bundle.issClaimed().createImage();
				statusImage.setTitle("Claimed by " + issue.getOwner().getName());
			}
			if (statusIcon != null) statusIcon.setWidget(statusImage);
		}
		statusSuffix.setText(issue.getStatusLabel());
		header.setDragHandle(issue.getReference());
		header.setCenter(issue.getLabel());
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new IssueWidget(getObject());
	}

	public AScrumAction getTrashAction() {
		return new DeleteIssueAction(getObject());
	}

	public static final BlockWidgetFactory<Issue> FACTORY = new BlockWidgetFactory<Issue>() {

		public IssueBlock createBlock() {
			return new IssueBlock();
		}
	};
}
