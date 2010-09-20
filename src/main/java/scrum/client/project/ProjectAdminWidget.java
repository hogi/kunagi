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

		page.addHeader("Product Descriptions");
		TableBuilder tbDescr = ScrumGwt.createFieldTable();
		tbDescr.addFieldRow("Name", project.getLabelModel());
		tbDescr.addFieldRow("Tagline", project.getShortDescriptionModel());
		tbDescr.addFieldRow("Short Description", project.getDescriptionModel());
		tbDescr.addFieldRow("Long Description", project.getLongDescriptionModel());
		page.addSection(tbDescr.createTable());

		page.addHeader("Project Homepage", new ButtonWidget(new UpdateProjectHomepageAction(project)));
		TableBuilder tbHomepage = ScrumGwt.createFieldTable();
		tbHomepage.addFieldRow("Homepage Direcotry", project.getHomepageDirModel());
		// TODO tbHomepage.addFieldRow("Auto-update homepage", project.getAutoUpdateHomepage());
		page.addSection(tbHomepage.createTable());
		return page;
	}
}
