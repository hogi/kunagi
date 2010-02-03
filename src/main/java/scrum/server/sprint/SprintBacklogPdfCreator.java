package scrum.server.sprint;

import ilarkesto.pdf.APdfContainerElement;
import ilarkesto.pdf.FieldList;

import java.util.ArrayList;
import java.util.List;

import scrum.server.common.APdfCreator;
import scrum.server.common.BurndownChart;
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
		Sprint sprint = project.getCurrentSprint();

		pdf.paragraph().text("Sprint Backlog", headerFonts[0]);

		pdf.nl();
		pdf.paragraph().text("Burndown", headerFonts[1]);
		pdf.image(BurndownChart.createBurndownChartAsByteArray(sprint, 1000, 500)).setScaleByWidth(150f);

		pdf.nl();
		pdf.paragraph().text("Stories", headerFonts[1]);
		List<Requirement> requirements = new ArrayList<Requirement>(sprint.getRequirements());
		for (Requirement req : requirements) {
			if (req.isClosed()) continue;
			pdf.nl();
			pdf.paragraph().text(req.getReferenceAndLabel(), headerFonts[3]);
			wiki(pdf, req.getDescription());
			FieldList fields = pdf.fieldList().setLabelFontStyle(fieldLabelFont);
			if (req.isTestDescriptionSet()) wiki(fields.field("Test"), req.getTestDescription());
			if (req.isEstimatedWorkSet()) fields.field("Estimated work").text(req.getEstimatedWork());
		}

	}

	@Override
	protected String getFilename() {
		return "sprintbacklog";
	}

}
