package scrum.client.files;

import ilarkesto.core.scope.Scope;
import scrum.client.common.AScrumAction;
import scrum.client.files.Uploader.UploadedFileHandler;

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
		Scope.get().getComponent(Uploader.class).showUploadDialog(null, new ShowFileHandler());
	}

	class ShowFileHandler implements UploadedFileHandler {

		public void onFileUploaded(File file) {
			cm.getProjectContext().showFile(file);
		}

	}

}
