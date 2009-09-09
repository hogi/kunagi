package scrum.client.project;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.AAction;

public class DeleteQualityAction extends AAction {

	private Quality quality;

	public DeleteQualityAction(Quality quality, AWidget... widgetsToUpdate) {
		super(widgetsToUpdate);
		this.quality = quality;
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
		return getProject().getProductOwners().contains(getUser());
	}

	@Override
	protected void onExecute() {
		getProject().deleteQuality(quality);
	}

}
