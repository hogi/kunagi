package scrum.client.issues;

import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.Widget;

public class IssueBlock extends AExtensibleBlockWidget<Issue> implements TrashSupport {

	@Override
	protected void onCollapsedInitialization() {
		setIcon(Img.bundle.issue16());
	}

	@Override
	protected void onUpdateHead() {
		Issue issue = getObject();
		setBlockTitle(issue.getReference() + " [" + issue.getTypeLabel() + "] " + issue.getLabel());
		addMenuAction(new ConvertIssueToRequirementAction(issue));
		addMenuAction(new ConvertIssueToQualityAction(issue));
		addMenuAction(new DeleteIssueAction(issue));
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new IssueWidget(getObject());
	}

	public AScrumAction getTrashAction() {
		return new DeleteIssueAction(getObject());
	}

	public static BlockWidgetFactory<Issue> FACTORY = new BlockWidgetFactory<Issue>() {

		public IssueBlock createBlock() {
			return new IssueBlock();
		}
	};
}
