package scrum.server.impediments;

import ilarkesto.pdf.APdfContainerElement;
import ilarkesto.pdf.FieldList;
import scrum.server.common.APdfCreator;
import scrum.server.project.Project;

public class ImpedimentListPdfCreator extends APdfCreator {

	private Project project;

	public ImpedimentListPdfCreator(Project project) {
		super();
		this.project = project;
	}

	@Override
	protected void build(APdfContainerElement pdf) {
		pdf.paragraph().text("Impediments", headerFonts[0]);

		for (Impediment imp : project.getImpediments()) {
			if (imp.isClosed()) continue;
			pdf.nl();
			pdf.paragraph().text(imp.getReferenceAndLabel(), headerFonts[2]);
			wiki(pdf, imp.getDescription());
			pdf.nl();
			FieldList fields = pdf.fieldList().setLabelFontStyle(fieldLabelFont);
			fields.field("Date").text(imp.getDate());
			if (imp.isSolutionSet()) wiki(fields.field("Solution"), imp.getSolution());
		}
	}

	@Override
	protected String getFilename() {
		return "impediments";
	}

}
