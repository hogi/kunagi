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

	public static void showDialog(int topPosition, IUploader.OnFinishUploaderHandler finishHandler) {
		UploadWidget uploadWidget = new UploadWidget();
		uploadWidget.uploader.setMaximumFiles(1);
		uploadWidget.finishHandler = finishHandler;

		uploadWidget.dialog = new DialogBox(true, true);
		DialogBox dialog = uploadWidget.dialog;
		dialog.setAnimationEnabled(true);
		dialog.setWidget(uploadWidget.update());
		uploadWidget.uploader.addOnStartUploadHandler(new StartHandler(dialog));

		dialog.center();
		dialog.setPopupPosition(dialog.getPopupLeft(), topPosition);
		dialog.show();
	}

	private static class StartHandler implements IUploader.OnStartUploaderHandler {

		private DialogBox dialog;

		public StartHandler(DialogBox dialog) {
			super();
			this.dialog = dialog;
		}

		public void onStart(IUploader uploader) {
			dialog.setAutoHideEnabled(false);
		}
	}

	private class FinishHandler implements IUploader.OnFinishUploaderHandler {

		public void onFinish(IUploader ul) {
			if (ul.getStatus() == Status.SUCCESS) ul.cancel();
			if (finishHandler != null) finishHandler.onFinish(ul);
			if (dialog != null) dialog.hide();
		}

	}

}
