package scrum.client.impediments;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;
import scrum.client.workspace.WorkareaWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class ImpedimentListWidget extends AWidget {

	public BlockListWidget<Impediment> list;

	@Override
	protected Widget onInitialization() {
		ScrumGwtApplication.get().callRequestImpediments();

		list = new BlockListWidget<Impediment>(ImpedimentBlock.FACTORY);
		list.setDndSorting(false);

		ToolbarWidget toolbar = new ToolbarWidget(true);
		toolbar.addButton(new CreateImpedimentAction());

		FlowPanel panel = new FlowPanel();
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);

		return new GroupWidget("Impediment List", panel);
	}

	@Override
	protected void onUpdate() {
		list.setObjects(ScrumGwtApplication.get().getProject().getImpediments());
	}

	public void showImpediment(Impediment impediment) {
		list.extendObject(impediment);
	}

	public static ImpedimentListWidget get() {
		return WorkareaWidget.get().getImpedimentList();
	}
}
