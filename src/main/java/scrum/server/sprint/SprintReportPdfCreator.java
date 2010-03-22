package scrum.server.sprint;

import ilarkesto.pdf.APdfContainerElement;
import ilarkesto.pdf.FieldList;
import scrum.server.common.APdfCreator;
import scrum.server.common.BurndownChart;
import scrum.server.common.ScrumPdfContext;
import scrum.server.common.WikiToPdfConverter;

public class SprintReportPdfCreator extends APdfCreator {

	private Sprint sprint;

	public SprintReportPdfCreator(Sprint sprint) {
		super();
		this.sprint = sprint;
	}

	@Override
	protected void build(APdfContainerElement pdf) {

		pdf.paragraph().text("Sprint Report", headerFonts[0]);
		pdf.nl();

		FieldList fields = pdf.fieldList().setLabelFontStyle(fieldLabelFont);
		fields.field("Project").text(sprint.getProject().getLabel());
		fields.field("Sprint").text(sprint.getLabel());
		fields.field("Period").text(
			sprint.getBegin() + " - " + sprint.getEnd() + " / " + sprint.getLengthInDays() + " days");
		fields.field("Velocity").text(sprint.getVelocity() + " StoryPoints");

		pdf.nl();
		pdf.image(BurndownChart.createBurndownChartAsByteArray(sprint, 1000, 500)).setScaleByWidth(150f);

		if (sprint.isGoalSet()) pdf.paragraph().nl().text("Goal", headerFonts[1]).nl().text(sprint.getGoal());

		if (sprint.isCompletedRequirementLabelsSet()) {
			pdf.paragraph().nl().text("Completed Stories", headerFonts[1]).nl();
			WikiToPdfConverter.buildPdf(pdf, sprint.getCompletedRequirementLabels(), new ScrumPdfContext());
		}

		if (sprint.isReviewNoteSet()) {
			pdf.paragraph().nl().text("Review notes", headerFonts[1]).nl();
			WikiToPdfConverter.buildPdf(pdf, sprint.getReviewNote(), new ScrumPdfContext());
		}

		if (sprint.isRetrospectiveNoteSet()) {
			pdf.paragraph().nl().text("Retrospective notes", headerFonts[1]).nl();
			WikiToPdfConverter.buildPdf(pdf, sprint.getRetrospectiveNote(), new ScrumPdfContext());
		}

	}

	@Override
	protected String getFilename() {
		return "sprint";
	}

}
