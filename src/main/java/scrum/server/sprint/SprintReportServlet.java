package scrum.server.sprint;

import ilarkesto.base.PermissionDeniedException;
import ilarkesto.pdf.APdfBuilder;

import javax.servlet.http.HttpServletRequest;

import scrum.server.ScrumWebApplication;
import scrum.server.WebSession;
import scrum.server.common.APdfServlet;
import scrum.server.project.Project;

public class SprintReportServlet extends APdfServlet {

	@Override
	protected void buildPdf(APdfBuilder pdf, HttpServletRequest req, WebSession session) {
		String sprintId = req.getParameter("sprintId");
		if (sprintId == null) throw new RuntimeException("sprintId==null");
		Project project = session.getProject();
		Sprint sprint = ScrumWebApplication.get().getSprintDao().getById(sprintId);
		if (!sprint.isProject(project)) throw new PermissionDeniedException();
		sprint.buildReport(pdf);
	}

}
