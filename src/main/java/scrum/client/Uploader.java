package scrum.client;

import gwtupload.client.IUploader;
import gwtupload.client.IUploadStatus.Status;
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
		this.uploadedFileHandler = uploadedFileHandler;
		UploadWidget uploadWidget = UploadWidget.showDialog(topPosition);
		dialog = uploadWidget.getDialog();
		uploadWidget.setFinishHandler(new IUploader.OnFinishUploaderHandler() {

			public void onFinish(IUploader uploader) {
				if (uploader.getStatus() == Status.SUCCESS) {
					cm.getApp().callPing();
				}
			}
		});

	}

	public void onFileUploaded(File file) {
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