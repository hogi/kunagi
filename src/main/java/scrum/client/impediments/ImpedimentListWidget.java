package scrum.client.impediments;

import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.Gwt;
import scrum.client.ScrumGwt;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.UserGuideWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class ImpedimentListWidget extends AScrumWidget {

	public BlockListWidget<Impediment> list;

	@Override
	protected Widget onInitialization() {
		new RequestImpedimentsServiceCall().execute();

		list = new BlockListWidget<Impediment>(ImpedimentBlock.FACTORY);
		list.setAutoSorter(Impediment.REVERSE_DATE_COMPARATOR);

		PagePanel page = new PagePanel();
		page.addHeader("Impediment List", new ButtonWidget(new CreateImpedimentAction()));
		page.addSection(Gwt.createFlowPanel(list, Gwt.createSpacer(1, 10),
			ScrumGwt.createPdfLink("Downlad as PDF", "impedimentList", "")));
		page.addSection(new UserGuideWidget(getLocalizer().views().impediments(), getCurrentProject().getImpediments()
				.size() < 5, getCurrentUser().getHideUserGuideImpedimentsModel()));
		return page;
	}

	@Override
	protected void onUpdate() {
		list.setObjects(getCurrentProject().getImpediments());
		super.onUpdate();
	}

	public boolean select(Impediment impediment) {
		if (!list.contains(impediment)) update();
		return list.showObject(impediment);
	}
}
