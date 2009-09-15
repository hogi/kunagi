package scrum.client.project;


public class DeleteQualityAction extends GDeleteQualityAction {

	protected DeleteQualityAction(Quality quality) {
		super(quality);
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		return "Delete this quality.";
	}

	@Override
	public boolean isExecutable() {
		return getProject().isProductOwner(getUser());
	}

	@Override
	protected void onExecute() {
		getProject().deleteQuality(quality);
	}

}
