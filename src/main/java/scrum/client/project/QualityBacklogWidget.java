package scrum.client.project;

import ilarkesto.gwt.client.ButtonWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class QualityBacklogWidget extends AScrumWidget {

	public BlockListWidget<Quality> list;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<Quality>(QualityBlock.FACTORY);

		PagePanel page = new PagePanel();
		page.addHeader("Quality Backlog", new ButtonWidget(new CreateQualityAction()));
		page.addSection(list);
		return page;
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		list.setObjects(getCurrentProject().getQualitys());
	}

	public void showQuality(Quality quality) {
		list.showObject(quality);
	}

	public void select(Quality quality) {
		list.showObject(quality);
	}
}
