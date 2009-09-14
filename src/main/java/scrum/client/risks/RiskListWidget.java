package scrum.client.risks;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;

import java.util.Comparator;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class RiskListWidget extends AWidget {

	public BlockListWidget<Risk> list;

	@Override
	protected Widget onInitialization() {
		list = new BlockListWidget<Risk>(RiskBlock.FACTORY);
		list.setAutoSorter(new RiskComparator());

		ToolbarWidget toolbar = new ToolbarWidget(true);
		toolbar.addButton(new CreateRiskAction());

		FlowPanel panel = new FlowPanel();
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);

		return new GroupWidget("Risks", panel);
	}

	@Override
	protected void onUpdate() {
		list.setObjects(ScrumGwtApplication.get().getProject().getRisks());
	}

	public void showRisk(Risk risk) {
		list.extendObject(risk);
	}

	class RiskComparator implements Comparator<Risk> {

		public int compare(Risk a, Risk b) {
			return b.compareTo(a);
		}
	}

}
