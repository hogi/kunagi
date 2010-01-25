package scrum.server.common;

import ilarkesto.base.PermissionDeniedException;
import ilarkesto.pdf.itext.PdfBuilder;
import ilarkesto.persistence.AEntity;
import ilarkesto.webapp.Servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrum.server.ScrumWebApplication;
import scrum.server.WebSession;
import scrum.server.collaboration.Wikipage;
import scrum.server.collaboration.WikipagePdfCreator;
import scrum.server.impediments.ImpedimentListPdfCreator;
import scrum.server.project.ProductBacklogPdfCreator;
import scrum.server.project.Project;
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
		throw new RuntimeException("Unknown pdfId: " + pdfId);
	}

	private APdfCreator createImpedimentList(HttpServletRequest req, WebSession session) {
		return new ImpedimentListPdfCreator(getProject(session));
	}

	private APdfCreator createSprintBacklog(HttpServletRequest req, WebSession session) {
		return new SprintBacklogPdfCreator(getProject(session));
	}

	private APdfCreator createProductBacklog(HttpServletRequest req, WebSession session) {
		return new ProductBacklogPdfCreator(getProject(session));
	}

	private APdfCreator createWikipage(HttpServletRequest req, WebSession session) {
		Wikipage wikipage = getEntityByParameter(req, Wikipage.class);
		if (!wikipage.isProject(getProject(session))) throw new PermissionDeniedException();
		return new WikipagePdfCreator(wikipage);
	}

	private APdfCreator createSprintReport(HttpServletRequest req, WebSession session) {
		Sprint sprint = getEntityByParameter(req, Sprint.class);
		if (!sprint.isProject(getProject(session))) throw new PermissionDeniedException();
		return new SprintReportPdfCreator(sprint);
	}

	@Override
	protected void onRequest(HttpServletRequest req, HttpServletResponse resp, WebSession session) throws IOException {
		String pdfId = req.getParameter("pdfId");
		if (pdfId == null) throw new RuntimeException("pdfId==null");
		APdfCreator creator = getPdfCreator(pdfId, req, session);

		resp.setContentType("application/pdf");
		Servlet.setFilename(creator.getFilename(), resp);

		PdfBuilder pdf = new PdfBuilder();
		creator.build(pdf);
		pdf.write(resp.getOutputStream());
	}

	protected String getFilename() {
		return null;
	}

	// --- helper ---

	protected <E extends AEntity> E getEntityByParameter(HttpServletRequest req, Class<E> type) {
		return getEntityByParameter(req, "entityId", type);
	}

	protected <E extends AEntity> E getEntityByParameter(HttpServletRequest req, String parameterName, Class<E> type) {
		String id = req.getParameter(parameterName);
		if (id == null) throw new RuntimeException(parameterName + "==null");
		return (E) ScrumWebApplication.get().getDaoService().getById(id);
	}

	protected Project getProject(WebSession session) {
		return session.getGwtConversation().getProject();
	}

}
