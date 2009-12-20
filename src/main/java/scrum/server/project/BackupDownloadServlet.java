package scrum.server.project;

import ilarkesto.base.PermissionDeniedException;
import ilarkesto.webapp.Servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrum.server.ScrumWebApplication;
import scrum.server.WebSession;
import scrum.server.common.AHttpServlet;

public class BackupDownloadServlet extends AHttpServlet {

	@Override
	protected void onRequest(HttpServletRequest req, HttpServletResponse resp, WebSession session) throws IOException {
		ScrumWebApplication webapp = ScrumWebApplication.get();
		String projectId = req.getParameter("projectId");
		if (projectId == null) throw new RuntimeException("projectId == null");

		Project project = webapp.getProjectDao().getById(projectId);
		if (!project.containsAdmin(session.getUser())) throw new PermissionDeniedException();

		ProjectZipper zipper = new ProjectZipper(new File(webapp.getApplicationDataDir()), project);

		Servlet.setFilename("scrum-project-backup.zip", resp);
		zipper.createZip(resp.getOutputStream());
	}

}
