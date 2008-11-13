package scrum.client.sprint;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListWidget;
import scrum.client.common.ScrumUtil;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SprintListWidget extends Composite implements ClickListener {

	private BlockListWidget list = new BlockListWidget();

	public SprintListWidget() {
		Button createButton = new Button("Create new Sprint");
		createButton.addClickListener(this);

		HorizontalPanel toolbar = new HorizontalPanel();
		toolbar.setWidth("100%");
		toolbar.setStyleName("Toolbar");
		toolbar.add(createButton);
		ScrumUtil.addFiller(toolbar);

		for (Sprint sprint : ScrumGwtApplication.get().getProject().getSprints()) {
			list.addBlock(new SprintWidget(sprint));
		}

		FlowPanel panel = new FlowPanel();
		panel.add(new Label("All sprints!"));
		panel.add(new HTML("<br>"));
		panel.setWidth("100%");
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);

		initWidget(panel);
	}

	public void onClick(Widget sender) {
		list.addBlock(new SprintWidget(ScrumGwtApplication.get().getProject().createNewSprint("<enter sprintname>")));
	}

}
