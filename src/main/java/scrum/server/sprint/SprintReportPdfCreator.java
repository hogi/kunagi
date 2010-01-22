package scrum.server.sprint;

import ilarkesto.pdf.AParagraph;
import ilarkesto.pdf.APdfContainerElement;
import ilarkesto.pdf.FontStyle;

import java.awt.Color;

import scrum.server.common.APdfCreator;
import scrum.server.common.BurndownChart;
import scrum.server.common.WikiToPdfConverter;

public class SprintReportPdfCreator extends APdfCreator {

	private Sprint sprint;

	public SprintReportPdfCreator(Sprint sprint) {
		super();
		this.sprint = sprint;
	}

	@Override
	protected void build(APdfContainerElement pdf) {
		FontStyle labelStyle = new FontStyle().setSize(4).setItalic(true).setColor(Color.DARK_GRAY);
		FontStyle headerStyle = new FontStyle().setSize(5).setBold(true);

		pdf.paragraph().text("Scrum Sprint Report", headerStyle);

		AParagraph pProps = pdf.paragraph();
		pProps.nl().text("Project: ", labelStyle).text(sprint.getProject().getLabel()).nl();
		pProps.text("Sprint: ", labelStyle).text(sprint.getLabel()).nl();
		pProps.text("Period: ", labelStyle).text(
			sprint.getBegin() + " - " + sprint.getEnd() + " / " + sprint.getLengthInDays() + " days");

		pdf.nl();
		pdf.image(BurndownChart.createBurndownChartAsByteArray(sprint, 1000, 500)).setScaleByWidth(150f);

		pdf.paragraph().nl().text("Velocity: ", labelStyle).text(sprint.getVelocity() + " StoryPoints");

		if (sprint.isGoalSet()) pdf.paragraph().nl().text("Goal", labelStyle).nl().text(sprint.getGoal());

		if (sprint.isCompletedRequirementLabelsSet()) {
			pdf.paragraph().nl().text("Completed Requirements", labelStyle).nl();
			WikiToPdfConverter.buildPdf(pdf, sprint.getCompletedRequirementLabels());
		}

		if (sprint.isReviewNoteSet()) {
			pdf.paragraph().nl().text("Review notes", labelStyle).nl();
			WikiToPdfConverter.buildPdf(pdf, sprint.getReviewNote());
		}

		if (sprint.isRetrospectiveNoteSet()) {
			pdf.paragraph().nl().text("Retrospective notes", labelStyle).nl();
			WikiToPdfConverter.buildPdf(pdf, sprint.getRetrospectiveNote());
		}

	}

	@Override
	protected String getFilename() {
		return "sprint";
	}

}
