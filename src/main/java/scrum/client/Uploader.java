package scrum.client;

import scrum.client.common.AScrumComponent;
import scrum.client.files.File;
import scrum.client.files.UploadWidget;

import com.google.gwt.user.client.ui.DialogBox;

public class Uploader extends AScrumComponent implements FileUploadedListener {

	private DialogBox dialog;
	private UploadedFileHandler uploadedFileHandler;

	public void showUploadDialog() {
		showUploadDialog(null, null);
	}

	public void showUploadDialog(Integer topPosition, UploadedFileHandler uploadedFileHandler) {
		log.debug("Acitvating file upload dialog");
		this.uploadedFileHandler = uploadedFileHandler;
		UploadWidget uploadWidget = UploadWidget.showDialog(topPosition);
		this.dialog = uploadWidget.getDialog();
	}

	public void onFileUploaded(File file) {
		log.debug("File received:", file);
		if (dialog != null) {
			dialog.hide();
			dialog = null;
		}
		if (uploadedFileHandler != null) {
			uploadedFileHandler.onFileUploaded(file);
			uploadedFileHandler = null;
		}
	}

	public static interface UploadedFileHandler {

		void onFileUploaded(File file);

	}

}