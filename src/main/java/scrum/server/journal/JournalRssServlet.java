package scrum.server.journal;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrum.server.ScrumWebApplication;
import scrum.server.WebSession;
import scrum.server.common.AHttpServlet;
import scrum.server.project.Project;
import scrum.server.project.ProjectDao;

public class JournalRssServlet extends AHttpServlet {

	private static final long serialVersionUID = 1;

	private ProjectDao projectDao;

	@Override
	protected void onRequest(HttpServletRequest req, HttpServletResponse resp, WebSession session) throws IOException {
		// TODO auth
		String projectId = req.getParameter("projectId");
		if (projectId == null) throw new RuntimeException("projectId == null");
		Project project = projectDao.getById(projectId);
		resp.setContentType("application/rss+xml");
		String urlBase = req.getRequestURL().toString();
		urlBase = urlBase.substring(0, urlBase.indexOf("/scrum.ScrumGwtApplication/") + 1);
		project.writeJournalAsRss(resp.getOutputStream(), "UTF-8", urlBase);
	}

	@Override
	protected void onInit(ServletConfig config) {
		ScrumWebApplication app = ScrumWebApplication.get(config);
		projectDao = app.getProjectDao();
	}

}
