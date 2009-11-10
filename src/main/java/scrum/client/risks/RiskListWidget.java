package scrum.client.risks;

import ilarkesto.gwt.client.ButtonWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class RiskListWidget extends AScrumWidget {

	public BlockListWidget<Risk> list;

	@Override
	protected Widget onInitialization() {
		cm.getApp().callRequestRisks();

		list = new BlockListWidget<Risk>(RiskBlock.FACTORY);
		list.setAutoSorter(Risk.PRIORITY_COMPARATOR);

		PagePanel page = new PagePanel();
		page.addHeader("Risk List", new ButtonWidget(new CreateRiskAction()));
		page.addSection(list);
		return page;
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		list.setObjects(getCurrentProject().getRisks());
	}

	public void showRisk(Risk risk) {
		list.extendObject(risk);
	}

	public void select(Risk risk) {
		list.extendObject(risk);
	}

}
