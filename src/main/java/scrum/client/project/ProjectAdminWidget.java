package scrum.client.project;

import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import scrum.client.ScrumGwt;
import scrum.client.admin.ProjectBlock;
import scrum.client.common.AScrumWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class ProjectAdminWidget extends AScrumWidget {

	@Override
	protected Widget onInitialization() {

		final Project project = getCurrentProject();

		PagePanel page = new PagePanel();

		page.addHeader("Project Properties");
		TableBuilder tbPro = ScrumGwt.createFieldTable();
		tbPro.addFieldRow("Name", project.getLabelModel());
		tbPro.addFieldRow("Vision", new RichtextEditorWidget(project.getVisionModel()));
		ProjectBlock.addRolesFieldRows(project, tbPro);
		page.addSection(tbPro.createTable());

		page.addHeader("Product Descriptions");
		TableBuilder tbDescr = ScrumGwt.createFieldTable();
		tbDescr.addFieldRow("Name", project.getProductLabelModel());
		tbDescr.addFieldRow("Tagline", project.getShortDescriptionModel());
		tbDescr.addFieldRow("Short Description", project.getDescriptionModel());
		tbDescr.addFieldRow("Long Description", project.getLongDescriptionModel());
		page.addSection(tbDescr.createTable());

		page.addHeader("Project Homepage", new ButtonWidget(new UpdateProjectHomepageAction(project)));
		TableBuilder tbHomepage = ScrumGwt.createFieldTable();
		tbHomepage.addFieldRow("Homepage Direcotry", project.getHomepageDirModel());
		tbHomepage.addFieldRow("Homepage URL", project.getHomepageUrlModel());
		tbHomepage.addFieldRow("Update automatically", project.getAutoUpdateHomepageModel());
		page.addSection(tbHomepage.createTable());
		return page;
	}
}
