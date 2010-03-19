package scrum.client.common;

import ilarkesto.core.scope.Scope;
import scrum.client.workspace.ProjectWorkspaceWidgets;

public class ShowEntityAction extends AScrumAction {

	private AScrumGwtEntity entity;
	private String label;

	public ShowEntityAction(AScrumGwtEntity entity, String label) {
		super();
		this.entity = entity;
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	protected void onExecute() {
		Scope.get().getComponent(ProjectWorkspaceWidgets.class).showEntity(entity);
	}

}
