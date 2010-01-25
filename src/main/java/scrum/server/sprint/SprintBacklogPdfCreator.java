package scrum.server.sprint;

import ilarkesto.pdf.APdfContainerElement;
import ilarkesto.pdf.FieldList;

import java.util.ArrayList;
import java.util.List;

import scrum.server.common.APdfCreator;
import scrum.server.impediments.Impediment;
import scrum.server.project.Project;
import scrum.server.project.Requirement;

public class SprintBacklogPdfCreator extends APdfCreator {

	private Project project;

	public SprintBacklogPdfCreator(Project project) {
		super();
		this.project = project;
	}

	@Override
	protected void build(APdfContainerElement pdf) {
		pdf.paragraph().text("Sprint Backlog", headerFonts[0]);

		pdf.nl();
		pdf.paragraph().text("Requirements", headerFonts[1]);
		List<Requirement> requirements = new ArrayList<Requirement>(project.getCurrentSprint().getRequirements());
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
		pdf.paragraph().text("Impediments", headerFonts[1]);
		for (Impediment imp : project.getImpediments()) {
			if (imp.isClosed()) continue;
			pdf.nl();
			pdf.paragraph().text(imp.getReferenceAndLabel(), headerFonts[2]);
			wiki(pdf, imp.getDescription());
			FieldList fields = pdf.fieldList().setLabelFontStyle(fieldLabelFont);
			fields.field("Date").text(imp.getDate());
			if (imp.isSolutionSet()) wiki(fields.field("Solution"), imp.getSolution());
		}
	}

	@Override
	protected String getFilename() {
		return "sprintbacklog";
	}

}
