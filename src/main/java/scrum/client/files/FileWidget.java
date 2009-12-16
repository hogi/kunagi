package scrum.client.files;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Widget;

public class FileWidget extends AScrumWidget {

	private File file;

	public FileWidget(File impediment) {
		super();
		this.file = impediment;
	}

	@Override
	protected Widget onInitialization() {
		TableBuilder tb = new TableBuilder();
		tb.setCellPadding(2);
		tb.addFieldRow("Label", file.getLabelModel());
		tb.addFieldRow("Download", Gwt.createServletDownloadLink("fileDownload?fileId=" + file.getId(), file
				.getFilename()));
		tb.addFieldRow("Notes", file.getNoteModel());
		tb.addFieldRow("Uploaded", file.getUploadTimeModel());
		return TableBuilder.row(20, tb.createTable(), new CommentsWidget(file));
	}

}
