package scrum.client.test;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.DateAndTime;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;

import java.util.Map;

import scrum.client.ApplicationInfo;
import scrum.client.Dao;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.AScrumAction;
import scrum.client.common.AScrumWidget;
import scrum.client.project.Requirement;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class ScrumStatusWidget extends AScrumWidget {

	private Dao dao;
	private ApplicationInfo applicationInfo;

	private SimplePanel entityCountWrapper;

	@Override
	protected Widget onInitialization() {
		dao = Scope.get().getComponent(Dao.class);
		applicationInfo = ((ScrumGwtApplication) Scope.get().getComponent("app")).getApplicationInfo();

		entityCountWrapper = new SimplePanel();

		PagePanel page = new PagePanel();
		page.addHeader("Entities");
		page.addSection(entityCountWrapper);

		if (!applicationInfo.isProductionStage()) {
			page.addHeader("Generators");
			page.addSection(createGenerators());
		}

		return page;
	}

	private Widget createGenerators() {
		TableBuilder tb = new TableBuilder();
		tb.setWidth(null);
		tb.setCellSpacing(5);
		tb.addRow(new ButtonWidget(new GenerateStoriesAction()));
		return tb.createTable();
	}

	@Override
	protected void onUpdate() {
		TableBuilder tb = new TableBuilder();
		tb.setWidth(null);
		tb.setCellSpacing(5);
		for (Map.Entry<String, Integer> entry : dao.getEntityCounts().entrySet()) {
			tb.addRow(Gwt.createFieldLabel(entry.getKey()), new Label(String.valueOf(entry.getValue())));
		}
		entityCountWrapper.setWidget(tb.createTable());
		super.onUpdate();
	}

	class GenerateStoriesAction extends AScrumAction {

		@Override
		public String getLabel() {
			return "Generate Stories";
		}

		@Override
		protected void onExecute() {
			DateAndTime time = DateAndTime.now();
			for (int i = 0; i < 10; i++) {
				final Requirement req = new Requirement(getCurrentProject());
				req.setLabel("Generated Story " + time + " - #" + i);
				req
						.setDescription("A description for the generated Story. A description for the generated Story. A description for the generated Story. A description for the generated Story. A description for the generated Story. A description for the generated Story. A description for the generated Story. A description for the generated Story. A description for the generated Story. ");
				req
						.setTestDescription("A test description for the generated Story. A test description for the generated Story. A test description for the generated Story. A test description for the generated Story. A test description for the generated Story. A test description for the generated Story. A test description for the generated Story. ");
				dao.createRequirement(req);
			}
		}

	}

}
