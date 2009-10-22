package scrum.client.issues;

import scrum.client.common.ABlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class IssueBlock extends ABlockWidget<Issue> implements TrashSupport {

	private Label typeLabel;

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		typeLabel = new Label();
		Issue issue = getObject();
		typeLabel = header.insertPrefixLabel("150px");
		header.addMenuAction(new ConvertIssueToRequirementAction(issue));
		header.addMenuAction(new ConvertIssueToQualityAction(issue));
		header.addMenuAction(new DeleteIssueAction(issue));
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		Issue issue = getObject();
		typeLabel.setText(issue.getTypeLabel());
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
