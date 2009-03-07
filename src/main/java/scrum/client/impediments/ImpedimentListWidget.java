package scrum.client.impediments;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;
import scrum.client.workspace.WorkareaWidget;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class ImpedimentListWidget extends AWidget {

	public BlockListWidget<Impediment> list;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<Impediment>(ImpedimentWidget.class);
		list.setDndSorting(false);

		ToolbarWidget toolbar = new ToolbarWidget(true);

		toolbar.addButton("Create new Impediment").addClickListener(new CreateClickListener());

		FlowPanel panel = new FlowPanel();
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);

		return new GroupWidget("Impediment List", panel);
	}

	@Override
	protected void onUpdate() {
		list.setBlocks(ScrumGwtApplication.get().getProject().getImpediments());
	}

	class CreateClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Impediment impediment = ScrumGwtApplication.get().getProject().createNewImpediment();
			list.addBlock(impediment, true);
		}
	}

	public static ImpedimentListWidget get() {
		return WorkareaWidget.get().getImpedimentList();
	}
}
