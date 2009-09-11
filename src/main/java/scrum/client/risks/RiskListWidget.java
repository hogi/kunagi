package scrum.client.risks;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;

import java.util.Comparator;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;
import scrum.client.img.Img;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

		toolbar.addButton(Img.bundle.new16().createImage(), "Create new Risk").addClickHandler(
			new CreateClickListener());

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

	class CreateClickListener implements ClickHandler {

		public void onClick(ClickEvent event) {
			Risk risk = ScrumGwtApplication.get().getProject().createNewRisk();
			list.addObject(risk, true);
		}
	}

	class RiskComparator implements Comparator<Risk> {

		public int compare(Risk a, Risk b) {
			return b.compareTo(a);
		}
	}

}
