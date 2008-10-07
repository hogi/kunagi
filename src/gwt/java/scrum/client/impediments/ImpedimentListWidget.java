package scrum.client.impediments;

import scrum.client.common.BlockListWidget;
import scrum.client.common.Gwt;
import scrum.client.service.Service;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ImpedimentListWidget extends Composite {

	public BlockListWidget list;

	public ImpedimentListWidget() {
		Button createButton = new Button("Create new Impediment");
		createButton.addClickListener(new CreateClickListener());

		HorizontalPanel toolbar = new HorizontalPanel();
		toolbar.setWidth("100%");
		toolbar.setStyleName("Toolbar");
		toolbar.add(createButton);
		Gwt.addFiller(toolbar);

		list = new BlockListWidget();

		FlowPanel panel = new FlowPanel();
		panel
				.add(new Label(
						"Anything that prevents a team member from performing work as efficiently as possible is an impediment. Each team member has an opportunity to announce impediments during the daily Scrum meeting. The ScrumMaster is charged with ensuring impediments get resolved. ScrumMasters often arrange sidebar meetings when impediments cannot be resolved on the spot in the daily Scrum meeting."));
		panel.add(new HTML("<br>"));
		panel.setWidth("100%");
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);
		initWidget(panel);
	}

	public void update() {
		for (Impediment impediment : Service.getProject().getImpediments()) {
			ImpedimentWidget widget = new ImpedimentWidget(impediment);
			list.addBlock(widget);
		}
	}

	class CreateClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Impediment impediment = Service.getProject().createNewImpediment();
			ImpedimentWidget block = new ImpedimentWidget(impediment);
			list.addBlock(block);
			list.selectBlock(block);
		}
	}
}
