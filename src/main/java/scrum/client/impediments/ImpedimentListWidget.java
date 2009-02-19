package scrum.client.impediments;

import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListController;
import scrum.client.common.BlockListWidget;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ImpedimentListWidget extends Composite {

	public BlockListWidget<ImpedimentWidget> list;

	public ImpedimentListWidget() {
		list = new BlockListWidget<ImpedimentWidget>(new BlockListController<ImpedimentWidget>());

		ToolbarWidget toolbar = new ToolbarWidget();

		toolbar.addButton("Create new Impediment").addClickListener(new CreateClickListener());

		FlowPanel panel = new FlowPanel();
		panel
				.add(new Label(
						"Anything that prevents a team member from performing work as efficiently as possible is an impediment. Each team member has an opportunity to announce impediments during the daily Scrum meeting. The ScrumMaster is charged with ensuring impediments get resolved. ScrumMasters often arrange sidebar meetings when impediments cannot be resolved on the spot in the daily Scrum meeting."));
		panel.add(new HTML("<br>"));
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);
		initWidget(panel);
	}

	public void update() {
		list.clear();
		for (Impediment impediment : ScrumGwtApplication.get().getProject().getImpediments()) {
			ImpedimentWidget widget = new ImpedimentWidget(impediment);
			list.addBlock(widget);
		}
	}

	class CreateClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Impediment impediment = ScrumGwtApplication.get().getProject().createNewImpediment();
			ImpedimentWidget block = new ImpedimentWidget(impediment);
			list.addBlock(block);
			list.selectBlock(block);
		}
	}
}
