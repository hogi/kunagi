package scrum.client.admin;

import ilarkesto.gwt.client.AFieldValueWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.IntegerEditorWidget;
import ilarkesto.gwt.client.editor.TextEditorWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.project.Project;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PunishmentsWidget extends AScrumWidget {

	@Override
	protected Widget onInitialization() {

		PagePanel page = new PagePanel();
		Project project = cm.getProjectContext().getProject();

		TableBuilder main = new TableBuilder();
		main.setCellPadding(2);

		main.add(Gwt.createDiv("PunishmentsWidget-tableHeader", "User"));
		main.add(Gwt.createDiv("PunishmentsWidget-tableHeader", "Misconducts"));
		main.add(Gwt.createDiv("PunishmentsWidget-tableHeader", "Punishment"));
		main.nextRow();

		for (User u : project.getParticipants()) {
			Label nameLabel = new Label(u.getName());
			nameLabel.getElement().getStyle().setProperty("color", u.getColor());
			main.add(nameLabel);
			main.add(new IntegerEditorWidget(u.getProjectConfig().getMisconductsModel()));
			main.add(new PunishmentViewer(u.getProjectConfig(), project));
			main.nextRow();
		}

		page.addHeader("Courtroom");
		page.addSection(main.createTable());

		if (project.isScrumMaster(getCurrentUser())) {
			TableBuilder settings = new TableBuilder();
			settings.setCellPadding(2);
			settings.addFieldRow("Factor", new IntegerEditorWidget(project.getPunishmentFactorModel()));
			settings.addFieldRow("Unit", new TextEditorWidget(project.getPunishmentUnitModel()));
			page.addHeader("Punishment Configuration");
			page.addSection(settings.createTable());
		}

		return page;
	}

	private class PunishmentViewer extends AFieldValueWidget {

		private ProjectUserConfig userConfig;
		private Project project;

		public PunishmentViewer(ProjectUserConfig userConfig, Project project) {
			super();
			this.userConfig = userConfig;
			this.project = project;
		}

		@Override
		protected void onUpdate() {
			setText(userConfig.getMisconducts() * project.getPunishmentFactor() + " " + project.getPunishmentUnit());
		}
	}

}
