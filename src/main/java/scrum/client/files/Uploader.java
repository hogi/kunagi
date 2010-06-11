package scrum.client.files;

import com.google.gwt.user.client.ui.DialogBox;

public class Uploader extends GUploader implements FileUploadedHandler {

	private DialogBox dialog;
	private UploadedFileHandler uploadedFileHandler;

	public void showUploadDialog(Integer topPosition, UploadedFileHandler uploadedFileHandler) {
		log.debug("Acitvating file upload dialog");
		this.uploadedFileHandler = uploadedFileHandler;
		UploadWidget uploadWidget = UploadWidget.showDialog(topPosition);
		this.dialog = uploadWidget.getDialog();
	}

	public void onFileUploaded(FileUploadedEvent event) {
		File file = event.getFile();
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
