package scrum.client.risks;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;

import java.util.Comparator;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class RiskListWidget extends AWidget {

	public BlockListWidget<RiskWidget> list;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<RiskWidget>();
		list.setAutoSorter(new RiskWidgetComparator());

		ToolbarWidget toolbar = new ToolbarWidget(true);

		toolbar.addButton("Create new Risk").addClickListener(new CreateClickListener());

		FlowPanel panel = new FlowPanel();
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);

		return new GroupWidget("Risks", panel);
	}

	@Override
	protected void onUpdate() {
		list.clear();
		for (Risk risk : ScrumGwtApplication.get().getProject().getRisks()) {
			RiskWidget widget = new RiskWidget(risk);
			list.addBlock(widget);
		}
		list.update();
	}

	class CreateClickListener implements ClickListener {

		public void onClick(Widget sender) {
			Risk risk = ScrumGwtApplication.get().getProject().createNewRisk();
			RiskWidget block = new RiskWidget(risk);
			list.addBlock(block);
			list.selectBlock(block);
		}
	}

	class RiskWidgetComparator implements Comparator<RiskWidget> {

		public int compare(RiskWidget a, RiskWidget b) {
			return b.getRisk().compareTo(a.getRisk());
		}
	}

}
