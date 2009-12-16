package scrum.server.files;

import ilarkesto.base.PermissionDeniedException;
import ilarkesto.webapp.Servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrum.server.ScrumWebApplication;
import scrum.server.WebSession;
import scrum.server.common.AHttpServlet;
import scrum.server.project.Project;

public class FileDownloadServlet extends AHttpServlet {

	@Override
	protected void onRequest(HttpServletRequest req, HttpServletResponse resp, WebSession session) throws IOException {
		String fileId = req.getParameter("fileId");
		if (fileId == null) throw new RuntimeException("fileId==null");
		Project project = session.getGwtConversation().getProject();
		File file = ScrumWebApplication.get().getFileDao().getById(fileId);
		if (!file.isProject(project)) throw new PermissionDeniedException();

		Servlet.serveFile(file.getJavaFile(), resp);
	}

}
