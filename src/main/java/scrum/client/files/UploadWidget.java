package scrum.client.files;

import gwtupload.client.IUploader;
import gwtupload.client.MultiUploader;
import gwtupload.client.IUploadStatus.Status;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Widget;

public class UploadWidget extends AScrumWidget {

	private MultiUploader uploader;

	@Override
	protected Widget onInitialization() {
		uploader = new MultiUploader();
		uploader.addOnFinishUploadHandler(new FinishHandler());
		return uploader;
	}

	private class FinishHandler implements IUploader.OnFinishUploaderHandler {

		public void onFinish(IUploader ul) {
			if (ul.getStatus() == Status.SUCCESS) ul.cancel();
		}

	}

}
