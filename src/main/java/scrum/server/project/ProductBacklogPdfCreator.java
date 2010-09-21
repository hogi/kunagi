package scrum.server.project;

import ilarkesto.pdf.APdfContainerElement;
import ilarkesto.pdf.FieldList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import scrum.server.common.APdfCreator;
import scrum.server.risks.Risk;

public class ProductBacklogPdfCreator extends APdfCreator {

	private Project project;

	public ProductBacklogPdfCreator(Project project) {
		super();
		this.project = project;
	}

	@Override
	protected void build(APdfContainerElement pdf) {
		pdf.paragraph().text("Product Backlog", headerFonts[0]);

		pdf.nl();
		pdf.paragraph().text("Stories", headerFonts[1]);
		List<Requirement> requirements = new ArrayList<Requirement>(project.getRequirements());
		Collections.sort(requirements, project.getRequirementsOrderComparator());
		for (Requirement req : requirements) {
			if (req.isClosed()) continue;
			pdf.nl();
			pdf.paragraph().text(req.getReferenceAndLabel(), headerFonts[3]);
			wiki(pdf, req.getDescription());
			FieldList fields = pdf.fieldList().setLabelFontStyle(fieldLabelFont);
			if (req.isTestDescriptionSet()) wiki(fields.field("Test"), req.getTestDescription());
			if (req.isEstimatedWorkSet()) fields.field("Estimated work").text(req.getEstimatedWork());
		}

		pdf.nl();
		pdf.paragraph().text("Risks", headerFonts[1]);
		for (Risk rsk : project.getRisks()) {
			pdf.nl();
			pdf.paragraph().text(rsk.getReferenceAndLabel(), headerFonts[2]);
			wiki(pdf, rsk.getDescription());
			FieldList fields = pdf.fieldList().setLabelFontStyle(fieldLabelFont);
			fields.field("Priority").text(rsk.getPriorityLabel());
			fields.field("Probability").text(rsk.getProbabilityLabel());
			if (rsk.isProbabilityMitigationSet())
				wiki(fields.field("Probability mitigation"), rsk.getProbabilityMitigation());
			fields.field("Impact").text(rsk.getImpactLabel());
			if (rsk.isImpactMitigationSet()) wiki(fields.field("Impact mitigation"), rsk.getImpactMitigation());
		}
	}

	@Override
	protected String getFilename() {
		return "productbacklog";
	}

}
