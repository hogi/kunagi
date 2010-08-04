package scrum.client.files;

import ilarkesto.gwt.client.ButtonWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.UserGuideWidget;
import scrum.client.impediments.RequestImpedimentsServiceCall;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class FileRepositoryWidget extends AScrumWidget {

	public BlockListWidget<File> list;

	@Override
	protected Widget onInitialization() {
		new RequestImpedimentsServiceCall().execute();

		list = new BlockListWidget<File>(FileBlock.FACTORY);
		list.setAutoSorter(File.REVERSE_UPLOAD_TIME_COMPARATOR);

		PagePanel page = new PagePanel();
		page.addHeader("File Repository", new ButtonWidget(new UploadFileAction()));
		page.addSection(list);
		page.addSection(new UserGuideWidget(getCurrentProject().getFiles().size() < 5, getLocalizer().views()
				.fileRepository()));
		return page;
	}

	@Override
	protected void onUpdate() {
		list.setObjects(getCurrentProject().getFiles());
		super.onUpdate();
	}

	public void showFile(File file) {
		list.showObject(file);
	}

	public void select(File file) {
		list.showObject(file);
	}
}
