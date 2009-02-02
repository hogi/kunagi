package scrum.client.project;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListWidget;
import scrum.client.common.ToolbarWidget;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RequirementListWidget extends Composite {

	public BlockListWidget<RequirementWidget> list = new BlockListWidget<RequirementWidget>();

	public RequirementListWidget() {
		ToolbarWidget toolbar = new ToolbarWidget();
		toolbar.addButton("Create new Requirement").addClickListener(new CreateClickListener());

		FlowPanel panel = new FlowPanel();
		panel
				.add(new Label(
						"The product backlog (or \"backlog\") is the requirements for a system, expressed as a prioritized list of product backlog Items. These included both functional and non-functional customer requirements, as well as technical team-generated requirements. While there are multiple inputs to the product backlog, it is the sole responsibility of the product owner to prioritize the product backlog."));
		panel.add(new HTML("<br>"));
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);
		initWidget(panel);
	}

	public void update() {
		list.clear();
		for (Requirement item : ScrumGwtApplication.get().getProject().getRequirements()) {
			list.addBlock(new RequirementWidget(item));
		}
	}

	class CreateClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Requirement requirement = ScrumGwtApplication.get().getProject().createNewRequirement();
			RequirementWidget block = new RequirementWidget(requirement);
			list.addBlock(block);
			list.selectBlock(block);
		}
	}
}
