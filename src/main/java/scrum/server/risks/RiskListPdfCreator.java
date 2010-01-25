package scrum.server.risks;

import ilarkesto.pdf.APdfContainerElement;
import ilarkesto.pdf.FieldList;
import scrum.server.common.APdfCreator;
import scrum.server.project.Project;

public class RiskListPdfCreator extends APdfCreator {

	private Project project;

	public RiskListPdfCreator(Project project) {
		super();
		this.project = project;
	}

	@Override
	protected void build(APdfContainerElement pdf) {
		pdf.paragraph().text("Risks", headerFonts[0]);

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
		return "risks";
	}

}
