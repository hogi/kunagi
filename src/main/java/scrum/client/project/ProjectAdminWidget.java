package scrum.client.project;

import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.ScrumGwt;
import scrum.client.common.AScrumWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class ProjectAdminWidget extends AScrumWidget {

	@Override
	protected Widget onInitialization() {

		final Project project = getCurrentProject();

		PagePanel page = new PagePanel();

		page.addHeader("Public Descriptions");
		TableBuilder tbDescr = ScrumGwt.createFieldTable();
		tbDescr.addFieldRow("Short description", project.getShortDescriptionModel());
		tbDescr.addFieldRow("Main description", project.getDescriptionModel());
		tbDescr.addFieldRow("Long description", project.getLongDescriptionModel());
		page.addSection(tbDescr.createTable());

		page.addHeader("Project homepage", new ButtonWidget(new UpdateProjectHomepageAction(project)));
		TableBuilder tbHomepage = ScrumGwt.createFieldTable();
		tbHomepage.addFieldRow("Homepage direcotry", project.getHomepageDirModel());
		// TODO tbHomepage.addFieldRow("Auto-update homepage", project.getAutoUpdateHomepage());
		page.addSection(tbHomepage.createTable());

		return page;
	}
}
