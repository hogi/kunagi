package scrum.server.project;

import ilarkesto.pdf.APdfContainerElement;

import java.util.ArrayList;
import java.util.List;

import scrum.server.common.APdfCreator;

public class ProductBacklogPdfCreator extends APdfCreator {

	private Project project;

	public ProductBacklogPdfCreator(Project project) {
		super();
		this.project = project;
	}

	@Override
	protected void build(APdfContainerElement pdf) {
		pdf.paragraph().text("Product Backlog", headerFonts[0]);

		List<Requirement> requirements = new ArrayList<Requirement>(project.getRequirements());
		for (Requirement req : requirements) {
			pdf.paragraph().text(req.getLabel(), headerFonts[1]);
			wiki(pdf, req.getDescription());
		}
	}

	@Override
	protected String getFilename() {
		return "productbacklog";
	}

}
