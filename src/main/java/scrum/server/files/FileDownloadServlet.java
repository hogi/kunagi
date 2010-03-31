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
		String reference = req.getParameter("reference");
		if (fileId == null && reference == null) throw new RuntimeException("fileId==null && reference==null");

		Project project = getProject(session, req);
		File file;

		if (fileId != null) {
			file = ScrumWebApplication.get().getFileDao().getById(fileId);
			if (file == null) throw new RuntimeException("File does not exist: " + fileId);
			if (!file.isProject(project)) throw new PermissionDeniedException();
		} else {
			int number = Integer.parseInt(reference.substring(3));
			file = project.getFileByNumber(number);
			if (file == null) throw new RuntimeException("File does not exist: " + reference);
		}

		Servlet.serveFile(file.getJavaFile(), resp);
	}

}
