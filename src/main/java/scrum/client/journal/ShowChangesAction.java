package scrum.client.journal;

import scrum.client.common.AScrumAction;
import scrum.client.common.AScrumGwtEntity;

public class ShowChangesAction extends AScrumAction {

	private AScrumGwtEntity entity;

	public ShowChangesAction(AScrumGwtEntity entity) {
		super();
		this.entity = entity;
	}

	@Override
	public String getLabel() {
		return "Show change history";
	}

	@Override
	protected void onExecute() {
		cm.getApp().callRequestChanges(entity.getId());
	}

}
