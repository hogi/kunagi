package scrum.client.journal;

import java.util.Collections;
import java.util.List;

import scrum.client.common.AScrumGwtEntity;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class ChangeHistoryWidget extends AScrumWidget {

	private AScrumGwtEntity parent;
	private FlowPanel panel;
	private ChangeHistoryManager changeHistoryManager;

	public ChangeHistoryWidget(AScrumGwtEntity parent, ChangeHistoryManager changeHistoryManager) {
		super();
		this.parent = parent;
		this.changeHistoryManager = changeHistoryManager;
	}

	@Override
	protected Widget onInitialization() {
		panel = new FlowPanel();
		return panel;
	}

	@Override
	protected void onUpdate() {
		if (!changeHistoryManager.isChangeHistoryActive(parent)) return;

		panel.setStyleName("ChangeHistoryWidget");
		panel.clear();
		List<Change> changes = changeHistoryManager.getChanges(parent);

		Collections.sort(changes, Change.DATE_AND_TIME_COMPARATOR);
		for (Change change : changes) {
			panel.add(new ChangeWidget(change));
		}

		super.onUpdate();
	}

}
