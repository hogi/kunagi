package scrum.client.project;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;
import scrum.client.workspace.WorkareaWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class QualityBacklogWidget extends AWidget {

	public BlockListWidget<Quality> list;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<Quality>(QualityBlock.FACTORY);
		ToolbarWidget toolbar = new ToolbarWidget(true);
		toolbar.addButton(new CreateQualityAction());

		FlowPanel panel = new FlowPanel();
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);

		return new GroupWidget("Quality Backlog", panel);
	}

	@Override
	protected void onUpdate() {
		list.setObjects(ScrumGwtApplication.get().getProject().getQualitys());
	}

	public void showQuality(Quality quality) {
		list.extendObject(quality);
	}

	public static QualityBacklogWidget get() {
		return WorkareaWidget.get().getQualityBacklog();
	}
}
