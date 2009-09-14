package scrum.client.project;

import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.AScrumAction;
import scrum.client.workspace.WorkareaWidget;

public class CreateQualityAction extends AScrumAction {

	public CreateQualityAction(AWidget... widgetsToUpdate) {
		super(widgetsToUpdate);
	}

	@Override
	public String getLabel() {
		return "Create new Quality";
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void onExecute() {
		Quality quality = ScrumGwtApplication.get().getProject().createNewQuality();
		WorkareaWidget.get().showQualityBacklog(quality);
	}

}
