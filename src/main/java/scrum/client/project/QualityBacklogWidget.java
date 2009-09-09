package scrum.client.project;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;
import scrum.client.img.Img;
import scrum.client.workspace.WorkareaWidget;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class QualityBacklogWidget extends AWidget {

	public BlockListWidget<Quality> list;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<Quality>(QualityBlock.FACTORY);
		ToolbarWidget toolbar = new ToolbarWidget(true);
		toolbar.addButton(Img.bundle.new16().createImage(), "Create new Quality").addClickListener(
			new CreateClickListener());

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

	class CreateClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Quality quality = ScrumGwtApplication.get().getProject().createNewQuality();
			list.addObject(quality, true);
		}
	}

	public static QualityBacklogWidget get() {
		return WorkareaWidget.get().getQualityBacklog();
	}
}
