package scrum.client.common;

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
		cm.getProjectContext().showEntity(entity);
	}

}
