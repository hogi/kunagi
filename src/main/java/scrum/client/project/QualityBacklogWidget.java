package scrum.client.project;

import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ComponentManager;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class QualityBacklogWidget extends AScrumWidget {

	public BlockListWidget<Quality> list;
	private ToolbarWidget toolbar;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<Quality>(QualityBlock.FACTORY);
		toolbar = new ToolbarWidget();
		toolbar.addButton(new CreateQualityAction());

		FlowPanel panel = new FlowPanel();
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);

		return new GroupWidget("Quality Backlog", panel);
	}

	@Override
	protected void onUpdate() {
		toolbar.update();
		list.setObjects(getCurrentProject().getQualitys());
	}

	public void showQuality(Quality quality) {
		list.extendObject(quality);
	}

	public static QualityBacklogWidget get() {
		return ComponentManager.get().getProjectContext().getQualityBacklog();
	}

	public void select(Quality quality) {
		list.extendObject(quality);
	}
}
