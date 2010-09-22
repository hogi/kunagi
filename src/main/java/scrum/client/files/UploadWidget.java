package scrum.client.files;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.SingleUploader;
import gwtupload.client.Uploader;
import ilarkesto.core.logging.Log;
import ilarkesto.core.scope.Scope;

import java.util.Set;

import scrum.client.common.AScrumWidget;
import scrum.client.communication.PingServiceCall;
import scrum.client.project.Project;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class UploadWidget extends AScrumWidget {

	private static final Log log = Log.get(UploadWidget.class);

	private FormFlowPanel formPanel = new FormFlowPanel();
	private Button button = new Button();
	private Label statusLabel;
	private SingleUploader uploader;
	private DialogBox dialog;

	public UploadWidget() {
		statusLabel = new Label();
		// uploader = new SingleUploader(FileInputType.BROWSER_INPUT, new UploadStatus(), button, formPanel);
		uploader = new SingleUploader(FileInputType.BROWSER_INPUT);
		uploader.setAutoSubmit(true);
		Uploader.setStatusInterval(1000);
	}

	@Override
	protected Widget onInitialization() {
		uploader.addOnFinishUploadHandler(new FinishHandler());
		return uploader;
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		button.setVisible(false);
	}

	public static UploadWidget showDialog(Integer topPosition) {
		UploadWidget uploadWidget = new UploadWidget();

		uploadWidget.dialog = new DialogBox(true, true);
		DialogBox dialog = uploadWidget.dialog;
		dialog.setAnimationEnabled(true);
		dialog.setWidget(uploadWidget.update());

		dialog.center();
		if (topPosition != null) dialog.setPopupPosition(dialog.getPopupLeft(), topPosition);
		dialog.show();

		return uploadWidget;
	}

	public DialogBox getDialog() {
		return dialog;
	}

	private class FinishHandler implements IUploader.OnFinishUploaderHandler {

		public void onFinish(IUploader ul) {
			if (ul.getStatus() != Status.SUCCESS) {
				new PingServiceCall().execute();
				if (dialog != null) dialog.hide();
			}
		}

	}

	private class UploadStatus implements IUploadStatus {

		private Status status;
		private String filename;

		public HandlerRegistration addCancelHandler(UploadCancelHandler handler) {
			return null;
		}

		public Status getStatus() {
			return status;
		}

		public Widget getWidget() {
			return statusLabel;
		}

		public IUploadStatus newInstance() {
			return null;
		}

		public void setCancelConfiguration(Set<CancelBehavior> config) {
			Log.DEBUG("----------------------> setCancelConfiguration()");
		}

		public void setError(String error) {
			log.error("Upload failed: " + error);
			statusLabel.setText(error);
		}

		public void setFileName(String name) {
			this.filename = name;
		}

		public void setI18Constants(UploadStatusConstants strs) {
			Log.DEBUG("----------------------> setI18()");

		}

		public void setStatus(Status status) {
			Log.DEBUG("Upload status changed:", status);
			if (this.status == null) {
				dialog.setAutoHideEnabled(false);
				formPanel.hideFileField();
				statusLabel.getElement().getStyle().setPadding(10, Unit.PX);
			} else if (status != Status.INPROGRESS && filename != null) {
				statusLabel.setText("Uploading " + filename);
			}
			this.status = status;
		}

		public void setStatusChangedHandler(UploadStatusChangedHandler handler) {}

		public void setVisible(boolean b) {}

		public void setProgress(int done, int total) {
			Log.DEBUG("Progress: " + done + "/" + total);
			if (total == 0) {
				statusLabel.setText("Uploading " + filename);
			} else {
				int percent = done * 100 / total;
				statusLabel.setText("Uploading " + filename + " (" + percent + "%)");
			}
		}
	}

	public class FormFlowPanel extends FormPanel {

		FlowPanel formElements = new FlowPanel();
		Widget fileField;

		public FormFlowPanel() {
			super.add(formElements);
			Project project = Scope.get().getComponent(Project.class);
			Hidden projectIdField = new Hidden("projectId", project.getId());
			projectIdField.setID("uploadProjectId");
			add(projectIdField);
		}

		@Override
		public void add(Widget w) {
			formElements.add(w);
			if (w != button) fileField = w;
		}

		public void hideFileField() {
			fileField.setVisible(false);
		}
	}

}
