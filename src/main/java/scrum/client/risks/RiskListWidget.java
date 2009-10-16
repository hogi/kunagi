package scrum.client.risks;

import ilarkesto.gwt.client.ButtonWidget;

import java.util.Comparator;

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
		list.setAutoSorter(new RiskComparator());

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

	private static class RiskComparator implements Comparator<Risk> {

		public int compare(Risk a, Risk b) {
			return b.compareTo(a);
		}
	}

	public void select(Risk risk) {
		list.extendObject(risk);
	}

}
