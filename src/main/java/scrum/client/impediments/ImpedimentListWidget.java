package scrum.client.impediments;

import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ComponentManager;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class ImpedimentListWidget extends AScrumWidget {

	public BlockListWidget<Impediment> list;
	private ToolbarWidget toolbar;

	@Override
	protected Widget onInitialization() {
		cm.getApp().callRequestImpediments();

		list = new BlockListWidget<Impediment>(ImpedimentBlock.FACTORY);
		list.setAutoSorter(Impediment.REVERSE_DATE_COMPARATOR);

		toolbar = new ToolbarWidget();
		toolbar.addButton(new CreateImpedimentAction());

		PagePanel page = new PagePanel();
		page.addHeader("Impediment List");
		page.addSection(toolbar);
		page.addSection(list);
		return page;
	}

	@Override
	protected void onUpdate() {
		toolbar.update();
		list.setObjects(getCurrentProject().getImpediments());
	}

	public void showImpediment(Impediment impediment) {
		list.extendObject(impediment);
	}

	public static ImpedimentListWidget get() {
		return ComponentManager.get().getProjectContext().getImpedimentList();
	}

	public void select(Impediment impediment) {
		list.extendObject(impediment);
	}
}
