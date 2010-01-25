package scrum.server.calendar;

import ilarkesto.base.time.Date;
import ilarkesto.pdf.APdfContainerElement;
import ilarkesto.pdf.FieldList;
import scrum.server.common.APdfCreator;
import scrum.server.project.Project;

public class CalendarPdfCreator extends APdfCreator {

	private Project project;
	private Date from;
	private Date to;

	public CalendarPdfCreator(Project project, Date from, Date to) {
		super();
		this.project = project;
		this.from = from;
		this.to = to;
	}

	@Override
	protected void build(APdfContainerElement pdf) {
		pdf.paragraph().text("Calendar", headerFonts[0]);

		for (SimpleEvent evt : project.getCalendarEvents()) {
			if (evt.getDate().isBefore(from)) continue;
			if (evt.getDate().isAfter(to)) continue;
			pdf.nl();
			StringBuilder date = new StringBuilder();
			date.append(evt.getDate());
			if (evt.isTimeSet()) date.append(" ").append(evt.getTime());
			pdf.paragraph().text(date + " " + evt.getLabel(), headerFonts[2]);
			if (evt.isNoteSet()) wiki(pdf, evt.getNote());
			pdf.nl();
			FieldList fields = pdf.fieldList().setLabelFontStyle(fieldLabelFont);
			if (evt.isDurationSet()) fields.field("Duration").text(evt.getDuration() + " min.");
			if (evt.isLocationSet()) fields.field("Location").text(evt.getLocation());
			if (evt.isAgendaSet()) wiki(fields.field("Agenda"), evt.getAgenda());
		}
	}

	@Override
	protected String getFilename() {
		return "calendar";
	}

}
