package scrum.client.files;

import scrum.client.common.AScrumAction;

public class UploadFileAction extends AScrumAction {

	@Override
	public String getLabel() {
		return "Upload file";
	}

	@Override
	public String getTooltip() {
		return "Upload a local file to the repository.";
	}

	@Override
	protected void onExecute() {
		cm.getUploader().showUploadDialog();
	}

}
