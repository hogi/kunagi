package scrum.server.project;

import ilarkesto.pdf.APdfContainerElement;
import ilarkesto.pdf.FieldList;

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
			if (req.isClosed()) continue;
			pdf.nl();
			pdf.paragraph().text(req.getReferenceAndLabel(), headerFonts[1]);
			wiki(pdf, req.getDescription());
			FieldList fields = pdf.fieldList().setLabelFontStyle(fieldLabelFont);
			wiki(fields.field("Test"), req.getTestDescription());
			fields.field("Estimated work").text(req.getEstimatedWork());
		}
	}

	@Override
	protected String getFilename() {
		return "productbacklog";
	}

}
