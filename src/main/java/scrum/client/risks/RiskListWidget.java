package scrum.client.risks;

import ilarkesto.gwt.client.ToolbarWidget;

import java.util.Comparator;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class RiskListWidget extends AScrumWidget {

	public BlockListWidget<Risk> list;
	private ToolbarWidget toolbar;

	@Override
	protected Widget onInitialization() {
		ScrumGwtApplication.get().callRequestRisks();

		list = new BlockListWidget<Risk>(RiskBlock.FACTORY);
		list.setAutoSorter(new RiskComparator());

		toolbar = new ToolbarWidget();
		toolbar.addButton(new CreateRiskAction());

		FlowPanel panel = new FlowPanel();
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);

		return new GroupWidget("Risks", panel);
	}

	@Override
	protected void onUpdate() {
		toolbar.update();
		list.setObjects(getCurrentProject().getRisks());
	}

	public void showRisk(Risk risk) {
		list.extendObject(risk);
	}

	class RiskComparator implements Comparator<Risk> {

		public int compare(Risk a, Risk b) {
			return b.compareTo(a);
		}
	}

	public void select(Risk risk) {
		list.extendObject(risk);
	}

}
