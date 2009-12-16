package scrum.client.files;

import gwtupload.client.MultiUploader;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Widget;

public class UploadWidget extends AScrumWidget {

	private MultiUploader uploader;

	@Override
	protected Widget onInitialization() {
		uploader = new MultiUploader();
		return uploader;
	}

}
