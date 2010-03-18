package scrum.client.journal;

import ilarkesto.core.scope.Scope;

import java.util.Collections;
import java.util.List;

import scrum.client.common.AScrumGwtEntity;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ChangeHistoryWidget extends AScrumWidget {

	private AScrumGwtEntity parent;
	private FlowPanel panel;
	private ChangeHistoryManager changeHistoryManager;

	public ChangeHistoryWidget(AScrumGwtEntity parent) {
		super();
		this.parent = parent;
	}

	@Override
	protected Widget onInitialization() {
		changeHistoryManager = Scope.get().getComponent(ChangeHistoryManager.class);

		panel = new FlowPanel();
		return panel;
	}

	@Override
	protected void onUpdate() {
		if (!changeHistoryManager.isChangeHistoryActive(parent)) return;

		panel.setStyleName("ChangeHistoryWidget");
		panel.clear();
		List<Change> changes = changeHistoryManager.getChanges(parent);

		if (changes.isEmpty()) {
			panel.add(new Label("No change history available."));
		} else {
			Collections.sort(changes, Change.DATE_AND_TIME_COMPARATOR);
			for (Change change : changes) {
				panel.add(new ChangeWidget(change));
			}
		}

		super.onUpdate();
	}

}
