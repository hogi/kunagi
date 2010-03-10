package scrum.client.journal;

import ilarkesto.core.scope.Scope;
import scrum.client.common.AScrumAction;
import scrum.client.common.AScrumGwtEntity;

public class ActivateChangeHistoryAction extends AScrumAction {

	private AScrumGwtEntity entity;

	public ActivateChangeHistoryAction(AScrumGwtEntity entity) {
		super();
		this.entity = entity;
	}

	@Override
	public String getLabel() {
		return "Show change history";
	}

	@Override
	protected void onExecute() {
		Scope.get().getComponent(ChangeHistoryManager.class).activateChangeHistory(entity);
	}

}
