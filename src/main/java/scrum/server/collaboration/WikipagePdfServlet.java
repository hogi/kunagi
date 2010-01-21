package scrum.server.collaboration;

import ilarkesto.base.PermissionDeniedException;
import ilarkesto.pdf.APdfBuilder;

import javax.servlet.http.HttpServletRequest;

import scrum.server.ScrumWebApplication;
import scrum.server.WebSession;
import scrum.server.common.APdfServlet;
import scrum.server.project.Project;

public class WikipagePdfServlet extends APdfServlet {

	@Override
	protected void buildPdf(APdfBuilder pdf, HttpServletRequest req, WebSession session) {
		String id = req.getParameter("wikipageId");
		if (id == null) throw new RuntimeException("wikipageId==null");
		Project project = session.getGwtConversation().getProject();
		Wikipage wikipage = ScrumWebApplication.get().getWikipageDao().getById(id);
		if (!wikipage.isProject(project)) throw new PermissionDeniedException();
		wikipage.buildPdf(pdf);
	}

}
