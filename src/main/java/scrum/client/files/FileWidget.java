package scrum.client.files;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.ScrumGwt;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.project.Project;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class FileWidget extends AScrumWidget {

	private File file;
	private Project project;

	public FileWidget(File impediment) {
		super();
		this.file = impediment;
	}

	@Override
	protected Widget onInitialization() {
		project = Scope.get().getComponent(Project.class);

		TableBuilder left = ScrumGwt.createFieldTable();
		left.addFieldRow("Label", file.getLabelModel());
		String relativeUrl = "fileDownload?projectId=" + project.getId() + "&fileId=" + file.getId();
		HTML downloadLink = Gwt.createServletDownloadLink(relativeUrl, file.getFilename());
		left.addFieldRow("Download", downloadLink);
		left.addFieldRow("Notes", file.getNoteModel());
		left.addFieldRow("Uploaded", file.getUploadTimeModel());

		TableBuilder right = new TableBuilder();
		if (file.isImage()) {
			String url = GWT.getModuleBaseURL() + relativeUrl;
			right.addRow(new HTML("<a href='" + url + "' target='_blank'><img src='" + url + "' width='300px'></a>"));
		}
		right.addRow(new CommentsWidget(file));

		return TableBuilder.row(20, left.createTable(), right.createTable());
	}

}
