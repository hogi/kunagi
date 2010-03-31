package scrum.server.common;

import ilarkesto.base.PermissionDeniedException;
import ilarkesto.base.time.Date;
import ilarkesto.pdf.itext.PdfBuilder;
import ilarkesto.webapp.Servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrum.server.WebSession;
import scrum.server.calendar.CalendarPdfCreator;
import scrum.server.collaboration.Wikipage;
import scrum.server.collaboration.WikipagePdfCreator;
import scrum.server.impediments.ImpedimentListPdfCreator;
import scrum.server.project.ProductBacklogPdfCreator;
import scrum.server.risks.RiskListPdfCreator;
import scrum.server.sprint.Sprint;
import scrum.server.sprint.SprintBacklogPdfCreator;
import scrum.server.sprint.SprintReportPdfCreator;

public class PdfServlet extends AHttpServlet {

	private APdfCreator getPdfCreator(String pdfId, HttpServletRequest req, WebSession session) {
		if (pdfId.equals("sprintReport")) return createSprintReport(req, session);
		if (pdfId.equals("wikipage")) return createWikipage(req, session);
		if (pdfId.equals("productBacklog")) return createProductBacklog(req, session);
		if (pdfId.equals("sprintBacklog")) return createSprintBacklog(req, session);
		if (pdfId.equals("impedimentList")) return createImpedimentList(req, session);
		if (pdfId.equals("riskList")) return createRiskList(req, session);
		if (pdfId.equals("calendar")) return createCalendar(req, session);
		throw new RuntimeException("Unknown pdfId: " + pdfId);
	}

	private APdfCreator createCalendar(HttpServletRequest req, WebSession session) {
		Date from = new Date(req.getParameter("from"));
		Date to = new Date(req.getParameter("to"));
		return new CalendarPdfCreator(getProject(session, req), from, to);
	}

	private APdfCreator createRiskList(HttpServletRequest req, WebSession session) {
		return new RiskListPdfCreator(getProject(session, req));
	}

	private APdfCreator createImpedimentList(HttpServletRequest req, WebSession session) {
		return new ImpedimentListPdfCreator(getProject(session, req));
	}

	private APdfCreator createSprintBacklog(HttpServletRequest req, WebSession session) {
		return new SprintBacklogPdfCreator(getProject(session, req));
	}

	private APdfCreator createProductBacklog(HttpServletRequest req, WebSession session) {
		return new ProductBacklogPdfCreator(getProject(session, req));
	}

	private APdfCreator createWikipage(HttpServletRequest req, WebSession session) {
		Wikipage wikipage = getEntityByParameter(req, Wikipage.class);
		if (!wikipage.isProject(getProject(session, req))) throw new PermissionDeniedException();
		return new WikipagePdfCreator(wikipage);
	}

	private APdfCreator createSprintReport(HttpServletRequest req, WebSession session) {
		Sprint sprint = getEntityByParameter(req, Sprint.class);
		if (!sprint.isProject(getProject(session, req))) throw new PermissionDeniedException();
		return new SprintReportPdfCreator(sprint);
	}

	@Override
	protected void onRequest(HttpServletRequest req, HttpServletResponse resp, WebSession session) throws IOException {
		String pdfId = req.getParameter("pdfId");
		if (pdfId == null) throw new RuntimeException("pdfId==null");
		APdfCreator creator = getPdfCreator(pdfId, req, session);

		resp.setContentType("application/pdf");
		Servlet.setFilename(creator.getFilename() + ".pdf", resp);

		PdfBuilder pdf = new PdfBuilder();
		creator.build(pdf);
		pdf.write(resp.getOutputStream());
	}

}
