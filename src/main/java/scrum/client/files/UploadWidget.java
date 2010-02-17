package scrum.client.files;

import gwtupload.client.IUploader;
import gwtupload.client.MultiUploader;
import gwtupload.client.IUploadStatus.Status;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;

public class UploadWidget extends AScrumWidget {

	private MultiUploader uploader;
	private IUploader.OnFinishUploaderHandler finishHandler;
	private DialogBox dialog;

	public UploadWidget() {
		uploader = new MultiUploader();
	}

	@Override
	protected Widget onInitialization() {
		uploader.addOnFinishUploadHandler(new FinishHandler());
		return uploader;
	}

	public static UploadWidget showDialog(int topPosition) {
		UploadWidget uploadWidget = new UploadWidget();
		uploadWidget.uploader.setMaximumFiles(1);

		uploadWidget.dialog = new DialogBox(true, true);
		DialogBox dialog = uploadWidget.dialog;
		dialog.setAnimationEnabled(true);
		dialog.setWidget(uploadWidget.update());
		uploadWidget.uploader.addOnStatusChangedHandler(uploadWidget.new StatusChangedHandler());

		dialog.center();
		dialog.setPopupPosition(dialog.getPopupLeft(), topPosition);
		dialog.show();

		return uploadWidget;
	}

	public void setFinishHandler(IUploader.OnFinishUploaderHandler finishHandler) {
		this.finishHandler = finishHandler;
	}

	public DialogBox getDialog() {
		return dialog;
	}

	private class StatusChangedHandler implements IUploader.OnStatusChangedHandler {

		public void onStatusChanged(IUploader ul) {
			dialog.setAutoHideEnabled(false);
		}
	}

	private class FinishHandler implements IUploader.OnFinishUploaderHandler {

		public void onFinish(IUploader ul) {
			if (finishHandler != null) finishHandler.onFinish(ul);
			if (ul.getStatus() != Status.SUCCESS && dialog != null) dialog.hide();
		}

	}

}
