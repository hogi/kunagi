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
import scrum.client.issues.Issue;
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
		tb.addRow(new ButtonWidget(new GenerateRequirementsAction()));
		tb.addRow(new ButtonWidget(new GenerateIssuesAction()));
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

	class GenerateIssuesAction extends AScrumAction {

		@Override
		public String getLabel() {
			return "Generate Issues";
		}

		@Override
		protected void onExecute() {
			DateAndTime time = DateAndTime.now();
			for (int i = 0; i < COUNT; i++) {
				Issue issue = new Issue(getCurrentProject());
				issue.setLabel("Generated Issue " + time + " - #" + i);
				issue.setDescription(longText());
				dao.createIssue(issue);
			}
		}

	}

	class GenerateRequirementsAction extends AScrumAction {

		@Override
		public String getLabel() {
			return "Generate Stories";
		}

		@Override
		protected void onExecute() {
			DateAndTime time = DateAndTime.now();
			for (int i = 0; i < COUNT; i++) {
				final Requirement req = new Requirement(getCurrentProject());
				req.setLabel("Generated Story " + time + " - #" + i);
				req.setDescription(longText());
				req.setTestDescription(longText());
				dao.createRequirement(req);
			}
		}

	}

	private static String longText() {
		return text(10);
	}

	private static String text(int lines) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lines; i++) {
			sb
					.append("This is stupid text. You should not waste your time to read it. There is nothing valuable to find.\n");
		}
		return sb.toString();
	}

	private static final int COUNT = 10;

}
