package scrum.client.journal;

import ilarkesto.gwt.client.HyperlinkWidget;

import java.util.Collections;
import java.util.List;

import scrum.client.common.AScrumGwtEntity;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class ChangeHistoryWidget extends AScrumWidget {

	private AScrumGwtEntity parent;

	private FlowPanel panel;
	private FlowPanel changeContainerPanel;
	private HyperlinkWidget showChangesHyperlink;

	public ChangeHistoryWidget(AScrumGwtEntity parent) {
		super();
		this.parent = parent;
	}

	@Override
	protected Widget onInitialization() {
		showChangesHyperlink = new HyperlinkWidget(new ShowChangesAction(parent));

		changeContainerPanel = new FlowPanel();

		panel = new FlowPanel();
		panel.setStyleName("ChangeHistoryWidget");
		panel.add(showChangesHyperlink);
		panel.add(changeContainerPanel);

		return panel;
	}

	@Override
	protected void onUpdate() {
		changeContainerPanel.clear();
		List<Change> changes = cm.getDao().getChangesByParent(parent);

		if (showChangesHyperlink == null && !changes.isEmpty()) {
			panel.remove(showChangesHyperlink);
			showChangesHyperlink = null;
		}

		Collections.sort(changes, Change.DATE_AND_TIME_COMPARATOR);
		for (Change change : changes) {
			changeContainerPanel.add(new ChangeWidget(change));
		}

		super.onUpdate();
	}

}
