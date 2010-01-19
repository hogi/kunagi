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

	public static void showDialog(IUploader.OnFinishUploaderHandler finishHandler) {
		UploadWidget uploadWidget = new UploadWidget();
		uploadWidget.uploader.setMaximumFiles(1);
		uploadWidget.finishHandler = finishHandler;

		uploadWidget.dialog = new DialogBox(false, true);
		uploadWidget.dialog.setAnimationEnabled(true);
		uploadWidget.dialog.setWidget(uploadWidget.update());

		uploadWidget.dialog.center();
		uploadWidget.dialog.show();
	}

	private class FinishHandler implements IUploader.OnFinishUploaderHandler {

		public void onFinish(IUploader ul) {
			if (ul.getStatus() == Status.SUCCESS) ul.cancel();
			if (finishHandler != null) finishHandler.onFinish(ul);
			if (dialog != null) dialog.hide();
		}

	}

}
