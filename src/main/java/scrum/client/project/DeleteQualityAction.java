package scrum.client.project;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.AScrumAction;

public class DeleteQualityAction extends AScrumAction {

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
		return getProject().isProductOwner(getUser());
	}

	@Override
	protected void onExecute() {
		getProject().deleteQuality(quality);
	}

}
