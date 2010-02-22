package scrum.client.collaboration;

import ilarkesto.gwt.client.ButtonWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class ForumWidget extends AScrumWidget {

	public BlockListWidget<ForumSupport> list;

	@Override
	protected Widget onInitialization() {
		cm.getApp().callRequestForum();

		list = new BlockListWidget<ForumSupport>(ForumItemBlock.FACTORY);
		list.setAutoSorter(ForumSupport.COMPARATOR);

		PagePanel page = new PagePanel();
		page.addHeader("Forum", new ButtonWidget(new CreateSubjectAction()));
		page.addSection(list);
		return page;
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		list.setObjects(getCurrentProject().getEntitiesWithComments());
	}

	public void select(ForumSupport entity) {
		list.showObject(entity);
	}
}
